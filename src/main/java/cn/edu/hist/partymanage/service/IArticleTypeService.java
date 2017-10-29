package cn.edu.hist.partymanage.service;
/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:08:10
* 类说明
*/

import java.util.List;

import cn.edu.hist.partymanage.entity.ArticleType;

public interface IArticleTypeService {
	List<ArticleType> getAll();
	ArticleType getById(int id);
	boolean updateArticleType(ArticleType articleType);
}
