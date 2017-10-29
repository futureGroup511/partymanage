package cn.edu.hist.partymanage.service;

import java.util.List;
import cn.edu.hist.partymanage.entity.Article;
import cn.edu.hist.partymanage.entity.User;
import cn.edu.hist.partymanage.util.PageCut;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:07:32
* 类说明
*/
public interface IArticleService {
	List<Article> getNew(int num,User user);
	List<Article> getHot(int num,User user);
	
	/**
	 * @author 丁赵雷
	 * @date 2017/5/10
	 * @Email 1524334009@qq.com
	 * @describe 根据文章的类别 搜索的关键字 用户的权限 进行分页查询
	 *           type 和 search 不可同时为空或同时不为空
	 * @param curpage 当前页
	 * @param pageSize 一页有几条记录
	 * @param type 文章的类型 （可为空）
	 * @param search 搜索的关键字 （可为空）
	 * @param user 用户的对象
	 * @return 返回文章的集合
	 */
	PageCut<Article> getArticles(int curpage , int pageSize , int type ,
			String search , User user);
	
	/**@author 丁赵雷
	 * @Email 1524334009@.qq
	 * @date 2017/5/11
	 * @param id 当前记录的id
	 * @param type 文章类型的id（可为空）
	 * @param search 关键词（可为空）
	 * @param upAndDown 上一篇下一篇的 标识符 false 代表上一篇 true 代表下一篇
	 * @param user 用户的对象
	 * @return 文章的集合 
	 * @describe  type和search 不可同时为空或同时不为空 这个方法是用来搜索
	 * 			  文章的上一篇下一篇
	 */
	List<Article> getUpAndDown(int id , int type , String search , boolean upAndDown , User user);
	boolean add(Article article);
	/**
	* @Auther 宋民举
	* @email 860080937@qq.com
	* @Date 2017年5月2日
	* @param id
	* @return
	* @decoration :根据id获得文章
	* @careful
	*/
	Article getById(int id);
	/**
	* @Auther 宋民举
	* @email 860080937@qq.com
	* @Date 2017年4月29日
	* @param type
	* @param title
	* @param userName
	* @param currentPage
	* @param pageSize
	* @return
	* @decoration :String参数为""或者null代表不做限制
	* @careful :type<1代表对类型不做限制
	*/
	PageCut<Article> search(int type,String title,String userName,int currentPage,int pageSize);
	
	
	/**
	 * @deprecated
	 * @param num
	 * @param id
	 * @param user
	 * @return
	 */
	List<Article> getNumById(int num,int id,User user);
	
	/**
	* @Auther 宋民举
	* @email 860080937@qq.com
	* @Date 2017年5月2日
	* @param article
	* @return
	* 
	* @decoration :更新文章
	* @careful
	*/
	boolean updateArticle(Article article);
	boolean deleteArticle(int id);
	
	/**
	 * @return
	 *@user song
	 *@date 2017年5月13日
	 *@todo 更新文章类型
	 */
	boolean updateType();
}
