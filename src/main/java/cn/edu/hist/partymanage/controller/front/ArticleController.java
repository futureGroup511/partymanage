/**        
 * @author: 焦祥宇 
 * @date:   createDate：2017年5月6日 下午8:25:03   
 * @Description:  
 * 
 */
package cn.edu.hist.partymanage.controller.front;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.hist.partymanage.base.BaseController;
import cn.edu.hist.partymanage.entity.Article;
import cn.edu.hist.partymanage.entity.IndexImage;
import cn.edu.hist.partymanage.entity.User;
import cn.edu.hist.partymanage.util.PageCut;
import cn.edu.hist.partymanage.util.PaperUtil;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/party/article")
public class ArticleController extends BaseController {
	
	PageCut<Article> pc=null;
	ModelAndView mv=null;
	
	// 转到学习园地
	@RequestMapping(value = "/toLearnGarden", method = RequestMethod.GET)
	public String toLearnGarden(HttpServletRequest request, HttpSession session) {
		User user = (User) session.getAttribute("user");
		// 获得轮播图片
		List<IndexImage> indexImages = indexImageService.getNew(4);
		request.setAttribute("indexImages", indexImages);

		// 两学一做
		List<Article> partyBuilding = articleService.getArticles(1, 5, 1, "", user).getData();
		partyBuilding = PaperUtil.titleLength(partyBuilding, 16);
		request.setAttribute("partyBuilding", partyBuilding);
		// 党风建设
		List<Article> universityCounseling = articleService.getArticles(1, 5, 2, "", user).getData();
		universityCounseling = PaperUtil.titleLength(universityCounseling, 16);
		request.setAttribute("universityCounseling", universityCounseling);
		// 党政法规
		List<Article> xinxiangStyle = articleService.getArticles(1, 5, 3, "", user).getData();
		xinxiangStyle = PaperUtil.titleLength(xinxiangStyle, 16);
		request.setAttribute("xinxiangStyle", xinxiangStyle);

		return "/front/learnGarden";
	}
	
	@RequestMapping(value = "/list" , method = RequestMethod.GET)
	public ModelAndView list(
			@RequestParam(defaultValue = "1" , name = "page") int curpage,
			@RequestParam(defaultValue = "0" , name = "type" ) int type,
			@RequestParam(defaultValue = "" , name = "search") String search,
			HttpSession session
			) throws UnsupportedEncodingException{
		 mv = new ModelAndView();

		 
		try {
			if (search!=null&&search.equals(new String(search.getBytes("iso8859-1"), "iso8859-1"))) {
				//判断是不是utf-8如果不是进行转码
				try {
					search= new String(search.getBytes("iso8859-1"),"utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		 logger.info("search :" + search);
		 logger.info("type: "+ type);
		 User user = (User) session.getAttribute("user");
		 pc = articleService.getArticles(curpage, 15, type, search, user) ;
		if(type !=0 ){
			mv.addObject("type", pc.getData().get(0).getType());
			mv.addObject("flag", pc.getData().get(0).getTypeName());
		}else{
			mv.addObject("flag", "搜素结果");
			mv.addObject("search", search);
		}
		
		
		mv.addObject("pc", pc);
		mv.setViewName("/front/paperSectionList");


		return mv;
	}
	
	/**
	 * 上一篇下一篇
	 * @author 丁赵雷
	 * @date 2017/5/11
 	 * @param id 文章的id
	 * @param search 关键字（可为空）
	 * @param upAndDown false：上一篇， true：下一篇 
	 * @param type 文章类型的id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/lookArticle" , method = RequestMethod.GET)
	public ModelAndView lookArticle(
			@RequestParam(name = "id") int id,
			@RequestParam(name = "search" , defaultValue = "") String search,
			@RequestParam(name = "type" , defaultValue = "0") int type,
			HttpSession session
			){
		mv = new ModelAndView();
		User user = (User) session.getAttribute("user");
		Article a = articleService.getById(id);
		a.setReadNum(a.getReadNum()+1);//阅读次数加一
		articleService.updateArticle(a);
		//这段代码日后整理
		try {
			if (search!=null&&search.equals(new String(search.getBytes("iso8859-1"), "iso8859-1"))) {
				//判断是不是utf-8如果不是进行转码
				try {
					search= new String(search.getBytes("iso8859-1"),"utf-8");
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		logger.info("search ="+search);
		
		List<Article> pre = articleService.getUpAndDown(id, type, search, false, user);
		List<Article> next = articleService.getUpAndDown(id, type, search, true, user);
		logger.info("pre.size "+pre.size());
		logger.info("next.size="+next.size());
		mv.addObject("paper", a);
		mv.addObject("pre", pre);
		mv.addObject("next", next);
		
		
		if(type!=0){
			mv.addObject("type", a.getType());
			mv.addObject("flag", a.getTypeName());
		}else{
			mv.addObject("search", search);
			mv.addObject("flag", "搜索结果");
		}
		mv.setViewName("/front/lookPaper");
		return mv;
	}
}
