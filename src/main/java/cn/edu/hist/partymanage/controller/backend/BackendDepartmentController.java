package cn.edu.hist.partymanage.controller.backend;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.hist.partymanage.base.BaseController;
import cn.edu.hist.partymanage.entity.Department;

/*
* @author 瀹�
* @mail 860080937@qq.com
* @time 2017骞�3鏈�6鏃� 涓嬪崍10:24:21
* 绫昏鏄�
*/
@Controller
@RequestMapping("/backend/department/")
public class BackendDepartmentController extends BaseController {
	private final String viewPath = "backend/department/";
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public ModelAndView add(){
		ModelAndView modelAndView = new ModelAndView(viewPath+"add");
		modelAndView.addObject("department",departmentService.getById(1));
		return modelAndView;
	}
	@RequestMapping(value="addDo",method=RequestMethod.POST)
	public ModelAndView addPost(Department department){
		System.out.println(department);
		department.setBelongName(departmentService.getById(department.getBelongId()).getName());
		
		ModelAndView modelAndView = new ModelAndView(viewPath+"add");
		modelAndView.addObject("departments",departmentService.getAll());
		if(departmentService.addDepartment(department)){
			modelAndView.addObject("remind","添加成功 !");
		}else{
			modelAndView.addObject("warning","添加失败 !部门名称已经存在,请重新填写!");
		}
		
		return modelAndView;
	}
	/*
	@RequestMapping(value="manage",method=RequestMethod.GET)
	public ModelAndView manage(){
		ModelAndView modelAndView = new ModelAndView(viewPath+"manage");
		modelAndView.addObject("departments",departmentService.getAll());
		return modelAndView;
	}
	*/
	@RequestMapping(value="manage")
	public ModelAndView manageSearch(
			@RequestParam(name="type",defaultValue="0") int type,
			@RequestParam(name="belongId",defaultValue="0") int belongId,
			@RequestParam(name="name",defaultValue="") String name,
			@RequestParam(name="currPage",defaultValue="1") int currPage,
			HttpSession session){
		
		if(currPage <1){
			currPage = 1;
		}
		
		ModelAndView modelAndView = new ModelAndView(viewPath+"manage");
		
		belongId = (int)session.getAttribute("userDepartmentId");
		
		modelAndView.addObject("departments",departmentService.getAll());
		modelAndView.addObject("type",type+"").addObject("belongId", belongId+"").addObject("name",name);
		modelAndView.addObject("pageCut",departmentService.searchDepartment(type, belongId, name, 10, currPage));
		return modelAndView;
	}
	
	@RequestMapping(value="change",method=RequestMethod.GET)
	public ModelAndView change(@RequestParam(name="departmentId",defaultValue="0")int departmentId){
		if(departmentId < 1){
			return null;
		}
		
		ModelAndView modelAndView = new ModelAndView(viewPath+"change");
		modelAndView.addObject("departments",departmentService.getAll());
		modelAndView.addObject("department",departmentService.getById(departmentId));
		return modelAndView;
	}
	@RequestMapping(value="changeDo",method=RequestMethod.POST)
	public ModelAndView changePost(Department department){
		department.setBelongName(departmentService.getById(department.getBelongId()).getName());
		
		ModelAndView modelAndView = new ModelAndView(viewPath+"change");
		modelAndView.addObject("departments",departmentService.getAll());
		if(departmentService.updateDepartment(department)){
			modelAndView.addObject("remind","修改成功 !");
			userService.updateWhileDepartmentChange(department.getId());
		}else{
			modelAndView.addObject("warning","修改失败 !部门名称已经存在或者错误,请重试!");
		}
		modelAndView.addObject("department",department);
		return modelAndView;
	}
	
	@RequestMapping(value="delete",method=RequestMethod.GET)
	public ModelAndView delete(@RequestParam(name="departmentId",defaultValue="0")int departmentId){
		ModelAndView modelAndView = new ModelAndView("backend/remind");
		if(departmentId<2){
			modelAndView.addObject("remind","系统设置此部门无法删除!");
			return modelAndView;
		}
		Department department = departmentService.getById(departmentId);
		int count = -1;
		if(department.getType()==Department.Organization){
			count = userService.getCountByOrganizationId(departmentId);
		}else if(department.getType() == Department.Party){
			count = userService.getCountByPartyId(departmentId);
		}else if(department.getType() == Department.Branch){
			count = userService.getCountByBranchId(departmentId);
		}
		if(count>0){
			modelAndView.addObject("remind","失败,此部门无法删除!因为里面有人存在,请先移除人员至其他部门!");
			return modelAndView;
		}
		if(departmentService.deleteById(departmentId)){
			modelAndView.addObject("remind","删除成功!");
			return modelAndView;
		}else{
			modelAndView.addObject("remind","删除失败,部门不存在或者未知原因.");
			return modelAndView;
		}
	}
}
