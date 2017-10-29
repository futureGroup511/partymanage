package cn.edu.hist.partymanage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.hist.partymanage.base.BaseDao;
import cn.edu.hist.partymanage.entity.Question;
import cn.edu.hist.partymanage.service.IQuestionService;
import cn.edu.hist.partymanage.util.PageCut;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:14:36
* 类说明
*/
@Service("questionService")
public class QuestionService extends BaseDao<Question> implements IQuestionService{

	@Override
	public List<Question> getRandom(int num,int typeId) {
		String sql=null;
		if(typeId==0){
			sql="SELECT * FROM tb_question  ORDER BY RAND() LIMIT " +num;
		}else{
			sql="SELECT * FROM tb_question where type="+typeId+"  ORDER BY RAND() LIMIT " +num;
		}
		
		return this.executeSQLQuery(sql);
	}
	
	@Override
	public Question getById(int id) {
		String hql=" from Question q where q.id="+id;		 
		return (Question)this.uniqueResult(hql);
	}
	
	@Override
	public void addQuestion(Question question) {
		// TODO Auto-generated method stub
		this.saveEntity(question);
	}
	
	@Override
	public PageCut<Question> getPageCut(int page, int pagesize) {
		// TODO Auto-generated method stub
		String hql = "from Question";
		
		//System.out.println(sb.toString());
		int count = this.getEntityNum("select count(*) "+hql);
		//System.out.println(count);
		PageCut<Question> pc = new PageCut<>(page,pagesize,count);
		if(count>0){
			pc.setData(this.getEntityLimitList(hql,pagesize*(page-1), pagesize));
		}
		return pc;
	}
	
	@Override
	public List<Question> getSearch(String title) {
		// TODO Auto-generated method stub
		return this.getEntityList("from Question as q where q.title like ?", "%"+title+"%");
	}
	
	@Override
	public boolean updateQuestion(Question question) {
		// TODO Auto-generated method stub
		return this.updateEntity(question);
	}
	
	@Override
	public boolean deleteQuestion(int id) {
		// TODO Auto-generated method stub
		return this.deleteEntity(id);
	}
}
