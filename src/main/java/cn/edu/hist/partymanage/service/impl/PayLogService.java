package cn.edu.hist.partymanage.service.impl;

import org.springframework.stereotype.Service;

import cn.edu.hist.partymanage.base.BaseDao;
import cn.edu.hist.partymanage.entity.Article;
import cn.edu.hist.partymanage.entity.PayLog;
import cn.edu.hist.partymanage.service.IPayLogService;
import cn.edu.hist.partymanage.util.PageCut;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:56:42
* 类说明
*/
@Service
public class PayLogService extends BaseDao<PayLog> implements IPayLogService {
	@Override
	public boolean savePayLog(PayLog payLog) {
		// TODO Auto-generated method stub
		return this.saveEntity(payLog);
	}

	@Override
	public PageCut<PayLog> search(String userName, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("from PayLog as a where 1 = 1");
		
		if (userName != null && userName.length() > 0) {
			sb.append(String.format(" and a.userName like '%%%s%%'", userName));
		}
		int count = this.getEntityNum("select count(*) " + sb.toString());
		PageCut<PayLog> pc = new PageCut<>(currentPage, pageSize, count);
		if (count > 0) {
			pc.setData(this.getEntityLimitList(sb.toString(), pageSize * (currentPage - 1), pageSize));
		}
		return pc;
	}
	
	@Override
	public PageCut<PayLog> search(int department, String userName, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
StringBuilder sb = new StringBuilder("from PayLog as a where a.departmentId = "+department);
		
		if (userName != null && userName.length() > 0) {
			sb.append(String.format(" and a.userName like '%%%s%%'", userName));
		}
		int count = this.getEntityNum("select count(*) " + sb.toString());
		PageCut<PayLog> pc = new PageCut<>(currentPage, pageSize, count);
		if (count > 0) {
			pc.setData(this.getEntityLimitList(sb.toString(), pageSize * (currentPage - 1), pageSize));
		}
		return pc;
	}
	
	@Override
	public PageCut<PayLog> getPc(int userId, int currentPage, int pageSize) {
		StringBuilder sb = new StringBuilder("from PayLog as p where p.userId = "+userId+" order by p.id desc");						
		int count = this.getEntityNum("select count(*) " + sb.toString());
		PageCut<PayLog> pc = new PageCut<>(currentPage, pageSize, count);
		if (count > 0) {
			pc.setData(this.getEntityLimitList(sb.toString(), pageSize * (currentPage - 1), pageSize));
		}
		return pc;
	}
	
}
