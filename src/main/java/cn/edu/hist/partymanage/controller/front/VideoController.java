/**        
 * @author: 焦祥宇 
 * @date:   createDate：2017年5月6日 下午8:25:26   
 * @Description:  
 * 
 */
package cn.edu.hist.partymanage.controller.front;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.hist.partymanage.base.BaseController;
import cn.edu.hist.partymanage.entity.IndexImage;
import cn.edu.hist.partymanage.entity.User;
import cn.edu.hist.partymanage.entity.Video;
import cn.edu.hist.partymanage.entity.WatchVideoLog;
import cn.edu.hist.partymanage.util.PageCut;
import cn.edu.hist.partymanage.util.SwitchTime;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/party/video")
public class VideoController extends BaseController {

	// 转到影音资料
	@RequestMapping(value = "/toVideoMaterials", method = RequestMethod.GET)
	public String toVideoMaterials(HttpServletRequest request,HttpSession session) {
		User user = (User) session.getAttribute("user");		
		// 获得轮播图片
		List<IndexImage> indexImages = indexImageService.getNew(4);
		request.setAttribute("indexImages", indexImages);

		List<Video> videos1 = videoService.getByType(1, 4, user);
		List<Video> videos2 = videoService.getByType(2, 4, user);
		List<Video> videos3 = videoService.getByType(3, 4, user);
		request.setAttribute("videos1", videos1);
		request.setAttribute("videos2", videos2);
		request.setAttribute("videos3", videos3);
		return "/front/videoMaterials";
	}
	
	// 影音资料列表
	@RequestMapping(value = "/videoList", method = RequestMethod.GET)
	public String toVideoList(
			@RequestParam(defaultValue = "1" , name = "curpage") int curpage,
			@RequestParam(defaultValue = "0" , name = "type" ) int type,
			@RequestParam(defaultValue = "" , name = "search") String search,
			HttpServletRequest request, HttpSession session) {
		
		User user = (User) session.getAttribute("user");				
		// 获得轮播图片
		List<IndexImage> indexImages = indexImageService.getNew(4);
		request.setAttribute("indexImages", indexImages);
		try {
			if (search!=null&&search.equals(new String(search.getBytes("iso8859-1"), "iso8859-1"))) {
				//判断是不是utf-8如果不是进行转码					
				try {
					search = new String(search.getBytes("iso8859-1"), "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		PageCut<Video> pc = videoService.getPC(curpage, 16,type, search, user);

		
		request.setAttribute("pc", pc);
		request.setAttribute("search", search);		
		request.setAttribute("type", type);	
		if (pc.getData().size() == 0) {
			request.setAttribute("NoVideo", "影音资料不存在！");
		}else{
			if("".equals(search)){
				request.setAttribute("title", ((Video)pc.getData().toArray()[0]).getTypeName());	
			}else{
				request.setAttribute("title", "搜索结果");
			}
		}		

		return "/front/videoList";
	}

	// 视频播放
	@RequestMapping(value = "/videoPlay", method = RequestMethod.GET)
	public String videoPlay(
			@RequestParam(name = "id") int id,
			@RequestParam(name = "search" , defaultValue = "") String search,
			@RequestParam(name = "type" , defaultValue = "0") int type,
			HttpServletRequest request,HttpServletResponse response , HttpSession session) {
		// 从路径获得视频id
		int videoId = Integer.valueOf(request.getParameter("id"));
		User user=(User)session.getAttribute("user");
		try {
			if (search!=null&&search.equals(new String(search.getBytes("iso8859-1"), "iso8859-1"))) {
				//判断是不是utf-8如果不是进行转码
				try {
					search= new String(search.getBytes("iso8859-1"),"utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int userId = user.getId();
		// 播放视频
		Video video = videoService.getById(videoId);
		if (!video.getPath().startsWith("http")) {
			// 获得视频观看记录
			WatchVideoLog watchVideoLog = watchVideoLogService.getByVidUid(videoId, userId);
			if (watchVideoLog != null)
				request.setAttribute("currentTime", watchVideoLog.getWatchSecond());
			// 视频浏览次数加一
			videoService.updateWatchNumById(videoId);

			request.setAttribute("video", video);
						
			// 查询上一个 下一个
			Video pre = videoService.getUpAndDown(id, type, search, true, user);
			Video next = videoService.getUpAndDown(id, type, search, false, user);
			
			request.setAttribute("next", next);							
			request.setAttribute("prev", pre);
			request.setAttribute("search", search);		
			request.setAttribute("type", type);	
			return "/front/videoPlay";

		} else {
			String strVar = "";
			strVar += "<script language=\"javascript\">";
			strVar += "window.top.location.href=\"" + video.getPath() + "\";";
			strVar += "</script>";
			try {
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8"); // 转码
				response.getWriter().println(strVar);
			} catch (Exception e) {			
				e.printStackTrace();
			}			
			return null;
		}
		
	}
	
	/**
	 * 更新党员的学习时间和视频播放记录历史 
	 */
	@RequestMapping(value = "/updateLearnTime", method = RequestMethod.POST)
	public void updateLearnTime(HttpServletRequest request,HttpSession session) {
		int userId = ((User)session.getAttribute("user")).getId();// 用户id
		User user = userService.getById(userId);
		long time = Integer.parseInt(request.getParameter("time"))/1000;		
		time = time + user.getStudySecond();
		String strTime = SwitchTime.switchTime(time);		
		user.setStudySecond(time);				
		user.setStudyTime(strTime);
		userService.updateUser(user);
		
		// 视频播放记录历史		
		String vt = request.getParameter("currentTime");
		long currentTime = 0;
		if (vt.indexOf(".") > 0) {
			currentTime = Integer.valueOf(vt.substring(0, vt.indexOf(".")));
		} else {
			currentTime = Integer.valueOf(vt);
		}
		int videoId = Integer.valueOf(request.getParameter("videoId"));
		WatchVideoLog watchVideoLog = watchVideoLogService.getByVidUid(videoId, userId);

		if (watchVideoLog == null) {
			watchVideoLog = new WatchVideoLog(userId,videoId,videoService.getById(videoId).getName(),user.getName(),currentTime);			
			watchVideoLogService.add(watchVideoLog);
		} else {		
			watchVideoLog.setWatchSecond(currentTime);
			watchVideoLogService.update(watchVideoLog);
		}		
	}
	

}
