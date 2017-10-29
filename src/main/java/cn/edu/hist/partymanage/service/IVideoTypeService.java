package cn.edu.hist.partymanage.service;
/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:10:48
* 类说明
*/

import java.util.List;

import cn.edu.hist.partymanage.entity.VideoType;

public interface IVideoTypeService {
	
	List<VideoType> getAll();
	VideoType getById(int id);
	boolean updateVideoType(VideoType videoType);

}
