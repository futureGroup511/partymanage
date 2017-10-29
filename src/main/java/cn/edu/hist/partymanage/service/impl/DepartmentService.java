package cn.edu.hist.partymanage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.hist.partymanage.base.BaseDao;
import cn.edu.hist.partymanage.entity.Department;
import cn.edu.hist.partymanage.service.IDepartmentService;
import cn.edu.hist.partymanage.util.PageCut;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:14:02
* 类说明
*/
@Service
public class DepartmentService extends BaseDao<Department> implements IDepartmentService {

	public List<Department> getAll() {
		// TODO Auto-generated method stub
		return this.getEntityList("from Department");
	}

	@Override
	public Department getById(int id) {
		// TODO Auto-generated method stub
		return this.getEntity(id);
	}

	@Override
	public Department getByName(String name) {
		// TODO Auto-generated method stub
		List<Department> list = this.getEntityList("form Department d where d.name = ?", name);
		if(list==null || list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public boolean addDepartment(Department department) {
		// TODO Auto-generated method stub
		int x = this.getEntityNum("select count(*) from Department d where d.name = ?", department.getName());
		if(x>0){
			return false;
		}
		return this.saveEntity(department);
	}

	@Override
	public PageCut<Department> searchDepartment(int type, int belongId, String name, int pageSize, int currPage) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("from Department d where 1 = 1");
		if(type>0){
			sb.append(" and d.type = "+type);
		}
		if(belongId>0){
			sb.append(" and d.belongId = "+belongId);
		}
		if(name!=null && name.length()>0){
			sb.append(String.format(" and d.name like '%%%s%%'", name));
		}
		int count = this.getEntityNum("select count(*) "+sb.toString());
		PageCut<Department> pc = new PageCut<>(currPage,pageSize,count);
		if(count>0){
			pc.setData(this.getEntityLimitList(sb.toString(),pageSize*(currPage-1), pageSize));
		}
		return pc;
	}

	@Override
	public boolean updateDepartment(Department department) {
		// TODO Auto-generated method stub
		return this.updateEntity(department);
	}

	@Override
	public boolean deleteById(int departmentId) {
		// TODO Auto-generated method stub
		if(departmentId < 2){
			return false;
		}
		return this.deleteEntity(departmentId);
	}

	@Override
	public List<Department> getByBelongId(int belongId) {
		String hql = "from Department as dt where dt.belongId = ?";
		return this.getEntityList(hql, belongId);
	}

	@Override
	public List<Department> getByType(int type) {
		// TODO Auto-generated method stub
		String hql = "from Department as dt where dt.type = ?";
		return this.getEntityList(hql, type);
	}

}
