package cn.edu.hist.partymanage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.hist.partymanage.base.BaseDao;
import cn.edu.hist.partymanage.entity.IndexImage;
import cn.edu.hist.partymanage.service.IIndexImageService;

/** 
* @author 焦祥宇 
* @version 创建时间：2017年3月7日 下午5:17:07 
* @description  
*/
@Service
public class IndexImageService  extends BaseDao<IndexImage> implements IIndexImageService {

	public List<IndexImage> getNew(int num) {
		
		return this.getEntityLimitList("from IndexImage order by id desc", 0, num);
	}

	@Override
	public boolean saveIndexImg(IndexImage indexImage) {
		// TODO Auto-generated method stub
		return this.saveEntity(indexImage);
	}

	@Override
	public boolean deleteIndexImg(int id) {
		// TODO Auto-generated method stub
		return this.deleteEntity(id);
	}
}
