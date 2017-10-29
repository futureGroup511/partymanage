package cn.edu.hist.partymanage.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月6日 下午9:36:56
* 类说明
*/
@Controller
@RequestMapping("/backend")
public class BackendIndexController {
	private final String viewPath = "backend/";
	
	@RequestMapping("index")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView(viewPath+"index");
		return modelAndView;
	}
	
	//下面是静态页面
	@RequestMapping("menu")
	public String menu(){
		return viewPath + "menu";
	}
	@RequestMapping("head")
	public String head(){
		return viewPath + "head";
	}
	@RequestMapping("welcome")
	public String welcome(){
		return viewPath + "welcome";
	}
}
