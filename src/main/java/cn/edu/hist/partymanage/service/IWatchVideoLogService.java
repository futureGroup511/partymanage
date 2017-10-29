package cn.edu.hist.partymanage.service;

import java.util.List;

import cn.edu.hist.partymanage.entity.WatchVideoLog;
import cn.edu.hist.partymanage.util.PageCut;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月5日 下午2:53:24
* 类说明
*/
public interface IWatchVideoLogService {
	//这个方法名...命名的真奇怪 ........
	public WatchVideoLog getByVidUid(int videoId,int userId);
	public boolean add(WatchVideoLog watchVideoLog);
	public boolean update(WatchVideoLog watchVideoLog);
	
	/**
	 * @param currPage
	 * @param pagesize
	 * @param userName ,可为null
	 * @return 
	 *@user song
	 *@date 2017年5月18日
	 *@todo TODO
	 */
	public PageCut<WatchVideoLog> getByPageCut(int currPage,int pageSize,String userName);
	public List<WatchVideoLog> searchByUserName(String userName);
	public List<WatchVideoLog> search(String userName,String videoName);
}
