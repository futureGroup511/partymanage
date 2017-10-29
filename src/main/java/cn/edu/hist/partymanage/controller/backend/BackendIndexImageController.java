package cn.edu.hist.partymanage.controller.backend;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.hist.partymanage.base.BaseController;
import cn.edu.hist.partymanage.entity.IndexImage;
import cn.edu.hist.partymanage.entity.User;

/*
*@Auther 宋民举
*@Email 860080937@qq.com
*@Date 2017年5月7日
*@Description :
*/
@Controller
@RequestMapping("/backend/indexImage/")
public class BackendIndexImageController extends BaseController{
	private final String viewPath = "backend/indexImage/";
	
	@RequestMapping("add")
	public ModelAndView add(){
		ModelAndView modelAndView = new ModelAndView(viewPath+"add");
		
		return modelAndView;
	}
	@RequestMapping(value="addDo",method=RequestMethod.POST)
	public ModelAndView addDo(
			@RequestParam(name="img",required=true)CommonsMultipartFile imgFile,
			@RequestParam(name="url",defaultValue="")String url,
			HttpServletRequest request
			){
		ModelAndView modelAndView = new ModelAndView("backend/remind");
		User user = (User)request.getSession().getAttribute("user");
		if(imgFile==null){
			return null;
		}
		String temp = imgFile.getOriginalFilename();
		int x =0;
		if(null !=temp && (x =temp.lastIndexOf("."))>0){
			temp=temp.substring(x);
		}else{
			return null;
		}
		String name = user.getId()+new Date().getTime()+temp;
		String path = request.getServletContext().getRealPath("/file/images/");
		File img = new File(path,name);
		try {
			imgFile.transferTo(img);
		}catch (Exception e) {
			// TODO: handle exception
		}
		IndexImage indexImage = new IndexImage();
		indexImage.setImgUrl("file/images/"+name);
		indexImage.setUrl(url);
		indexImage.setCreateTime(new Timestamp(new Date().getTime()));
		
		indexImageService.saveIndexImg(indexImage);
		modelAndView.addObject("remind","成功!");
		return modelAndView;
	}
	
	@RequestMapping("manage")
	public ModelAndView manage(){
		ModelAndView modelAndView = new ModelAndView(viewPath+"manage");
		modelAndView.addObject("indexImages",indexImageService.getNew(4));
		
		return modelAndView;
	}
	@RequestMapping("delete")
	public ModelAndView delete(int id){
		ModelAndView modelAndView = new ModelAndView("backend/remind");
		boolean b = indexImageService.deleteIndexImg(id);
		
		modelAndView.addObject(b?"remind":"warning",b?"删除成功!":"删除失败");
		return modelAndView;
	}
}
