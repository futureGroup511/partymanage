package cn.edu.hist.partymanage.service;

import java.util.List;

import cn.edu.hist.partymanage.entity.Department;
import cn.edu.hist.partymanage.util.PageCut;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:08:38
* 类说明
*/
public interface IDepartmentService {

	/*
	*@Auther 宋民举
	*@Date 2017年4月18日
	*@Return_type List<Department>
	*@Description
	*@Careful
	*/
	List<Department> getAll();

	/*
	*@Auther 宋民举
	*@Date 2017年4月18日
	*@Return_type Department
	*@Description
	*@Careful
	*/
	Department getById(int id);

	/*
	*@Auther 宋民举
	*@Date 2017年4月18日
	*@Return_type Department
	*@Description
	*@Careful
	*/
	Department getByName(String name);

	/**
	 * @param department
	 * @return 当名字存在时添加失败，返回false，其他异常同样返回false
	 * @author 宋
	 */
	boolean addDepartment(Department department);
	
	/*
	*@Auther 宋民举
	*@Date 2017年4月18日
	*@Return_type boolean
	*@Description
	*@Careful 当Id为1,拒绝删除,因为组织部不能删除
	*/
	boolean deleteById(int departmentId);

	/**
	 * @param type,类型，为0则不限制
	 * @param belongId，所属部门Id,为0则不限制
	 * @param name，名字，为""或者null则不限制
	 * @param pageSize
	 * @param currPage
	 * @return
	 * @author 宋
	 */
	PageCut<Department> searchDepartment(int type, int belongId, String name, int pageSize, int currPage);
	
	/*
	*@Auther 宋民举
	*@Date 2017年4月18日
	*@Return_type boolean
	*@param department
	*@return
	*@Description
	*@Careful
	*/
	boolean updateDepartment(Department department);
	
	/*
	*@Auther 宋民举
	*@Date 2017年4月19日
	*@Return_type List<Department>
	*@param belongId
	*@return
	*@Description 获得部门下所有部门
	*@Careful
	*/
	List<Department> getByBelongId(int belongId);
	
	/**
	* @Auther 宋民举
	* @email 860080937@qq.com
	* @Date 2017年4月28日
	* @param type
	* @return
	* @decoration :根据type获取所有部门
	* @careful
	*/
	List<Department> getByType(int type);
	
}
