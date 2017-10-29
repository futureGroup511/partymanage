package cn.edu.hist.partymanage.controller.backend;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.hist.partymanage.base.BaseController;
import cn.edu.hist.partymanage.entity.ExamLog;
import cn.edu.hist.partymanage.entity.QuestionType;

/*
@user song
@date 2017年5月21日
@todo TODO
*/

@Controller
@RequestMapping("/backend/questionType/")
public class BackendQuestionTypeController extends BaseController {
	private final String viewPath = "backend/questionType/";

	@RequestMapping("manage")
	public ModelAndView manage() {
		ModelAndView modelAndView = new ModelAndView(viewPath + "manage");
		List<QuestionType> types = questionTypeService.getAll();
		modelAndView.addObject("types", types);
		modelAndView.addObject("typesNum", types.size());
		return modelAndView;
	}

	@RequestMapping("addDo")
	public ModelAndView addDo(@RequestParam(name = "name", defaultValue = "") String name) {
		ModelAndView modelAndView = new ModelAndView(viewPath + "manage");
		if (!"".equals(name)) {
			QuestionType qType = new QuestionType();
			qType.setName(name);
			questionTypeService.add(qType);
			modelAndView.addObject("remind", "添加成功");
		} else {
			modelAndView.addObject("warning", "添加失败,请正确填写名字.");
		}

		List<QuestionType> types = questionTypeService.getAll();

		modelAndView.addObject("types", types);
		modelAndView.addObject("typesNum", types.size());
		return modelAndView;
	}

	@RequestMapping("changeDo")
	public ModelAndView changeDo(@RequestParam(name = "id", defaultValue = "0") int id,
			@RequestParam(name = "name", defaultValue = "") String name) {
		ModelAndView modelAndView = new ModelAndView(viewPath + "manage");
		if (id > 0 && (!"".equals(name))) {
			questionTypeService.update(id, name);
			modelAndView.addObject("remind", "修改成功!");
		}

		List<QuestionType> types = questionTypeService.getAll();
		modelAndView.addObject("types", types);
		modelAndView.addObject("typesNum", types.size());
		return modelAndView;
	}

	@RequestMapping("delete")
	public ModelAndView delete(@RequestParam(name = "id", defaultValue = "0") int id) {
		ModelAndView modelAndView = new ModelAndView(viewPath + "manage");
		if (id > 0) {
			questionTypeService.deleteById(id);
			modelAndView.addObject("remind", "删除成功!");
		}
		List<QuestionType> types = questionTypeService.getAll();
		modelAndView.addObject("types", types);
		modelAndView.addObject("typesNum", types.size());
		return modelAndView;
	}

	@RequestMapping("report")
	public ModelAndView report(@RequestParam(name = "typeId", defaultValue = "0") int typeId) {
		ModelAndView modelAndView = new ModelAndView(viewPath + "report");
		List<QuestionType> types = questionTypeService.getAll();
		List<ExamLog> list = new LinkedList<>();
		if (typeId > 0) {
			list = examLogService.getAll(typeId);
		} else {
			list = examLogService.getAll();
		}
		modelAndView.addObject("nowTypeId",typeId);
		modelAndView.addObject("types", types);
		modelAndView.addObject("examLogs", list);
		modelAndView.addObject("nums", list.size());
		return modelAndView;
	}

	@RequestMapping("deleteReport")
	public ModelAndView deleteReport(@RequestParam(name = "id", defaultValue = "0") int id) {
		ModelAndView modelAndView = new ModelAndView(viewPath + "report");
		if(id>0){
			examLogService.delete(id);
			modelAndView.addObject("remind","删除成功。");
		}else {
			modelAndView.addObject("warning","已经删除");
		}
		List<QuestionType> types = questionTypeService.getAll();
		List<ExamLog> list = examLogService.getAll();
		modelAndView.addObject("types", types);
		modelAndView.addObject("examLogs", list);
		modelAndView.addObject("nums", list.size());
		return modelAndView;
	}
}
