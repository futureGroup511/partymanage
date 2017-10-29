package cn.edu.hist.partymanage.controller.backend;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.hist.partymanage.base.BaseController;
import cn.edu.hist.partymanage.entity.Article;
import cn.edu.hist.partymanage.entity.ArticleType;
import cn.edu.hist.partymanage.entity.User;
import cn.edu.hist.partymanage.util.SysConfig;
import cn.edu.hist.partymanage.util.TextUtil;

/*
*@Auther 宋民举
*@Email 860080937@qq.com
*@Date 2017年4月29日
*@Description :
*/
@Controller
@RequestMapping("/backend/article/")
public class BackendArticleController extends BaseController{
	private String viewPath = "/backend/article/";
	@RequestMapping("add")
	public ModelAndView add(){
		ModelAndView modelAndView = new ModelAndView(viewPath + "add");
		modelAndView.addObject("articleTypes",articleTypeService.getAll());
		modelAndView.addObject("deppartments",departmentService.getAll());
		return modelAndView;
	}
	@RequestMapping(value="addDo",method=RequestMethod.POST)
	public ModelAndView addDo(
			Article article,
			HttpSession session,
			int[] canSeeDep,
			int[] canSeeUser){
		User user = (User)session.getAttribute("user");
		article.setUserId(user.getId());
		article.setUserName(user.getName());
		article.setCreateTime(new Timestamp(new Date().getTime()));
		article.setTypeName(articleTypeService.getById(article.getType()).getName());
		StringBuilder dep = new StringBuilder();
		for(int x:canSeeDep){
			if (x==0) {
				article.setAllDepartment(true);
			}
			dep.append("#"+x);
		}
		dep.append("#");
		article.setDepartment(dep.toString());
		dep=null;
		StringBuilder role = new StringBuilder();
		for(int x:canSeeUser){
			if (x==0) {
				article.setAllRole(true);
			}
			role.append("#"+x);
		}
		role.append("#");
		article.setRole(role.toString());
		String contextPath = session.getServletContext().getContextPath();
		System.out.println(article.getContent());
		String firstImg = TextUtil.findImgSrcOne(article.getContent());
		System.out.println(firstImg);
		if(firstImg !=null){
			int x = firstImg.indexOf(contextPath);
			firstImg = firstImg.substring(x+contextPath.length()+1);
		}
		
				
		article.setFirstImg(firstImg);
		ModelAndView modelAndView = new ModelAndView("backend/remind");
		logger.info(article);
		if(articleService.add(article)){
			modelAndView.addObject("remind","发表成功!");
		}else{
			modelAndView.addObject("warning","发表失败!数据库错误!");
		}
		return modelAndView;
	}
	@RequestMapping(value="uploadImg",method=RequestMethod.POST)
	public void uploadImg(
			@RequestParam(name="wangEditorH5File",required=false)CommonsMultipartFile file,
			HttpServletRequest request,
			HttpServletResponse response){
		User user = (User)request.getSession().getAttribute("user");
		if(file==null){
			return;
		}
		String temp = file.getOriginalFilename();
		int x =0;
		if(null !=temp && (x =temp.lastIndexOf("."))>0){
			temp=temp.substring(x);
		}else{
			return;
		}
		String name = user.getId()+new Date().getTime()+temp;
		String path = request.getSession().getServletContext().getRealPath("/file/images/");
		File img = new File(path,name);
		try {
			file.transferTo(img);
			response.getWriter().print(SysConfig.FILE_PATH+"images/"+name);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="manage")
	public ModelAndView manageSearch(
			@RequestParam(name="type",defaultValue="0") int type,
			@RequestParam(name="title",defaultValue="")String title,
			@RequestParam(name="userName",defaultValue="")String userName,
			@RequestParam(name="currPage",defaultValue="1") int currPage){
		if(currPage <1){
			currPage = 1;
		}
		
		ModelAndView modelAndView = new ModelAndView(viewPath+"manage");
		modelAndView.addObject("types",articleTypeService.getAll());
		modelAndView.addObject("type",type).addObject("title",title).addObject("userName",userName);
		modelAndView.addObject("pc",articleService.search(type, title, userName, currPage, 10));
		return modelAndView;
	}
	@RequestMapping(value="change",method=RequestMethod.GET)
	public ModelAndView change(@RequestParam(name="id",defaultValue="0")int id){
		if(id<1){
			return null;
		}
		ModelAndView modelAndView = new ModelAndView(viewPath+"change");
		modelAndView.addObject("articleTypes",articleTypeService.getAll());
		modelAndView.addObject("deppartments",departmentService.getAll());
		modelAndView.addObject("article",articleService.getById(id));
		return modelAndView;
	}
	@RequestMapping(value="changeDo",method=RequestMethod.POST)
	public ModelAndView changePost(Article article,
			HttpSession session,
			int[] canSeeDep,
			int[] canSeeUser){
		ModelAndView modelAndView = new ModelAndView("/backend/remind");
		Article old = articleService.getById(article.getId());
		old.setTitle(article.getTitle());
		old.setType(article.getType());
		old.setTypeName(articleTypeService.getById(article.getType()).getName());
		
		StringBuilder dep = new StringBuilder();
		for(int x:canSeeDep){
			if (x==0) {
				old.setAllDepartment(true);
			}
			dep.append("#"+x);
		}
		dep.append("#");
		old.setDepartment(dep.toString());
		dep=null;
		StringBuilder role = new StringBuilder();
		for(int x:canSeeUser){
			if (x==0) {
				old.setAllRole(true);
			}
			role.append("#"+x);
		}
		role.append("#");
		old.setRole(role.toString());
		String firstImg = TextUtil.findImgSrcOne(article.getContent());
		old.setFirstImg(firstImg);
		old.setContent(article.getContent());
		articleService.updateArticle(old);
		return modelAndView.addObject("remind","更新成功!");
	}
	@RequestMapping(value="delete",method=RequestMethod.GET)
	public ModelAndView delete(@RequestParam(name="id",defaultValue="0")int id,HttpServletRequest request){
		
		//这个删除没有做权限验证,反正学校那帮书记们也不会乱鼓捣,差不多得了
		
		ModelAndView modelAndView = new ModelAndView("backend/remind");
		if(id<1){
			modelAndView.addObject("remind","找不到这文章,无法删除啊!");
			return modelAndView;
		}
		User user = (User) request.getSession().getAttribute("user");
		if(user==null || user.getType()>3){
			return modelAndView.addObject("remind","你的权限不够,无法删除.");
		}
		articleService.deleteArticle(id);
		return modelAndView.addObject("remind","删除成功!");
	}
	
	@RequestMapping(value="typeManage",method=RequestMethod.GET)
	public ModelAndView typeManage(){
		ModelAndView modelAndView = new ModelAndView(viewPath+"typeManage");
		modelAndView.addObject("one",articleTypeService.getById(1))
		.addObject("two",articleTypeService.getById(2))
		.addObject("three",articleTypeService.getById(3));
		
		return modelAndView;
	}
	
	@RequestMapping(value="typeManageDo",method=RequestMethod.POST)
	public ModelAndView typeManageDo(
			@RequestParam(name="one",defaultValue="未知")String one,
			@RequestParam(name="two",defaultValue="未知")String two,
			@RequestParam(name="three",defaultValue="未知")String three
			){
		ModelAndView modelAndView = new ModelAndView(viewPath+"typeManage");
		if(one.length()!=4 || two.length()!=4 || three.length()!=4){
			modelAndView.addObject("warning","类型长度必须为4!");
			
			return modelAndView;
		}
		
		
		ArticleType oneType = articleTypeService.getById(1);
		ArticleType twoType = articleTypeService.getById(2);
		ArticleType threeType = articleTypeService.getById(3);
		
		oneType.setName(one);
		twoType.setName(two);
		threeType.setName(three);
		
		articleTypeService.updateArticleType(oneType);
		articleTypeService.updateArticleType(twoType);
		articleTypeService.updateArticleType(threeType);
		
		
		modelAndView.addObject("one",oneType)
		.addObject("two",twoType)
		.addObject("three",threeType);
		articleService.updateType();
		modelAndView.addObject("remind","修改成功!");
		
		return modelAndView;
	}
}