package cn.edu.hist.partymanage.controller.backend;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.hist.partymanage.base.BaseController;
import cn.edu.hist.partymanage.entity.Inform;
import cn.edu.hist.partymanage.entity.User;

/*
*@Auther 宋民举
*@Email 860080937@qq.com
*@Date 2017年5月6日
*@Description :
*/
@Controller
@RequestMapping("/backend/inform/")
public class BackendInformController extends BaseController{
	private final String viewPath = "backend/inform/";
	
	@RequestMapping("add")
	public ModelAndView add(){
		ModelAndView modelAndView = new ModelAndView(viewPath+"add");
		modelAndView.addObject("deppartments",departmentService.getAll());
		return modelAndView;
	}
	
	@RequestMapping("addDo")
	public ModelAndView addDo(
			Inform inform,
			HttpSession session,
			int[] canSeeDep,
			int[] canSeeUser
			){
		ModelAndView modelAndView = new ModelAndView("/backend/remind");
		User user = (User)session.getAttribute("user");
		inform.setAutherId(user.getId());
		inform.setAutherName(user.getName());
		inform.setCreateTime(new Timestamp(new Date().getTime()));
		
		StringBuilder dep = new StringBuilder();
		for(int x:canSeeDep){
			if (x==0) {
				inform.setAllDepartment(true);
			}
			dep.append("#"+x);
		}
		dep.append("#");
		inform.setDepartment(dep.toString());
		
		StringBuilder role = new StringBuilder();
		for(int x:canSeeUser){
			if (x==0) {
				inform.setAllRole(true);
			}
			role.append("#"+x);
		}
		role.append("#");
		inform.setRole(role.toString());
		
		System.out.println(inform);
		informService.add(inform);
		modelAndView.addObject("remind","添加成功");
		return modelAndView;
	}
	@RequestMapping(value="manage")
	public ModelAndView manageSearch(
			@RequestParam(name="title",defaultValue="")String title,
			@RequestParam(name="userName",defaultValue="")String userName,
			@RequestParam(name="currPage",defaultValue="1") int currPage){
		
		if(currPage < 1){
			currPage = 1;
		}
		
		ModelAndView modelAndView = new ModelAndView(viewPath+"manage");
		modelAndView.addObject("title",title).addObject("userName",userName);
		modelAndView.addObject("pc",informService.search(title, userName, currPage, 10));
		return modelAndView;
	}
	
	@RequestMapping(value="change",method=RequestMethod.GET)
	public ModelAndView change(@RequestParam(name="id",defaultValue="0")int id){
		if(id<1){
			return null;
		}
		ModelAndView modelAndView = new ModelAndView(viewPath+"change");
		modelAndView.addObject("deppartments",departmentService.getAll());
		modelAndView.addObject("inform",informService.getBy(id));
		
		return modelAndView;
	}
	@RequestMapping(value="changeDo",method=RequestMethod.POST)
	public ModelAndView changeDo(
			Inform inform,
			int[] canSeeDep,
			int[] canSeeUser
			){
		ModelAndView modelAndView = new ModelAndView("/backend/remind");
		Inform old = informService.getBy(inform.getId());
		
		if(old == null){
			return modelAndView.addObject("warning","失败,通知不存在!");
		}
		
		StringBuilder dep = new StringBuilder();
		for(int x:canSeeDep){
			if (x==0) {
				old.setAllDepartment(true);
			}
			dep.append("#"+x);
		}
		dep.append("#");
		old.setDepartment(dep.toString());
		
		StringBuilder role = new StringBuilder();
		for(int x:canSeeUser){
			if (x==0) {
				old.setAllRole(true);
			}
			role.append("#"+x);
		}
		role.append("#");
		old.setRole(role.toString());
		old.setContent(inform.getContent());
		old.setTitle(inform.getTitle());
		
		informService.updateInform(old);
		modelAndView.addObject("remind","修改成功!");
		return modelAndView;
	}
	
	@RequestMapping(value="delete",method=RequestMethod.GET)
	public ModelAndView delete(@RequestParam(name="id",defaultValue="0")int id,HttpServletRequest request){
		
		//这个删除没有做权限验证,反正学校那帮书记们也不会乱鼓捣,差不多得了
		
		ModelAndView modelAndView = new ModelAndView("backend/remind");
		if(id<1){
			modelAndView.addObject("remind","找不到,无法删除啊!");
			return modelAndView;
		}
		User user = (User) request.getSession().getAttribute("user");
		if(user==null || user.getType()>3){
			return modelAndView.addObject("remind","你的权限不够,无法删除.");
		}
		informService.deleteInform(id);
		return modelAndView.addObject("remind","删除成功!");
	}
	
}
