package cn.edu.hist.partymanage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.hist.partymanage.base.BaseDao;
import cn.edu.hist.partymanage.entity.ExamLog;

@Service
public class ExamLogService extends BaseDao<ExamLog> {
	public void addLog(ExamLog el) {
		this.saveEntity(el);
	}
	
	public ExamLog getByUserAndQuestionType(int userId,int typeId) {
		String hql="from ExamLog as el where el.userId = ? and el.questionTypeId = ?";
		List<ExamLog> list = this.getEntityList(hql, userId,typeId);
		if(list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
		
	}
	public List<ExamLog> getAll(int typeId){
		String hql = "from ExamLog as el where el.questionTypeId = ? order by el.userScore desc";
		List<ExamLog> list = this.getEntityList(hql,typeId);
		return list;
	}
	public List<ExamLog> getAll(){
		String hql = "from ExamLog as el order by el.userScore desc";
		List<ExamLog> list = this.getEntityList(hql);
		return list;
	}
	public void delete(int id) {
		try {
			this.deleteEntity(id);
		}catch(Exception e) {
			
		}
		
	}
}
