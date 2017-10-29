package cn.edu.hist.partymanage.controller.backend;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.hist.partymanage.base.BaseController;
import cn.edu.hist.partymanage.entity.Department;
import cn.edu.hist.partymanage.entity.User;
import cn.edu.hist.partymanage.util.PageCut;

/* 
* @author 宋民举
* @Date 创建时间：2017年4月19日
* @Description :
*/

@Controller
@RequestMapping("/backend/user/")
public class BackendUserController extends BaseController{
private final String viewPath = "backend/user/";
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public ModelAndView add(){
		ModelAndView modelAndView = new ModelAndView(viewPath+"add");
		modelAndView.addObject("department",departmentService.getById(1));
		return modelAndView;
	}
	@RequestMapping(value="addDo",method=RequestMethod.POST)
	public ModelAndView addPost(User user){
		ModelAndView modelAndView = new ModelAndView(viewPath+"add");
		if(user.getPhone() == null || "".equals(user.getPhone())){
			return modelAndView.addObject("remind","请输入手机号"); 
		}
		user.setPassword("123456");
		User user1 = userService.getByPhone(user.getPhone());
		if(user1!=null){
			modelAndView.addObject("remind","添加失败,手机号已经存在!");
			return modelAndView;
		}
		
		if(!userService.addUser(user)){
			modelAndView.addObject("remind","添加失败,数据库错误!");
			return modelAndView;
		}
		modelAndView.addObject("remind","添加成功!");
		return modelAndView;
	}
	
	@RequestMapping(value="manage")
	public ModelAndView manageSearch(
			@RequestParam(name="name",defaultValue="")String name,
			@RequestParam(name="phone",defaultValue="")String phone,
			@RequestParam(name="department",defaultValue="") String department,
			@RequestParam(name="currPage",defaultValue="1") int currPage,
			HttpServletRequest request){
		if(currPage < 1){
			currPage = 1;
		}
		ModelAndView modelAndView = new ModelAndView(viewPath+"manage");
		User user = (User)request.getSession().getAttribute("user");
		if(user==null){
			return null;
		}
		department=user.getDepartmentName();
		PageCut<User> users = null;
		if(user.getType()==1) {
			users=userService.getPageCutWithSearch(currPage, 10, name, phone, null);
		}else {
			users=userService.getPageCutWithSearch(currPage, 10, name, phone, department);
		}
		
		modelAndView.addObject("users",users);
		modelAndView.addObject("name",name);
		modelAndView.addObject("phone",phone);
		modelAndView.addObject("department",department);
		return modelAndView;
	}
	@RequestMapping(value="change",method=RequestMethod.GET)
	public ModelAndView changePost(@RequestParam(name="id",defaultValue="0")int id){
		if(id<1){
			return null;
		}
		ModelAndView modelAndView = new ModelAndView(viewPath+"change");
		//modelAndView.addObject("departments",departmentService.getAll());
		modelAndView.addObject("organizations",departmentService.getByType(Department.Organization));
		modelAndView.addObject("partys",departmentService.getByType(Department.Party));
		modelAndView.addObject("branchs",departmentService.getByType(Department.Branch));
		modelAndView.addObject("user",userService.getById(id));
		return modelAndView;
	}
	@RequestMapping(value="changeDo",method=RequestMethod.POST)
	public ModelAndView changePost(User u){
		ModelAndView modelAndView = new ModelAndView(viewPath+"change");
		modelAndView.addObject("organizations",departmentService.getByType(Department.Organization));
		modelAndView.addObject("partys",departmentService.getByType(Department.Party));
		modelAndView.addObject("branchs",departmentService.getByType(Department.Branch));
		User userOld = userService.getById(u.getId());
		if(userOld == null){
			return null;
		}
		userOld.setType(u.getType());
		userOld.setOrganizationId(u.getOrganizationId());
		if(u.getOrganizationId()>0){
			userOld.setOrganizationName(departmentService.getById(u.getOrganizationId()).getName());
		}else{
			userOld.setOrganizationName("");
		}
		userOld.setPartyId(u.getPartyId());
		if(u.getPartyId()>0){
			userOld.setPartyName(departmentService.getById(u.getPartyId()).getName());
		}else{
			userOld.setPartyName("");
		}
		userOld.setBranchId(u.getBranchId());
		if(u.getBranchId()>0){
			userOld.setBranchName(departmentService.getById(u.getBranchId()).getName());
		}else{
			userOld.setBranchName("");
		}
		userOld.setName(u.getName());
		userOld.setPhone(u.getPhone());
		userOld.setPassword(u.getPassword());
		modelAndView.addObject("user",userOld);
		if(userService.updateUser(userOld)){
			return modelAndView.addObject("remind","修改成功!");
		}
		return modelAndView.addObject("remind","修改失败!");
	}
	
	@RequestMapping(value="delete",method=RequestMethod.GET)
	public ModelAndView delete(@RequestParam(name="id",defaultValue="0")int id,HttpServletRequest request){
		
		//这个删除没有做权限验证,反正学校那帮书记们也不会乱鼓捣,差不多得了
		
		ModelAndView modelAndView = new ModelAndView("backend/remind");
		if(id<1){
			modelAndView.addObject("remind","找不到这个人,无法删除啊!");
			return modelAndView;
		}
		User user = (User) request.getSession().getAttribute("user");
		if(user==null || user.getType()>3){
			return modelAndView.addObject("remind","你的权限不够,无法删除.");
		}
		userService.deleteById(id);
		return modelAndView.addObject("remind","删除成功!");
	}
}