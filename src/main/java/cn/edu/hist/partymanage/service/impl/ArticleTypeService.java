package cn.edu.hist.partymanage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.hist.partymanage.base.BaseDao;
import cn.edu.hist.partymanage.entity.ArticleType;
import cn.edu.hist.partymanage.service.IArticleTypeService;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:13:17
* 类说明
*/
@Service
public class ArticleTypeService extends BaseDao<ArticleType> implements IArticleTypeService {

	@Override
	public List<ArticleType> getAll() {
		// TODO Auto-generated method stub
		return this.getEntityList("from ArticleType");
	}

	@Override
	public ArticleType getById(int id) {
		// TODO Auto-generated method stub
		return this.getEntity(id);
	}
	
	@Override
	public boolean updateArticleType(ArticleType articleType) {
		// TODO Auto-generated method stub
		return this.updateEntity(articleType);
	}

}
