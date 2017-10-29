package cn.edu.hist.partymanage.controller.backend;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.hist.partymanage.base.BaseController;
import cn.edu.hist.partymanage.entity.User;
import cn.edu.hist.partymanage.entity.Video;
import cn.edu.hist.partymanage.entity.VideoType;
import cn.edu.hist.partymanage.util.FileUtils;

/*
*@Auther 宋民举
*@Email 860080937@qq.com
*@Date 2017年4月29日
*@Description :
*/
@RequestMapping("/backend/video/")
@Controller
public class BackendVideoController extends BaseController{
	private String viewPath = "/backend/video/";
	@RequestMapping("add")
	public ModelAndView add(){
		ModelAndView modelAndView = new ModelAndView(this.viewPath+"add");
		modelAndView.addObject("videoTypes",videoTypeService.getAll());
		modelAndView.addObject("deppartments",departmentService.getAll());
		return modelAndView;
	}
	@RequestMapping("addDo")
	public ModelAndView addDo(
			@RequestParam(name="videoFile",required=false)CommonsMultipartFile videoFile,
			@RequestParam(name="videoImg",required=true)CommonsMultipartFile videoFileImg,
			@RequestParam(name="videoUrl",required=false)String videoUrl,
			Video video,
			HttpSession session,
			int[] canSeeDep,
			int[] canSeeUser
			){
		ModelAndView modelAndView = new ModelAndView(viewPath + "add");
		modelAndView.addObject("videoTypes",videoTypeService.getAll());
		modelAndView.addObject("deppartments",departmentService.getAll());
		logger.info(video);
		StringBuilder dep = new StringBuilder();
		for(int x:canSeeDep){
			if (x==0) {
				video.setAllDepartment(true);
			}
			dep.append("#"+x);
		}
		dep.append("#");
		video.setDepartment(dep.toString());
		dep=null;
		StringBuilder role = new StringBuilder();
		for(int x:canSeeUser){
			if (x==0) {
				video.setAllRole(true);
			}
			role.append("#"+x);
		}
		role.append("#");
		video.setRole(role.toString());
		video.setTypeName(videoTypeService.getById(video.getType()).getName());
		
		video.setCreateTime(new Timestamp(new Date().getTime()));
		String fileName = "外部视频";
		if(videoUrl==null || "".equals(videoUrl)){
			if(videoFile==null || videoFile.isEmpty()){
				modelAndView.addObject("warning","请上传视频文件或者填写外部视频链接!");
				return modelAndView;
			}
			String videoName = videoFile.getOriginalFilename().toLowerCase();
			if(!videoName.endsWith(".mp4")){
				return modelAndView.addObject("warning","失败!请上传mp4文件!");
			}
			video.setName(videoName);
			User user = (User)session.getAttribute("user");
			String temp1 = user.getId() +""+ new Date().getTime();
			fileName =temp1 +".mp4";
			String imgName = fileName +".jpg";
			File file = new File(session.getServletContext().getRealPath("/file/"),fileName);
			File img = new File(session.getServletContext().getRealPath("/file/"),imgName);
			//C:\Users\song\workspace\partymanage\src\main\webapp\file
			if(!file.exists()){
				try {
					file.createNewFile();
					img.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			InputStream in;
			InputStream in2;
			try {
				in = videoFile.getFileItem().getInputStream();
				in2=videoFileImg.getFileItem().getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return modelAndView.addObject("warning","失败!解析文件错误!");
			}
			boolean b = FileUtils.copyFile(in, file);
			boolean b1 = FileUtils.copyFile(in2, img);
			if(!(b&&b1)){
				return modelAndView.addObject("warning","失败!解析文件错误!");
			}
		}else{
			if(!videoUrl.startsWith("http")){
				modelAndView.addObject("warning","失败,请正确填写视频链接地址!");
				return modelAndView;
			}
			video.setPath(videoUrl);
			videoService.saveVideo(video);
			modelAndView.addObject("remind","成功!");
			return modelAndView;
		}
		
		video.setPath(fileName);
		videoService.saveVideo(video);
		modelAndView.addObject("remind","成功!");
		return modelAndView;
	}
	
	@RequestMapping(value="manage")
	public ModelAndView manageSearch(
			@RequestParam(name="type",defaultValue="0") int type,
			@RequestParam(name="name",defaultValue="")String name,
			@RequestParam(name="currPage",defaultValue="1") int currPage){
		
		if(currPage < 1){
			currPage = 1;
		}
		ModelAndView modelAndView = new ModelAndView(viewPath+"manage");
		modelAndView.addObject("types",videoTypeService.getAll());
		modelAndView.addObject("type", type);
		modelAndView.addObject("name",name);
		
		modelAndView.addObject("pc",videoService.search(type, name, currPage, 10));
		return modelAndView;
	}
	
	@RequestMapping(value="change",method=RequestMethod.GET)
	public ModelAndView change(@RequestParam(name="id",defaultValue="0")int id){
		if(id<1){
			return null;
		}
		ModelAndView modelAndView = new ModelAndView(viewPath+"change");
		modelAndView.addObject("videoTypes",videoTypeService.getAll());
		modelAndView.addObject("deppartments",departmentService.getAll());
		modelAndView.addObject("video",videoService.getById(id));
		return modelAndView;
	}
	
	@RequestMapping(value="changeDo",method=RequestMethod.POST)
	public ModelAndView changePost(
			int id,
			int type,
			HttpSession session,
			int[] canSeeDep,
			int[] canSeeUser){
		ModelAndView modelAndView = new ModelAndView("/backend/remind");
		Video video = videoService.getById(id);
		if(type == 0 || video == null){
			return null;
		}
		StringBuilder dep = new StringBuilder();
		for(int x:canSeeDep){
			if (x==0) {
				video.setAllDepartment(true);
			}
			dep.append("#"+x);
		}
		dep.append("#");
		video.setDepartment(dep.toString());
		
		StringBuilder role = new StringBuilder();
		for(int x:canSeeUser){
			if (x==0) {
				video.setAllRole(true);
			}
			role.append("#"+x);
		}
		role.append("#");
		video.setRole(role.toString());
		
		if(type != video.getType()){
			video.setType(type);
			video.setTypeName(videoTypeService.getById(type).getName());
		}
		videoService.updateVideo(video);
		modelAndView.addObject("remind","修改成功!");
		return modelAndView;
	}
	
	@RequestMapping(value="delete",method=RequestMethod.GET)
	public ModelAndView delete(@RequestParam(name="id",defaultValue="0")int id,HttpServletRequest request){
		
		//这个删除没有做权限验证,反正学校那帮书记们也不会乱鼓捣,差不多得了
		
		ModelAndView modelAndView = new ModelAndView("backend/remind");
		if(id<1){
			modelAndView.addObject("remind","找不到这文章,无法删除啊!");
			return modelAndView;
		}
		User user = (User) request.getSession().getAttribute("user");
		if(user==null || user.getType()>3){
			return modelAndView.addObject("remind","你的权限不够,无法删除.");
		}
		videoService.deleteVideo(id);
		return modelAndView.addObject("remind","删除成功!");
	}
	
	@RequestMapping(value="typeManage",method=RequestMethod.GET)
	public ModelAndView typeManage(){
		ModelAndView modelAndView = new ModelAndView(viewPath+"typeManage");
		modelAndView.addObject("one",videoTypeService.getById(1))
		.addObject("two",videoTypeService.getById(2))
		.addObject("three",videoTypeService.getById(3));
		
		return modelAndView;
	}
	
	@RequestMapping(value="typeManageDo",method=RequestMethod.POST)
	public ModelAndView typeManageDo(
			@RequestParam(name="one",defaultValue="未知")String one,
			@RequestParam(name="two",defaultValue="未知")String two,
			@RequestParam(name="three",defaultValue="未知")String three
			){
		ModelAndView modelAndView = new ModelAndView(viewPath+"typeManage");
		if(one.length()!=4 || two.length()!=4 || three.length()!=4){
			modelAndView.addObject("warning","类型长度必须为4!");
			
			return modelAndView;
		}
		
		
		VideoType oneType = videoTypeService.getById(1);
		VideoType twoType = videoTypeService.getById(2);
		VideoType threeType = videoTypeService.getById(3);
		
		oneType.setName(one);
		twoType.setName(two);
		threeType.setName(three);
		
		videoTypeService.updateVideoType(oneType);
		videoTypeService.updateVideoType(twoType);
		videoTypeService.updateVideoType(threeType);
		
		modelAndView.addObject("one",oneType)
		.addObject("two",twoType)
		.addObject("three",threeType);
		videoService.updateType();
		modelAndView.addObject("remind","修改成功!");
		
		return modelAndView;
	}
}
