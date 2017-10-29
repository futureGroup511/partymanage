package cn.edu.hist.partymanage.controller.backend;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.hist.partymanage.base.BaseController;
import cn.edu.hist.partymanage.entity.WatchVideoLog;

/*
@user song
@date 2017年5月18日
@todo TODO
*/
@Controller
@RequestMapping("/backend/watchVideoLog/")
public class BackendWatchVideoLogController extends BaseController{
	
	private String viewPath = "/backend/watchVideoLog/";
	
	@RequestMapping("seeLog")
	public ModelAndView seeLog(
			@RequestParam(name="userName",defaultValue="")String userName,
			@RequestParam(name="videoName",defaultValue="")String videoName,
			@RequestParam(name="currPage",defaultValue="0")int currPage
			){
		ModelAndView modelAndView = new ModelAndView(viewPath + "seeLog");
		modelAndView.addObject("deps",departmentService.getAll());
		
		modelAndView.addObject("logsNum",0);
		modelAndView.addObject("userName",userName);
		modelAndView.addObject("videoName",videoName);
		List<WatchVideoLog> logs = watchVideoLogService.search(userName,videoName);
		if(logs==null){
			modelAndView.addObject("logsNum",0);
		}else{
			modelAndView.addObject("logsNum",logs.size());
		}
		
		
		return modelAndView.addObject("logs",logs);
	}
}
