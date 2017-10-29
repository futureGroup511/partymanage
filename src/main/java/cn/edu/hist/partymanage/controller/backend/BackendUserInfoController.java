package cn.edu.hist.partymanage.controller.backend;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.hist.partymanage.base.BaseController;
import cn.edu.hist.partymanage.entity.User;

/*
*@Auther 宋民举
*@Email 860080937@qq.com
*@Date 2017年5月7日
*@Description :
*/
@Controller
@RequestMapping("/backend/userInfo/")
public class BackendUserInfoController extends BaseController{
	private final String viewPath = "/backend/userInfo/";
	@RequestMapping("index")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView(viewPath+"index");
		return modelAndView;
	}
	@RequestMapping("changeDo")
	public ModelAndView changeDo(
			User user1,
			HttpSession session
			){
		ModelAndView modelAndView = new ModelAndView(viewPath+"index");
		User user = (User)session.getAttribute("user");
		try{
			user.setSex(user1.getSex());
			user.setBirthday(user1.getBirthday());
			user.setAge(user1.getAge());
			user.setNation(user1.getNation());
			user.setNativePlace(user1.getNativePlace());
			user.setEducation(user1.getEducation());
			user.setAcademicDegree(user1.getAcademicDegree());
			user.setTitle(user1.getTitle());
			user.setJoinWorkDate(user1.getJoinWorkDate());
			user.setJoinPartyDate(user1.getJoinPartyDate());
			user.setGrade(user1.getGrade());
			user.setClassName(user1.getClassName());
			user.setNowJob(user1.getNowJob());
			user.setJoinSchoolDate(user1.getJoinSchoolDate());
		}catch (Exception e) {
			// TODO: handle exception
			modelAndView.addObject("warning","错误,请正确填写信息!");
			return modelAndView;
		}
		
		
		userService.updateUser(user);
		session.setAttribute("user", user);
		
		modelAndView.addObject("remind","修改成功!");
		
		return modelAndView;
	}
	@RequestMapping("logOut")
	public String logOut(
			HttpSession session
			){
		session.invalidate();
		return "redirect:/index";
	}
}
