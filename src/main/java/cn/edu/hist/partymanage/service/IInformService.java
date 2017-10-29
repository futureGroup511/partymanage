package cn.edu.hist.partymanage.service;

import java.util.List;

import cn.edu.hist.partymanage.entity.Inform;
import cn.edu.hist.partymanage.entity.User;
import cn.edu.hist.partymanage.util.PageCut;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月5日 下午2:53:09
* 类说明
*/
public interface IInformService {

	/**
	 * @author 丁赵雷
	 * @email 1524334009@.com
	 * @Date 2017/5/4
	 * @param curPage 当前页
	 * @param pageSize 一页有几条记录
	 * @param search 搜索时的关键字（可为空）
	 * @param user对象 主要用他的 role 和 所属部门的id
	 * @decoration 按条件进行分页搜索
	 * @return 根据 用户的权限和 关键字（可为空）  所属部门来查询通知
	 */
	PageCut<Inform> getInforms(int curPage,int pageSize,String search ,User user);
	

	

	List<Inform> getNew(int num,User user);
	
	/**
	 * 
	 * @dete 2017/5/10
	 * @param id 通知的id
	 * @return 通知的对象
	 * @decoration 阅读通知
	 */
	Inform getBy(int id);
	boolean add(Inform inform);
	boolean updateInform(Inform inform);
	boolean deleteInform(int id);
	
	/**
	* @Auther 宋民举
	* @email 860080937@qq.com
	* @Date 2017年5月6日
	* @param title
	* @param userName
	* @param currentPage
	* @param pageSize
	* @return
	* @decoration :根据条件进行分页搜索
	* @careful
	*/
	PageCut<Inform> search(String title,String userName,int currentPage,int pageSize);
}
