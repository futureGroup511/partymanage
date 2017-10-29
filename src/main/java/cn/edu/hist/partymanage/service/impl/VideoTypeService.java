package cn.edu.hist.partymanage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.hist.partymanage.base.BaseDao;
import cn.edu.hist.partymanage.entity.VideoType;
import cn.edu.hist.partymanage.service.IVideoTypeService;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:16:57
* 类说明
*/
@Service
public class VideoTypeService extends BaseDao<VideoType> implements IVideoTypeService {

	@Override
	public List<VideoType> getAll() {
		// TODO Auto-generated method stub
		return this.getEntityList("from VideoType");
	}

	@Override
	public VideoType getById(int id) {
		// TODO Auto-generated method stub
		return this.getEntity(id);
	}
	
	@Override
	public boolean updateVideoType(VideoType videoType) {
		// TODO Auto-generated method stub
		return this.updateEntity(videoType);
	}

}
