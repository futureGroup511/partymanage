package cn.edu.hist.partymanage.service;
/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:09:23
* 类说明
*/

import java.util.List;

import cn.edu.hist.partymanage.entity.QuestionType;

public interface IQuestionTypeService {
	List<QuestionType> getAll();
	boolean deleteById(int id);
	QuestionType getById(int id);
	boolean add(QuestionType qt);
	boolean update(int id,String name);
}
