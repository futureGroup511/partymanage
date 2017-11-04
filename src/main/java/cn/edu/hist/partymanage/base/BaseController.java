package cn.edu.hist.partymanage.base;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import cn.edu.hist.partymanage.service.IArticleService;
import cn.edu.hist.partymanage.service.IArticleTypeService;
import cn.edu.hist.partymanage.service.IDepartmentService;
import cn.edu.hist.partymanage.service.IIndexImageService;
import cn.edu.hist.partymanage.service.IInformService;
import cn.edu.hist.partymanage.service.IPayLogService;
import cn.edu.hist.partymanage.service.IQuestionService;
import cn.edu.hist.partymanage.service.IQuestionTypeService;
import cn.edu.hist.partymanage.service.IUserService;
import cn.edu.hist.partymanage.service.IVideoService;
import cn.edu.hist.partymanage.service.IVideoTypeService;
import cn.edu.hist.partymanage.service.IWatchVideoLogService;
import cn.edu.hist.partymanage.service.impl.ExamLogService;
import cn.edu.hist.partymanage.service.impl.QuestionInTestService;
import cn.edu.hist.partymanage.service.impl.QuestionLogService;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年2月28日 下午6:10:03
* 类说明
*/
public class BaseController {
	
	protected Logger logger = null;
	
	public BaseController(){
		super();
		this.logger = Logger.getLogger(this.getClass());
		this.logger.info(this.getClass()+" is instance");
	}
	
	@Resource
	protected IArticleService articleService;
	
	@Resource
	protected IArticleTypeService articleTypeService;
	
	@Resource
	protected IDepartmentService departmentService;
	
	@Resource
	protected IQuestionService questionService;
	
	@Resource
	protected IQuestionTypeService questionTypeService;
	
	@Resource
	protected IUserService userService;
	
	@Resource
	protected IVideoService videoService;
	
	@Resource
	protected IVideoTypeService videoTypeService;
	
	@Resource
	protected IWatchVideoLogService watchVideoLogService;
	
	@Resource
	protected IInformService informService;
	
	@Resource
	protected IIndexImageService indexImageService;
	
	@Resource
	protected IPayLogService payLogService;
	
	@Resource
	protected QuestionLogService questionLogService;
	
	@Resource
	protected ExamLogService examLogService;
	
	@Resource
	protected QuestionInTestService questionInTestService;
}
