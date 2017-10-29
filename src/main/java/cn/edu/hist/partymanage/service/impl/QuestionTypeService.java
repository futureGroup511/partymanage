package cn.edu.hist.partymanage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.hist.partymanage.base.BaseDao;
import cn.edu.hist.partymanage.entity.QuestionType;
import cn.edu.hist.partymanage.service.IQuestionTypeService;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:15:16
* 类说明
*/
@Service
public class QuestionTypeService extends BaseDao<QuestionType> implements IQuestionTypeService{

	@Override
	public List<QuestionType> getAll() {
		// TODO Auto-generated method stub
		
		return this.getEntityList("from QuestionType");
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return this.deleteEntity(id);
	}

	@Override
	public boolean add(QuestionType qt) {
		// TODO Auto-generated method stub
		return this.saveEntity(qt);
	}
	
	@Override
	public boolean update(int id, String name) {
		// TODO Auto-generated method stub
		this.updateEntity("update Question as q set q.typeName = ? where q.type = ?", name,id);
		this.updateEntity("update QuestionType as qt set qt.name = ? where id = ?", name,id);
		return true;
	}

	@Override
	public QuestionType getById(int id) {
		// TODO Auto-generated method stub
		return this.getEntity(id);
	}

}
