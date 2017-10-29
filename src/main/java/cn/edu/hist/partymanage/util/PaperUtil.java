package cn.edu.hist.partymanage.util;


import java.util.ArrayList;
import java.util.List;
import cn.edu.hist.partymanage.entity.Article;

/**
 * 文章的工具类
 * @author 丁赵雷
 *
 */
public class PaperUtil {
	
	/**
	 * 控制文章标题的长度
	 * @param titleLength 处理后的文章标题长度
	 * @param paperList
	 * @return PaperList
	 */
	
	public static List<Article> titleLength(List<Article> paperList,int titleLength){
		String t="";
		
		List<Article> list=new ArrayList<Article>();
		
		for (Article article : paperList) {
			
			if(article.getTitle().length()>titleLength){
				t=article.getTitle().substring(0, titleLength-2)+"...";
				article.setTitle(t);
				
				list.add(article);
			}else{
				list.add(article);
			}
		}
		
		return list;
	}
}
