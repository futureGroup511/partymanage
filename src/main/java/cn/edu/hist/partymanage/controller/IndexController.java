package cn.edu.hist.partymanage.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.hist.partymanage.base.BaseController;
import cn.edu.hist.partymanage.entity.Role;
import cn.edu.hist.partymanage.entity.User;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年2月28日 下午6:10:13
* 类说明
*/
@Controller
@RequestMapping("/")
public class IndexController extends BaseController{
	
	@RequestMapping("index")
	public ModelAndView index(){
		
		this.logger.debug("do index");
		ModelAndView modelAndView =new ModelAndView("index");
	
	
		logger.info(modelAndView.toString());
		return modelAndView;
		
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(HttpSession session,HttpServletRequest request,String randStr,String account,String password) throws Exception {
		/**
		 * 这是用来校验验证码的
		 */				
		String vCode=(String)session.getAttribute("randStr");
		session.removeAttribute("randStr");
		//vCode=randStr="1111";
		if(randStr==null || !randStr.equals(vCode) ){
			request.setAttribute("codeMeg", "验证码错误");
			return "index";
		}else{
			User newUser=userService.getByPwd(account,password);
			if(newUser!=null){
				session.setAttribute("user", newUser);	
				session.setAttribute("userType", newUser.getType());//用户权限
				logger.info(newUser);
				logger.info("type"+newUser.getType());
				
				//此处我修改了一下 丁赵雷
				if(newUser.getType()== Role.OrganizationDepartment){//管理员（组织部）
					return "redirect:/backend/index";
				}else {//党委书记、党支部书记
					return "redirect:/party/toFront/toIndex";
				}
			}else {
				request.setAttribute("loginMeg", "用户名或密码错误！");
				return "index";
			}
		}
	}
	
	
	
	@RequestMapping(value = "toFront" , method = RequestMethod.GET)
	public String bToFront(HttpSession session){
		User user = (User) session.getAttribute("user");
		
		if(user != null && user.getType() != Role.OrganizationDepartment){
			return "redirect:/party/toFront/toIndex";
		}else {
			return "index";
		}
	}
	
	//退出登录	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("user") != null ) {
			session.removeAttribute("user");			
		}		
		return "redirect:/index";
	}
	
	
	@RequestMapping(value="validation",method=RequestMethod.GET)
	public String validation(HttpServletResponse response,HttpSession session) throws Exception {
		//设置响应头不要缓存页面
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", -1);
		
		
		//在内存中创建图像
		int width=60, height=20;
		BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		//获取画笔
		Graphics g=image.getGraphics();
		//设定背景色
		g.setColor(new Color(200,200,200));
		//填充区域
		g.fillRect(0, 0, width, height);
		//取随机产生的验证码4位数字
		Random rnd=new Random();
		int randNum=rnd.nextInt(8999)+1000;

		String randStr=String.valueOf(randNum);
		//将验证码存到session中
		session.setAttribute("randStr", randStr);
		//将验证码显示到图像中
		g.setColor(Color.black);
		g.setFont(new Font("",Font.PLAIN,20));
		g.drawString(randStr, 10, 17);
		//随机产生350个干扰点
		
		for(int i=0;i<350;i++){
			int y=rnd.nextInt(height);
			int x=rnd.nextInt(width);
			g.drawOval(x, y, 1, 1);
		}
		
		//输出到页面
		ImageIO.write(image, "JPEG", response.getOutputStream());
		//返回值必须为空
		return null;
	}


}
