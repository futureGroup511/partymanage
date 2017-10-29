package cn.edu.hist.partymanage.util;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {
	private static final String regEx_html="<[^>]+>";
	private static final Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
	

	private static final String regEx_findImg = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
	private static final Pattern p_img=Pattern.compile(regEx_findImg,Pattern.CASE_INSENSITIVE);
	public static String noHtml(String html) {
        Matcher m_html=p_html.matcher(html);
        return m_html.replaceAll("");
	}
	public static List<String> findImgSrc(String html){
		Matcher m_img = p_img.matcher(html);
		List<String> list = new LinkedList<String>();
		while(m_img.find()){
			String img = m_img.group().toLowerCase();
			try{
				int x = img.indexOf("src=");
				img=img.substring(x+5);
				x=img.indexOf("\"");
				img=img.substring(0, x);
				list.add(img);
			}catch(Exception e){
				continue;
			}
		}
		return list;
	}
	public static String findImgSrcOne(String html){
		Matcher m_img = p_img.matcher(html);
		if(m_img.find()){
			String img = m_img.group().toLowerCase();
			try{
				int x = img.indexOf("src=");
				img=img.substring(x+5);
				x=img.indexOf("\"");
				img=img.substring(0, x);
				if(img.length() ==0 ){
					return "";
				}
				return img;
			}catch(Exception e){
				return "";
			}
		}
		return null;
	}
	
}
