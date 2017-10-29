package cn.edu.hist.partymanage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.hist.partymanage.base.BaseDao;
import cn.edu.hist.partymanage.entity.User;
import cn.edu.hist.partymanage.entity.WatchVideoLog;
import cn.edu.hist.partymanage.service.IWatchVideoLogService;
import cn.edu.hist.partymanage.util.PageCut;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月5日 下午2:55:32
* 类说明
*/
@Service
public class WatchVideoLogService extends BaseDao<WatchVideoLog> implements IWatchVideoLogService {

	@Override
	public WatchVideoLog getByVidUid(int videoId, int userId) {
		String hql = "from WatchVideoLog w where w.userId=" + userId + " and w.videoId=" + videoId;
		List<WatchVideoLog> list = this.getEntityList(hql);
		if (list.size() > 0) {
			return (WatchVideoLog) list.toArray()[0];
		} else {
			return null;
		}

	}

	@Override
	public boolean add(WatchVideoLog watchVideoLog) {
		return this.saveEntity(watchVideoLog);
	}

	@Override
	public boolean update(WatchVideoLog watchVideoLog) {
		return this.updateEntity(watchVideoLog);
	}

	@Override
	public PageCut<WatchVideoLog> getByPageCut(int currPage, int pageSize, String userName) {
		// TODO Auto-generated method stub

		StringBuilder sb = new StringBuilder("from WatchVideoLog as w");
		if (null != userName && !"".equals(userName)) {
			sb.append(String.format("  where w.userName '%%%s%%'", userName));
		}
		int count = this.getEntityNum("select count(*) " + sb.toString());
		// System.out.println(count);
		PageCut<WatchVideoLog> pc = new PageCut<>(currPage, pageSize, count);
		if (count > 0) {
			pc.setData(this.getEntityLimitList(sb.toString(), pageSize * (currPage - 1), pageSize));
		}
		return pc;
	}

	@Override
	public List<WatchVideoLog> searchByUserName(String userName) {
		// TODO Auto-generated method stub
		String hql = "from WatchVideoLog as w where w.userName = ?";
		return this.getEntityList(hql, userName);
	}

	@Override
	public List<WatchVideoLog> search(String userName, String videoName) {
		// TODO Auto-generated method stub
		String hql = "from WatchVideoLog as w where 1 = 1";
		int x =0;
		if (userName != null && userName.length() > 0) {
			hql += " and w.userName like ?";
			x++;
		}
		if (videoName != null && videoName.length() > 0) {
			hql += " and w.videoName like ?";
			x++;
			x++;
		}
		if(x==0){
			return null;
		}
		if(x == 1){
			return this.getEntityList(hql,"%"+ userName +"%");
		}
		if(x==2){
			return this.getEntityList(hql,"%"+ videoName +"%");
		}
		
		return this.getEntityList(hql,"%"+ userName +"%","%"+videoName+"%");
		
		
	}

}
