package cn.edu.hist.partymanage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.hist.partymanage.base.BaseDao;
import cn.edu.hist.partymanage.entity.QuestionInTest;

@Service
public class QuestionInTestService extends BaseDao<QuestionInTest>{
	
	public QuestionInTest get(int userId,int questionId,int questionTypeId) {
		List<QuestionInTest> list = getEntityList("from QuestionInTest as qit where qit.questionId = ? and qit.userId = ? and qit.questionTypeId = ?", questionId,userId,questionTypeId);
		if(list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	public List<QuestionInTest> get(int userId,int questionTypeId) {
		List<QuestionInTest> list = getEntityList("from QuestionInTest as qit where qit.userId = ? and qit.questionTypeId = ?",userId,questionTypeId);
		return list;
	}
	public void insert(QuestionInTest qit) {
		this.saveEntity(qit);
	}
	public void delete(int userId,int questionTypeId) {
		this.executeHql("delete QuestionInTest as qit where qit.userId = ? and qit.questionTypeId = ?",userId,questionTypeId);
	}

}
