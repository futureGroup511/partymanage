package cn.edu.hist.partymanage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import cn.edu.hist.partymanage.base.BaseDao;
import cn.edu.hist.partymanage.entity.Department;
import cn.edu.hist.partymanage.entity.User;
import cn.edu.hist.partymanage.service.IUserService;
import cn.edu.hist.partymanage.util.PageCut;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:15:57
* 类说明
*/
@Service
public class UserService extends BaseDao<User> implements IUserService {

	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return this.saveEntity(user);
	}


	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return this.updateEntity(user);
	}

	public User getById(int id) {
		// TODO Auto-generated method stub
		return this.getEntity(id);
	}

	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return this.deleteEntity(id);
	}

	public User getByPwd(String phone, String password) {
		String hql="from User user where user.phone='"+phone+"' and user.password='"+password+"'";
		return (User)this.uniqueResult(hql);
	}
	@Override
	public List<User> getByOrganizationId(int organizationId) {
		// TODO Auto-generated method stub
		String hql = "from User as u where u.organizationId = ?";
		return this.getEntityList(hql, organizationId);
	}
	@Override
	public List<User> getByPartyId(int partyId) {
		// TODO Auto-generated method stub
		String hql = "from User as u where u.partyId = ?";
		return this.getEntityList(hql, partyId);
	}
	@Override
	public int getCountByOrganizationId(int organizationId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from User as u where u.organizationId = ?";
		return ((Long)this.uniqueResult(hql, organizationId)).intValue();
	}
	@Override
	public int getCountByPartyId(int partyId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from User as u where u.partyId = ?";
		return ((Long)this.uniqueResult(hql, partyId)).intValue();
	}


	@Override
	public List<User> getByBranchId(int branchId) {
		String hql = "from User as u where u.branchId = ?";
		return this.getEntityList(hql, branchId);
	}


	@Override
	public int getCountByBranchId(int branchId) {
		String hql = "select count(*) from User as u where u.branchId = ?";
		return ((Long)this.uniqueResult(hql, branchId)).intValue();
	}


	@Override
	public User getByPhone(String phone) {
		// TODO Auto-generated method stub
		if(phone==null){
			return null;
		}
		String hql = "from User as u where u.phone = ?";
		List<User> list = this.getEntityList(hql, phone);
		return list==null||list.isEmpty()?null:(list.get(0));
	}


	@Override
	public PageCut<User> getPageCutWithSearch(int currPage,int pageSize,String name, String phone, String department) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("from User as u where 1 = 1");
		if(null!=name && !"".equals(name)){
			sb.append(String.format(" and u.name like '%%%s%%'", name));
		}
		if(null!=phone && !"".equals(phone)){
			sb.append(String.format(" and u.phone like '%%%s%%'", phone));
		}
		if(null!=department && !"".equals(department)){
			sb.append(String.format(" and (u.organizationName like '%%%s%%' or u.partyName like '%%%s%%' or u.branchName like '%%%s%%')", department,department,department));
		}
		//System.out.println(sb.toString());
		int count = this.getEntityNum("select count(*) "+sb.toString());
		//System.out.println(count);
		PageCut<User> pc = new PageCut<>(currPage,pageSize,count);
		if(count>0){
			pc.setData(this.getEntityLimitList(sb.toString(),pageSize*(currPage-1), pageSize));
		}
		return pc;
	}
	
	@Override
	public List<User> getByDepartmentId(int departmentId) {
		// TODO Auto-generated method stub
		String hql = "from User as u where u.organizationId=? or u.partyId=? or u.branchId = ?";
		return this.getEntityList(hql, departmentId,departmentId,departmentId);
	}
	@Override
	public void updateWhileDepartmentChange(int department) {
		// TODO Auto-generated method stub
		Department department2 = (Department) this.getSession().get(Department.class, department);
		String hql = "update User as u set u.organizationName = ? where u.organizationId = ?";
		this.executeHql(hql, department2.getName(),department);
		
		hql = "update User as u set u.partyName = ? where u.partyId = ?";
		this.executeHql(hql, department2.getName(),department);
		
		hql = "update User as u set u.branchName = ? where u.branchId = ?";
		this.executeHql(hql, department2.getName(),department);
	}
}
