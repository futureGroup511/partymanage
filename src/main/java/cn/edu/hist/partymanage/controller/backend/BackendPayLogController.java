package cn.edu.hist.partymanage.controller.backend;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.hist.partymanage.base.BaseController;
import cn.edu.hist.partymanage.entity.PayLog;
import cn.edu.hist.partymanage.entity.User;

/*
*@Auther 宋民举
*@Email 860080937@qq.com
*@Date 2017年5月7日
*@Description :
*/
@Controller
@RequestMapping("/backend/payLog")
public class BackendPayLogController extends BaseController{
	
	private final String viewPath = "/backend/payLog/";
	
	@RequestMapping("add")
	public ModelAndView add(
			HttpSession session
			){
		ModelAndView modelAndView = new ModelAndView(viewPath+"add");
		User user = (User)session.getAttribute("user");
		List<User> users = userService.getByDepartmentId(user.getDepartmentId());
		modelAndView.addObject("users",users);
		return modelAndView;
		
	}
	
	@RequestMapping("addDo")
	public ModelAndView addDo(
			@RequestParam(name="payNum",defaultValue="0")int payNum,
			@RequestParam(name="lastNum",defaultValue="0")int lastNum,
			@RequestParam(name="userId",defaultValue="0")int userId,
			HttpSession session
			){
		ModelAndView modelAndView = new ModelAndView("/backend/remind");
		if(userId<1){
			return modelAndView.addObject("请正确选择人员!");
		}
		User user = (User)session.getAttribute("user");
		PayLog payLog = new PayLog();
		payLog.setCreateTime(new Timestamp(new Date().getTime()));
		payLog.setDepartmentId(user.getDepartmentId());
		payLog.setDepartmentName(departmentService.getById(payLog.getDepartmentId()).getName());
		payLog.setLastNum(lastNum);
		payLog.setPayNum(payNum);
		payLog.setUserId(userId);
		payLog.setUserName(userService.getById(userId).getName());
		payLogService.savePayLog(payLog);
		return modelAndView.addObject("remind","添加成功!");
	}
	
	@RequestMapping(value="manage")
	public ModelAndView manageSearch(
			@RequestParam(name="userName",defaultValue="")String userName,
			@RequestParam(name="currPage",defaultValue="1") int currPage,
			HttpSession session){
		
		int departmetn =(int) session.getAttribute("userDepartmentId");
		if(currPage <1){
			currPage = 1;
		}
		ModelAndView modelAndView = new ModelAndView(viewPath+"manage");
		modelAndView.addObject("remind","");
		modelAndView.addObject("userName",userName);
		modelAndView.addObject("pc",payLogService.search(departmetn,userName, currPage, 10));
		return modelAndView;
	}
}
