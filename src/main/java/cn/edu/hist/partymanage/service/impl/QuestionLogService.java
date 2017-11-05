package cn.edu.hist.partymanage.service.impl;

import org.springframework.stereotype.Service;

import cn.edu.hist.partymanage.base.BaseDao;
import cn.edu.hist.partymanage.entity.QuestionLog;

//临时加的，不写接口了
@Service
public class QuestionLogService  extends BaseDao<QuestionLog> {
	public void addLog(QuestionLog ql) {
		this.saveEntity(ql);
	}
	public void delete(int userId,int questionTypeId) {
		this.executeHql("delete QuestionLog as ql where ql.userId = ? and ql.questionType = ?", userId,questionTypeId);
	}
}
