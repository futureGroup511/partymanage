/**        
 * @author: 焦祥宇 
 * @date:   createDate：2017年5月6日 下午8:26:07   
 * @Description:  
 * 
 */  
package cn.edu.hist.partymanage.controller.front;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.hist.partymanage.base.BaseController;
import cn.edu.hist.partymanage.entity.Inform;
import cn.edu.hist.partymanage.entity.User;
import cn.edu.hist.partymanage.util.PageCut;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/party/inform")
public class InformController extends BaseController {
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView informList(
			@RequestParam(defaultValue="1" ,name="page") int page,
			@RequestParam(defaultValue="" , name="search") String search,
			HttpSession session
			){
		ModelAndView mv=new ModelAndView();
		int pageSize=10;
		User user= (User) session.getAttribute("user");
		PageCut<Inform> pc = informService.getInforms(page, pageSize, search, user); 
		mv.addObject("pc", pc);

		mv.setViewName("front/informList");
		return mv;
	}
	
	@RequestMapping(value="/lookInform" , method = RequestMethod.GET)
	public ModelAndView lookInform(@RequestParam(name="id") int id){
		ModelAndView mv=new ModelAndView();
		Inform i=informService.getBy(id);
		mv.addObject("inform" ,i );
		mv.setViewName("front/lookInform");
		return mv;
	}
}
