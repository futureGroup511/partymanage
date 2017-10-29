package cn.edu.hist.partymanage.controller.backend;

import java.io.IOException;
import java.lang.reflect.Executable;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.hist.partymanage.base.BaseController;
import cn.edu.hist.partymanage.entity.Question;
import cn.edu.hist.partymanage.util.PageCut;

/*
@user song
@date 2017年5月21日
@todo TODO
*/
@Controller
@RequestMapping("/backend/question/")
public class BackendQuestionController extends BaseController{
	
	private final String viewPath = "backend/question/";

	@RequestMapping("add")
	public ModelAndView add(){
		ModelAndView modelAndView = new ModelAndView(viewPath + "add");
		modelAndView.addObject("types",questionTypeService.getAll());
		return modelAndView;
	}

	@RequestMapping("addByPage")
	public ModelAndView addByPage(
			Question question
			){
		ModelAndView modelAndView = new ModelAndView(viewPath+"add");
		question.setTypeName(questionTypeService.getById(question.getType()).getName());
		
		questionService.addQuestion(question);
		logger.debug(question);
		modelAndView.addObject("remind","添加成功!");
		modelAndView.addObject("types",questionTypeService.getAll());
		return modelAndView;
	}

	
	@RequestMapping(value="addByExcel",method=RequestMethod.POST)
	public ModelAndView addByExcel(
			@RequestParam(name="excelFile",required=false)CommonsMultipartFile excelFile,
			@RequestParam(name="type",defaultValue="0")int type
			
			){
		if(type ==0 || excelFile == null || excelFile.isEmpty()){
			return null;
		}
		ModelAndView modelAndView = new ModelAndView(viewPath+"add");
		modelAndView.addObject("types",questionTypeService.getAll());
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
			XSSFSheet spreadsheet = workbook.getSheetAt(0);
		      Iterator < Row > rowIterator = spreadsheet.iterator();
		      if(rowIterator.hasNext()){
		    	  rowIterator.next();
		      }
		      String typeName = questionTypeService.getById(type).getName();
		      while (rowIterator.hasNext()) 
		      {
		    	 XSSFRow row = (XSSFRow) rowIterator.next();
		         Iterator < Cell > cellIterator = row.cellIterator();
		         Question question = new Question();
		         question.setType(type);
		         question.setTypeName(typeName);
		         String[] data = new String[7];
		            int x =0;
		         while ( cellIterator.hasNext()) 
		         {
		            Cell cell = cellIterator.next();
		            if(x==7){
		            	break;
		            }
		            switch (cell.getCellType()) 
		            {
		               case Cell.CELL_TYPE_NUMERIC:
		            	   data[x]=cell.getNumericCellValue()+"";
		               break;
		               case Cell.CELL_TYPE_STRING:
		            	   data[x]=cell.getStringCellValue();
		               break;
		            }
		            x++;
		         }
		         question.setTitle(data[0]);
		         question.setaAnswer(data[1]);
		         question.setbAnswer(data[2]);
		         question.setcAnswer(data[3]);
		         question.setdAnswer(data[4]);
		         question.setAnswer(data[5]);
		         question.setAnalyse(data[6]);
		         
		         questionService.addQuestion(question);
		      }
		      
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping("manage")
	public ModelAndView manage(
			@RequestParam(name="page",defaultValue="1")int page,
			@RequestParam(name="title",defaultValue="")String title
			){
		ModelAndView modelAndView = new ModelAndView(viewPath+ "manage");
		if(!"".equals(title)){
			modelAndView.addObject("title",title);
			List<Question> questions = questionService.getSearch(title);
			if(questions==null || questions.size()==0){
				PageCut<Question> pc = new PageCut<>(1, 1,0);
				modelAndView.addObject("pc",pc);
				return modelAndView;
			}
			
			PageCut<Question> pc = new PageCut<>(1, 10,questions.size());
			pc.setData(questions);
			modelAndView.addObject("pc",pc);
			return modelAndView;
		}
		modelAndView.addObject("pc",questionService.getPageCut(page, 10));
		return modelAndView;
	}
	
	@RequestMapping("change")
	public ModelAndView change(
			int id
			){
		ModelAndView modelAndView = new ModelAndView(viewPath+ "change");
		modelAndView.addObject("types",questionTypeService.getAll());
		modelAndView.addObject("question",questionService.getById(id));
		return modelAndView;
	}
	
	@RequestMapping("changeDo")
	public ModelAndView changeDo(
			Question question
			){
		ModelAndView modelAndView = new ModelAndView(viewPath+"add");
		question.setTypeName(questionTypeService.getById(question.getType()).getName());
		questionService.updateQuestion(question);
		logger.debug(question);
		modelAndView.addObject("remind","修改成功!");
		return modelAndView;
	}
	
	@RequestMapping("delete")
	public ModelAndView delete(
			@RequestParam(name="id",defaultValue ="0")int id
			){
		ModelAndView modelAndView = new ModelAndView("/backend/remind");
		if(id<1){
			modelAndView.addObject("warning","删除失败!");
			return modelAndView;
		}
		questionService.deleteQuestion(id);
		modelAndView.addObject("remind","删除成功!");
		return modelAndView;
	}
}
