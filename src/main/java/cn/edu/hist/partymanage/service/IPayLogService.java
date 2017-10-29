package cn.edu.hist.partymanage.service;

import cn.edu.hist.partymanage.entity.Article;
import cn.edu.hist.partymanage.entity.PayLog;
import cn.edu.hist.partymanage.util.PageCut;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:56:22
* 类说明
*/
public interface IPayLogService {
	boolean savePayLog(PayLog payLog);
	
	/**
	* @Auther 宋民举
	* @email 860080937@qq.com
	* @Date 2017年5月7日
	* @param userName
	* @param currentPage
	* @param pageSize
	* @return
	* @decoration :根据名字查找记录并分页,名字可为null或者""
	* @careful
	*/
	PageCut<PayLog> search(String userName,int currentPage,int pageSize);
	//同上,限制部门
	PageCut<PayLog> search(int department,String userName,int currentPage,int pageSize);
	PageCut<PayLog> getPc(int userId,int currentPage,int pageSize);
}
