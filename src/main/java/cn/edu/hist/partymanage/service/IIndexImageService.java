package cn.edu.hist.partymanage.service;


import java.util.List;

import cn.edu.hist.partymanage.entity.IndexImage;

/** 
* @author 焦祥宇 
* @version 创建时间：2017年3月7日 下午5:12:23 
* @description 轮播图片 
*/
public interface IIndexImageService {
	List<IndexImage> getNew(int num);
	boolean saveIndexImg(IndexImage indexImage);
	boolean deleteIndexImg(int id);
}
