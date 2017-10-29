package cn.edu.hist.partymanage.service;

import java.util.List;

import cn.edu.hist.partymanage.entity.Question;
import cn.edu.hist.partymanage.util.PageCut;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:09:03
* 类说明
*/
public interface IQuestionService {
	//随机获得num条数据
	public List<Question> getRandom(int num,int typeId);
	public Question getById(int id);
	
	/**
	 * @param question
	 *@user song
	 *@date 2017年5月21日
	 *@todo TODO
	 */
	void addQuestion(Question question);
	boolean updateQuestion(Question question);
	boolean deleteQuestion(int id);
	
	/**
	 * @param page
	 * @param pagesize
	 * @param title
	 * @return
	 *@user song
	 *@date 2017年5月21日
	 *@todo TODO获得分页
	 */
	PageCut<Question> getPageCut(int page,int pagesize);
	/**
	 * @param title
	 * @return
	 *@user song
	 *@date 2017年5月21日
	 *@todo TODO搜索
	 */
	List<Question> getSearch(String title);
}
