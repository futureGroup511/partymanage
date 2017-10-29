package cn.edu.hist.partymanage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.hist.partymanage.base.BaseDao;
import cn.edu.hist.partymanage.entity.Article;
import cn.edu.hist.partymanage.entity.Inform;
import cn.edu.hist.partymanage.entity.User;
import cn.edu.hist.partymanage.service.IInformService;
import cn.edu.hist.partymanage.util.PageCut;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月5日 下午2:53:44
* 类说明
*/
@Service
public class InformService extends BaseDao<Inform> implements IInformService {
	@Override
	public List<Inform> getNew(int num,User user) {		
		String hql="from Inform i " + getCondition(user) + "  order by i.id desc";
		return this.getEntityLimitList(hql, 0, num);
	}
	
	//丁赵雷
	@Override
	public PageCut<Inform> getInforms(int curPage, int pageSize, String search, User user) {
		
		PageCut<Inform> pc=null;
		
		String organizationId="%#"+user.getOrganizationId()+"#%";
		String partyId="%#"+user.getPartyId()+"#%";
		String branchId="%#"+user.getBranchId()+"#%";
		String type="%#"+user.getType()+"#%";
		String keyword="%"+search+"%";
		String hqlSearch=" and i.title like ?  ";
		String hqlNum="select count(*) ";
		String order="order by i.id desc";
		
		String hql="from Inform i where (i.department like ? or i.department like ? "
				+ "or i.department like ? or i.allDepartment=1) and (i.role like ? or i.allRole=1) ";
		StringBuilder sb = new StringBuilder(hql);
		if(search!=null&&!"".equals(search)){
			sb.append(hqlSearch);
			sb.append(order);
			int count = this.getEntityNum(hqlNum+sb.toString(), organizationId , 
					partyId , branchId , type , keyword);
			pc = new PageCut<>(curPage-1,pageSize,count);
			
			if(count>0){
				pc.setData(this.getEntityLimitList(sb.toString(), pageSize*(curPage-1), pageSize , organizationId , 
						partyId , branchId , type , keyword));
			}
		}else{
			
			sb.append(order);
			int count = this.getEntityNum(hqlNum+sb.toString(), organizationId , 
					partyId , branchId , type);
			pc = new PageCut<>(curPage,pageSize,count);
			
			if(count>0){
				pc.setData(this.getEntityLimitList(sb.toString(), pageSize*(curPage-1), pageSize , organizationId , 
						partyId , branchId , type));
			}
		}
		
		

		return pc;
	}

	public String getCondition(User user) {
		return "where (i.department like '%#"+user.getOrganizationId()+"#%' or i.department like '%#"+user.getPartyId()+"#%' or i.department like '%#"+user.getBranchId()+"#%' or i.allDepartment=1) and (i.role like '%#"+user.getType()+"#%' or i.allRole=1)";
	}
	

	@Override
	public boolean add(Inform inform) {
		return this.saveEntity(inform);
	}

	@Override
	public PageCut<Inform> search(String title, String userName, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("from Inform as i where 1 = 1");

		if(title!=null && title.length()>0){
			sb.append(String.format(" and i.title like '%%%s%%'", title));
		}
		if(userName!=null && userName.length()>0){
			sb.append(String.format(" and i.autherName like '%%%s%%'", userName));
		}
		int count = this.getEntityNum("select count(*) "+sb.toString());
		PageCut<Inform> pc = new PageCut<>(currentPage,pageSize,count);
		if(count>0){
			pc.setData(this.getEntityLimitList(sb.toString(),pageSize*(currentPage-1), pageSize));
		}
		return pc;
	}
	@Override
	public Inform getBy(int id) {
		// TODO Auto-generated method stub
		return this.getEntity(id);
	}

	@Override
	public boolean updateInform(Inform inform) {
		// TODO Auto-generated method stub
		return this.updateEntity(inform);
	}

	@Override
	public boolean deleteInform(int id) {
		// TODO Auto-generated method stub
		return this.deleteEntity(id);
	}
}
