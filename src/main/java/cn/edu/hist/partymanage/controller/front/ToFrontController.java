package cn.edu.hist.partymanage.controller.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.edu.hist.partymanage.base.BaseController;
import cn.edu.hist.partymanage.entity.Article;
import cn.edu.hist.partymanage.entity.Department;
import cn.edu.hist.partymanage.entity.IndexImage;
import cn.edu.hist.partymanage.entity.Inform;
import cn.edu.hist.partymanage.entity.PayLog;
import cn.edu.hist.partymanage.entity.User;
import cn.edu.hist.partymanage.entity.Video;
import cn.edu.hist.partymanage.util.PageCut;
import cn.edu.hist.partymanage.util.PaperUtil;
import javassist.bytecode.LineNumberAttribute.Pc;

/**
 * @author 焦祥宇
 * @version 创建时间：2017年3月7日 下午5:00:20
 * @description 党员和积极分子---转发页面控制类
 */
@Controller
@RequestMapping("/party/toFront")
public class ToFrontController extends BaseController {

	// 转到首页
	@RequestMapping(value = "/toIndex", method = RequestMethod.GET)
	public String toIndex(HttpServletRequest request, HttpSession session) {
		User user = (User) session.getAttribute("user");
		// 获得轮播图片
		List<IndexImage> indexImages = indexImageService.getNew(4);
		request.setAttribute("indexImages", indexImages);

		// 获得公告
		List<Inform> informList = informService.getNew(2, user);
		request.setAttribute("informList", informList);

		// 获得文章
		List<Article> newPaperList = articleService.getNew(5, user);
		newPaperList = PaperUtil.titleLength(newPaperList, 15);

		List<Article> hotPaperList = articleService.getHot(5, user);
		hotPaperList = PaperUtil.titleLength(hotPaperList, 15);

		request.setAttribute("newPaperList", newPaperList);
		request.setAttribute("hotPaperList", hotPaperList);

		// 获得视频
		List<Video> recommendVideosList = videoService.getRecommend(7, user);
		List<Video> newVideosList = videoService.getNew(7, user);
		request.setAttribute("recommendVideosList", recommendVideosList);
		request.setAttribute("newVideosList", newVideosList);
		
		//获得活动记录
		List<Article> runRecord = articleService.getArticles(1, 7, 4, "", user).getData();
		runRecord = PaperUtil.titleLength(runRecord, 16);
		request.setAttribute("runRecord", runRecord);

		return "/front/index";
	}

	// 获得党员个人信息
	@RequestMapping(value = "/getSelfInfo", method = RequestMethod.GET)
	public String getSelfInfo(HttpServletRequest request, HttpSession session) {
		int id=((User)session.getAttribute("user")).getId();
		User user=userService.getById(id);
		request.setAttribute("user", user);
		return "/front/getSelfInfo";
	}
	
	//转到修改密码和手机号页面
	@RequestMapping(value = "/toUpdateInfo", method = RequestMethod.GET)
	public String toUpdatePassword() {
		
		return "/front/updateInfo";
	}
	
	//修改密码和手机号
	@RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
	public String updatePassword(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int judge=Integer.valueOf(request.getParameter("judge"));
		User user=(User)session.getAttribute("user");		
		if(judge==1){//修改密码
			
			String password =request.getParameter("newPassword");
			user.setPassword(password);
		}
		else if(judge==2){
			String phoneNum =request.getParameter("newPhoneNum");
			user.setPhone(phoneNum);
		}			
		Boolean bool = userService.updateUser(user);
		if (bool == true) {
			if(judge==1){
				//转到登录页面			
				String returnUrl =request.getContextPath() + "/index.jsp";
				try {
					request.setCharacterEncoding("UTF-8");
					response.setContentType("text/html; charset=UTF-8"); // 转码
					response
						.getWriter()
							.println(
								"<script language=\"javascript\">alert(\"密码修改成功，请重新登录!\");"
									+ "if(window.opener==null){window.top.location.href=\""
										+ returnUrl+ "\";}else{window.opener.top.location.href=\""
											+ returnUrl
												+ "\";window.close();}</script>");								
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;	
			}
			else{
				request.setAttribute("updateMsg", "手机号修改成功！");
				return "/front/updateInfo";
			}
			 
		} else {
			request.setAttribute("updateMsg", "修改失败！");
			return "/front/updateInfo";
		}				
		
	}
	
	/**
	 * 
	 * @param search 搜索的关键字
	 * @param tab 1:学习园地 ，2：影音资料
	 * @return
	 */
	@RequestMapping(value = "/select" , method = RequestMethod.POST)
	public String toSelect(
			@RequestParam(defaultValue = "" , name = "search")String search,
			@RequestParam(name = "tab") String tab,
			RedirectAttributes attr
			){
		 attr.addAttribute("search", search);
		
		if("".equals(search)){
			return null;
		}
		
		if(tab.equals("1")){
			logger.info("redirect:/party/article/list?search="+search);
			String url="/party/article/list";
			return "redirect:"+url;
		}
		if(tab.equals("2")){
			String url="/party/video/videoList";
			return "redirect:"+url;
		}
		return null;
	}
	
	/**
	 * 党委简介  只有以党委 也就是role=2的部门为主
	 * @return
	 */
	@RequestMapping(value = "/intro" , method = RequestMethod.GET)
	public ModelAndView intro(HttpSession session){
		ModelAndView mv = new ModelAndView();
		User user = (User) session.getAttribute("user");
		Department department = departmentService.getById(user.getDepartmentId());
		if(department!=null){
			//只要党委部门
			while(department.getType()>2){
				department = departmentService.getById(department.getBelongId());
			}
			logger.info("department "+department);
			if(department.getType()==2){
				mv.addObject("intro", department.getSummary());
			}
			
		}else{
			mv.addObject("intro", null);
		}
		
		mv.setViewName("front/intro");
		return mv;
	}
		
	/*党费记录*/
	@RequestMapping(value = "/payLog", method = RequestMethod.GET)
	public String payLog(
			@RequestParam(defaultValue = "1" , name = "page") int currentPage,
			HttpServletRequest request, HttpSession session) {
		int userId=((User)session.getAttribute("user")).getId();
		PageCut<PayLog> pc=payLogService.getPc(userId, currentPage, 4);
		request.setAttribute("pc", pc);
		
		return "/front/payLog";
	}
	
	
}
