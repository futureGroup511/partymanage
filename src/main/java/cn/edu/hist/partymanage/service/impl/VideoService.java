package cn.edu.hist.partymanage.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import cn.edu.hist.partymanage.base.BaseDao;
import cn.edu.hist.partymanage.entity.User;
import cn.edu.hist.partymanage.entity.Video;
import cn.edu.hist.partymanage.entity.VideoType;
import cn.edu.hist.partymanage.service.IVideoService;
import cn.edu.hist.partymanage.util.PageCut;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:16:26
* 类说明
*/
@Service
public class VideoService extends BaseDao<Video> implements IVideoService {

	public List<Video> getRecommend(int num, User user) {
		String hql = "from Video v " + getCondition(user) + " order by v.watchNum desc";
		return this.getEntityLimitList(hql, 0, num);
	}

	public List<Video> getNew(int num, User user) {
		String hql = "from Video v " + getCondition(user) + " order by v.id desc";
		return this.getEntityLimitList(hql, 0, num);
	}

	public PageCut<Video> getPC(int page, int pageSize, User user) {
		String hql = "select count(*) from Video v "+getCondition(user);
		int count = ((Long) this.uniqueResult(hql)).intValue();
		PageCut<Video> pc = new PageCut<Video>(page, pageSize, count);
		pc.setData(this.getEntityLimitList("from Video v "+getCondition(user), (page-1)*pageSize, pageSize));
		return pc;
	}
	
	@SuppressWarnings("unchecked")
	public PageCut<Video> getPC(int page, int pageSize, String search, User user) {
		if(search==null || search.length()==0){
			return this.getPC(page, pageSize, user);
		}
		
		String hql = "select count(*) from Video as v "+getCondition(user)+" and v.name like :search";
		
		Query query=this.getSession().createQuery(hql);
		
		String format=String.format("%%%s%%", search);
		query.setString("search",format);
		int count = ((Long)query.uniqueResult()).intValue();
		
		PageCut<Video> pc = new PageCut<Video>(page, pageSize, count);
		hql="from Video as v "+getCondition(user)+" and v.name like :search";
		query=this.getSession().createQuery(hql);
		query.setString("search", format);
		query.setFirstResult((page-1)*pageSize);
		query.setMaxResults(pageSize);
		pc.setData(query.list());
		return pc;
	}
	
	

	@Override
	public boolean saveVideo(Video video) {
		// TODO Auto-generated method stub
		return this.saveEntity(video);
	}

	@Override
	public boolean updateVideo(Video video) {
		// TODO Auto-generated method stub
		return this.updateEntity(video);
	}

	@Override
	public PageCut<Video> search(int type, String name, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("from Video as v where 1 = 1");
		if(type>0){
			sb.append(" and v.type = "+type);
		}
		if(name!=null && name.length()>0){
			sb.append(String.format(" and v.name like '%%%s%%'", name));
		}

		int count = this.getEntityNum("select count(*) "+sb.toString());
		PageCut<Video> pc = new PageCut<>(currentPage,pageSize,count);
		if(count>0){
			pc.setData(this.getEntityLimitList(sb.toString(),pageSize*(currentPage-1), pageSize));
		}
		return pc;
	}

	@Override
	public Video getById(int id) {
		return this.getEntity(id);
	}

	@Override
	public boolean deleteVideo(int id) {
		// TODO Auto-generated method stub
		return this.deleteEntity(id);
	}

	
	@Override
	public boolean updateWatchNumById(int id) {
		Video video=this.getEntity(id);
		video.setWatchNum(video.getWatchNum()+1);
		return this.updateEntity(video);
	}
		
	@Override
	public List<Video> getByType(int type, int num, User user) {
		String sql="select * from tb_video  "+getConditionForSql(user)+"and type="+type+" order by id desc limit "+num;
		return executeSQLQuery(sql);
	}

	
	public String getCondition(User user) {
		return "where (v.department like '%#" + user.getOrganizationId()
				+ "#%' or v.department like '%#" + user.getPartyId() + "#%' or v.department like '%#"
				+ user.getBranchId() + "#%' or v.allDepartment=1) and (v.role like '%#" + user.getType()
				+ "#%' or v.allRole=1)";
	}
	
	public String getConditionForSql(User user) {
		return "where (department like '%#" + user.getOrganizationId()
				+ "#%' or department like '%#" + user.getPartyId() + "#%' or department like '%#"
				+ user.getBranchId() + "#%' or allDepartment=1) and (role like '%#" + user.getType()
				+ "#%' or allRole=1)";
	}

	@Override
	public boolean updateType() {
		// TODO Auto-generated method stub
		VideoType one = (VideoType) this.getSession().get(VideoType.class, 1);
		VideoType two = (VideoType) this.getSession().get(VideoType.class, 2);
		VideoType three = (VideoType) this.getSession().get(VideoType.class, 3);
		
		this.executeHql("update Video as v set v.typeName = ? where v.type =1 ",one.getName() );
		this.executeHql("update Video as v set v.typeName = ? where v.type =2 ",two.getName() );
		this.executeHql("update Video as v set v.typeName = ? where v.type =3 ",three.getName() );
		return true;
	}
	
	@Override
	public PageCut<Video> getPC(int curpage, int pageSize, int type, String search, User user) {
		if(type != 0 && (search != null && !"".equals(search))){
			logger.error("type和search这两个参数不能同时不为空");
			return null;
		}
		if(type == 0 && (search == null || "".equals(search))){
			logger.error("type和search这两个参数不能同时为空");
			return null;
		}
		PageCut<Video> pc = null;
		StringBuilder sb = new StringBuilder("from Video as v ");
		sb.append(this.getCondition(user));
		if(type != 0){
			sb.append(" and v.type = "+type);
			sb.append(" order by v.id desc ");

		}
		if(search != null && !"".equals(search)){
			sb.append(String.format(" and v.name like '%%%s%%' ", search));
			sb.append(" order by v.id desc");
		}
		
		int count = this.getEntityNum("select count(*) "+sb.toString());
		pc = new PageCut<>(curpage, pageSize, count);
		
		if(count>0){
			pc.setData(this.getEntityLimitList(sb.toString(), (curpage-1)*pageSize , 
					pageSize));
		}
		
		return pc;
	}

	@Override
	public Video getUpAndDown(int id, int type, String search, boolean upAndDown, User user) {
		if(type != 0 && (search != null && !"".equals(search))){
			logger.error("type和search这两个参数不能同时不为空");
			return null;
		}
		if(type == 0 && (search == null || "".equals(search))){
			logger.error("type和search这两个参数不能同时为空");
			return null;
		}
		StringBuilder sb = new StringBuilder("select * from tb_video as v ");
		sb.append(this.getCondition(user));
		if(type != 0){
			sb.append(" and v.type = "+type+" ");
			if(upAndDown){
				sb.append(" and v.id > "+id+" ");
				sb.append(" order by v.id asc limit 1");
			}else{
				sb.append(" and v.id < "+id+" ");
				sb.append(" order by v.id desc limit 1");
			}
			List<Video> list=this.executeSQLQuery(sb.toString());
			if(list.size()>0){
				return (Video)list.toArray()[0];
			}else{
				return null;
			}
			
		}else{
			sb.append(String.format(" and v.name like '%%%s%%'", search));
			if(upAndDown){
				sb.append(" and v.id > "+id+" ");
				sb.append(" order by v.id asc limit 1");
			}else{
				sb.append(" and v.id < "+id+" ");
				sb.append(" order by v.id desc limit 1");
			}			
			List<Video> list=this.executeSQLQuery(sb.toString());
			if(list.size()>0){
				return (Video)list.toArray()[0];
			}else{
				return null;
			}
		}
	}	
}
