package cn.edu.hist.partymanage.service;

import java.util.List;

import cn.edu.hist.partymanage.entity.User;
import cn.edu.hist.partymanage.entity.Video;
import cn.edu.hist.partymanage.util.PageCut;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:09:46
* 类说明
*/
public interface IVideoService {
	//推荐视频
	List<Video> getRecommend(int num,User user);
	List<Video> getNew(int num,User user);
	List<Video> getByType(int type, int num, User user);
	PageCut<Video> getPC(int page, int pageSize, User user);
	
	boolean saveVideo(Video video);
	boolean updateVideo(Video video);
	boolean deleteVideo(int id);
	
	Video getById(int id);
	public boolean updateWatchNumById(int id);
	/**
	 * @author 焦祥宇
	 * @describe 根据文章的类别 搜索的关键字 用户的权限 进行分页查询
	 *           type 和 search 不可同时为空或同时不为空
	 * @param curpage 当前页
	 * @param pageSize 一页有几条记录
	 * @param type 文章的类型 （可为空）
	 * @param search 搜索的关键字 （可为空）
	 * @param user 用户的对象
	 * @return 返回文章的集合
	 */
	PageCut<Video> getPC(int curpage , int pageSize , int type ,
			String search , User user);
	
	/**@author 焦祥宇
	 * @param id 当前记录的id
	 * @param type 文章类型的id（可为空）
	 * @param search 关键词（可为空）
	 * @param upAndDown 上一篇下一篇的 标识符 false 代表上一篇 true 代表下一篇
	 * @param user 用户的对象
	 * @return 文章的集合 
	 * @describe  type和search 不可同时为空或同时不为空 这个方法是用来搜索
	 * 			  文章的上一篇下一篇
	 */
	Video getUpAndDown(int id , int type , String search , boolean upAndDown , User user);
	
	/**
	* @Auther 宋民举
	* @email 860080937@qq.com
	* @Date 2017年5月6日
	* @param type
	* @param name 视频名字,包含匹配
	* @param currentPage
	* @param pageSize
	* @return
	* @decoration :根据条件获得视频分页
	* @careful
	*/
	PageCut<Video> search(int type,String name,int currentPage,int pageSize);
	/**
	 * @return
	 *@user song
	 *@date 2017年5月13日
	 *@todo 更新视频类型
	 */
	boolean updateType();
	
	PageCut<Video> getPC(int num, int page, String search,User user);
	
}
