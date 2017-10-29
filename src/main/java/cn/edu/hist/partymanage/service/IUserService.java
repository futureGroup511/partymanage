package cn.edu.hist.partymanage.service;

import java.util.List;

import cn.edu.hist.partymanage.entity.User;
import cn.edu.hist.partymanage.util.PageCut;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:09:35
* 类说明
*/
public interface IUserService {

	
	boolean addUser(User user);
	boolean updateUser(User user);
	User getById(int id);
	User getByPwd(String phone,String password);
	
	/**
	* @Auther 宋民举
	* @email 860080937@qq.com
	* @Date 2017年4月25日
	* @param phone
	* @return
	* @decoration :查找不到则返回null
	* @careful
	*/
	User getByPhone(String phone);
	
	List<User> getByOrganizationId(int organizationId);
	
	/*
	*@Auther 宋民举
	*@Date 2017年4月18日
	*@Return_type List<User>
	*@Description 获得部门的人员
	*@Careful
	*/
	List<User> getByDepartmentId(int departmentId);
	/*
	*@Auther 宋民举
	*@Date 2017年4月18日
	*@Return_type int
	*@Description 获得组织部人员人数
	*@Careful
	*/
	int getCountByOrganizationId(int organizationId);
	
	/*
	*@Auther 宋民举
	*@Date 2017年4月18日
	*@Return_type List<User>
	*@Description 获得所有党委的人员
	*@Careful
	*/
	List<User> getByPartyId(int partyId);
	
	/*
	*@Auther 宋民举
	*@Date 2017年4月18日
	*@Return_type int
	*@Description
	*@Careful
	*/
	int getCountByPartyId(int partyId);
		
	/*
	*@Auther 宋民举
	*@Date 2017年4月18日
	*@Return_type List<User>
	*@param partyId
	*@return
	*@Description
	*@Careful
	*/
	List<User> getByBranchId(int branchId);
	/*
	*@Auther 宋民举
	*@Date 2017年4月18日
	*@Return_type int
	*@param partyId
	*@return
	*@Description
	*@Careful
	*/
	int getCountByBranchId(int branchId);

	/**
	* @Auther 宋民举
	* @email 860080937@qq.com
	* @Date 2017年4月25日
	* @param id
	* @return
	* @decoration :
	* @careful
	*/
	boolean deleteById(int id);
	
	/**
	* @Auther 宋民举
	* @email 860080937@qq.com
	* @Date 2017年4月25日
	* @param pc
	* @param name
	* @param phone
	* @param department
	* @decoration :查询条件可为null和"",此时不限定此条件
	* @careful
	*/
	PageCut<User> getPageCutWithSearch(int currPage,int pageSize,String name,String phone,String department);
	
	void updateWhileDepartmentChange(int department);

}
