package cn.edu.hist.partymanage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.hist.partymanage.base.BaseDao;
import cn.edu.hist.partymanage.entity.Article;
import cn.edu.hist.partymanage.entity.ArticleType;
import cn.edu.hist.partymanage.entity.User;
import cn.edu.hist.partymanage.service.IArticleService;
import cn.edu.hist.partymanage.util.PageCut;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:12:34
* 类说明
*/
@Service
public class ArticleService extends BaseDao<Article> implements IArticleService {

	public List<Article> getNew(int num, User user) {
		String hql = "from Article a " + getCondition(user) + " order by a.id desc";
		return this.getEntityLimitList(hql, 0, num);
	}

	public List<Article> getHot(int num, User user) {
		String hql = "from Article a " + getCondition(user) + " order by a.readNum desc";
		return this.getEntityLimitList(hql, 0, num);
	}

	public List<Article> getNumById(int num, int id, User user) {
		String hql = "from Article a " + getCondition(user) + " order by a.id desc ";
		return this.getEntityLimitList(hql, 0, num);
	}

	@Override
	public boolean add(Article article) {
		// TODO Auto-generated method stub
		return this.saveEntity(article);
	}

	@Override
	public PageCut<Article> search(int type, String title, String userName, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("from Article as a where 1 = 1");
		if (type > 0) {
			sb.append(" and a.type = " + type);
		}
		if (title != null && title.length() > 0) {
			sb.append(String.format(" and a.title like '%%%s%%'", title));
		}
		if (userName != null && userName.length() > 0) {
			sb.append(String.format(" and a.userName like '%%%s%%'", userName));
		}
		int count = this.getEntityNum("select count(*) " + sb.toString());
		PageCut<Article> pc = new PageCut<>(currentPage, pageSize, count);
		if (count > 0) {
			pc.setData(this.getEntityLimitList(sb.toString(), pageSize * (currentPage - 1), pageSize));
		}
		return pc;
	}

	@Override
	public Article getById(int id) {
		// TODO Auto-generated method stub
		return this.getEntity(id);
	}

	@Override
	public boolean updateArticle(Article article) {
		// TODO Auto-generated method stub
		return this.updateEntity(article);
	}

	@Override
	public boolean deleteArticle(int id) {
		// TODO Auto-generated method stub
		return this.deleteEntity(id);
	}

	public String getCondition(User user) {
		return "where (a.department like '%#" + user.getOrganizationId() + "#%' or a.department like '%#"
				+ user.getPartyId() + "#%' or a.department like '%#" + user.getBranchId()
				+ "#%' or a.allDepartment=1) and (a.role like '%#" + user.getType() + "#%' or a.allRole=1)";
	}

	@Override
	public boolean updateType() {
		// TODO Auto-generated method stub
		ArticleType one = (ArticleType) this.getSession().get(ArticleType.class, 1);
		ArticleType two = (ArticleType) this.getSession().get(ArticleType.class, 2);
		ArticleType three = (ArticleType) this.getSession().get(ArticleType.class, 3);
		
		this.executeHql("update Article as a set a.typeName = ? where a.type =1 ",one.getName() );
		this.executeHql("update Article as a set a.typeName = ? where a.type =2 ",two.getName() );
		this.executeHql("update Article as a set a.typeName = ? where a.type =3 ",three.getName() );
		return true;
	}
	//丁赵雷
	@Override
	public PageCut<Article> getArticles(int curpage, int pageSize, int type, String search, User user) {
		if(type != 0 && (search != null && !"".equals(search))){
			logger.error("type和search这两个参数不能同时不为空");
			return null;
		}
		if(type == 0 && (search == null || "".equals(search))){
			logger.error("type和search这两个参数不能同时为空");
			return null;
		}
		PageCut<Article> pc = null;
		StringBuilder sb = new StringBuilder("from Article as a ");
		sb.append(this.getCondition(user));
		if(type != 0){
			sb.append(" and a.type = "+type);
			sb.append(" order by a.id desc ");

		}
		if(search != null && !"".equals(search)){
			sb.append(String.format(" and a.title like '%%%s%%' ", search));
			sb.append(" order by a.id desc");
		}
		
		int count = this.getEntityNum("select count(*) "+sb.toString());
		pc = new PageCut<>(curpage, pageSize, count);
		
		if(count>0){
			pc.setData(this.getEntityLimitList(sb.toString(), (curpage-1)*pageSize , 
					pageSize));
		}
		
		return pc;
	}

	
	//丁赵雷
	@Override
	public List<Article> getUpAndDown(int id, int type, String search, boolean upAndDown, User user) {
		if(type != 0 && (search != null && !"".equals(search))){
			logger.error("type和search这两个参数不能同时不为空");
			return new ArrayList<Article>();
		}
		if(type == 0 && (search == null || "".equals(search))){
			logger.error("type和search这两个参数不能同时为空");
			return new ArrayList<Article>();
		}
		StringBuilder sb = new StringBuilder("select * from tb_article as a ");
		sb.append(this.getCondition(user));
		if(type != 0){
			sb.append(" and a.type = "+type+" ");
			if(upAndDown){
				sb.append(" and a.id > "+id+" ");
				sb.append(" order by a.id asc limit 1");
			}else{
				sb.append(" and a.id < "+id+" ");
				sb.append(" order by a.id desc limit 1");
			}
			
			return this.executeSQLQuery(sb.toString());
		}else{
			sb.append(String.format(" and a.title like '%%%s%%'", search));
			if(upAndDown){
				sb.append(" and a.id > "+id+" ");
				sb.append(" order by a.id asc limit 1");
			}else{
				sb.append(" and a.id < "+id+" ");
				sb.append(" order by a.id desc limit 1");
			}
			
			return this.executeSQLQuery(sb.toString());
		}
	}
}
