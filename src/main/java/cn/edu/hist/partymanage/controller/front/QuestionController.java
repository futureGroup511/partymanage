/**        
 * @author: 焦祥宇 
 * @date:   createDate：2017年5月7日 上午8:04:02   
 * @Description:  
 * 
 */
package cn.edu.hist.partymanage.controller.front;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.hist.partymanage.base.BaseController;
import cn.edu.hist.partymanage.entity.ExamLog;
import cn.edu.hist.partymanage.entity.Question;
import cn.edu.hist.partymanage.entity.QuestionInTest;
import cn.edu.hist.partymanage.entity.QuestionLog;
import cn.edu.hist.partymanage.entity.QuestionType;
import cn.edu.hist.partymanage.entity.User;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/party/question")
public class QuestionController extends BaseController {
	// 转到试题类型列表
	@RequestMapping(value = "/examType", method = RequestMethod.GET)
	public String examSort(HttpServletRequest request, HttpSession session) {
		List<QuestionType> questionTypes=questionTypeService.getAll();
		request.setAttribute("questionTypes", questionTypes);
		return "/front/examType";
	}

	// 在线考试，宋民举改
	@RequestMapping(value = "/onlineExam", method = RequestMethod.GET)
	public String toExam(
			@RequestParam(defaultValue="0" , name="id") int id,
			HttpServletRequest request, HttpSession session) {
		User user = (User)session.getAttribute("user");
		ExamLog el = examLogService.getByUserAndQuestionType(user.getId(), id);
		if(el!=null) {
			request.setAttribute("examLog",el);
			request.setAttribute("questionNum", "保密");
			return "/front/examResult";
		}
		List<Question> questionList = questionService.getRandom(100,id);
		//把所有多选的放在后面`	
		for(int i=questionList.size()-1;i>=0;i--) {
			Question q = questionList.get(i);
			if(q.isDuoxuan()) {
				questionList.remove(i);
				questionList.add(q);
			}
		}
		List<QuestionInTest> inTests = questionInTestService.get(user.getId(), id);
		List<Question> hasAnswerQuestion = new LinkedList<>();
		//把做过的题注入答案，并放在前面
		for(int i=questionList.size()-1;i>=0;i--) {
			Question q = questionList.get(i);
			for(int j=0;j<inTests.size();j++) {
				QuestionInTest qit = inTests.get(j);
				if(q.getId() == qit.getQuestionId()) {
					q.setMyAnswer(qit.getAnswer());
					inTests.remove(j);
					questionList.remove(i);
					hasAnswerQuestion.add(q);
					break;
				}
			}
		}
		hasAnswerQuestion.addAll(questionList);
		if (hasAnswerQuestion.size() > 0) {
			int testScore = 0;
			for (Question q : hasAnswerQuestion) {
				testScore = testScore + q.getScore();
			}
			//防止做题超时
			session.setMaxInactiveInterval(14400);
			session.setAttribute("questionList", hasAnswerQuestion);
			session.setAttribute("testNum", hasAnswerQuestion.size());
			session.setAttribute("testScore", testScore);
		} else {
			request.setAttribute("NoQuestion", "该类型暂时没有试题！");
		}
		for(Question q:hasAnswerQuestion) {
			logger.debug(q);
		}
		return "/front/onlineExam";
	}
	//生成成绩，宋民举改
	@RequestMapping(value = "/examResult", method = RequestMethod.POST)
	public String examResult(HttpServletRequest request, HttpSession session) {
		
		String isTemp = request.getParameter("istemp");
		logger.debug(isTemp);
		if("y".equals(isTemp)) {
			return this.tempAnswer(request, session);
		}
		
		List<Question> questionList = (List<Question>) session.getAttribute("questionList");
		
		if(questionList==null) {
			request.setAttribute("remind", "您已经参加过考试，请不要重复提交。");
			return "/front/examResult";
		}
		User user = (User) session.getAttribute("user");
		if(questionList.size()>0) {
			questionInTestService.delete(user.getId(), questionList.get(0).getType());
		}
		ExamLog el = new ExamLog();
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		el.setFinishedTime(df.format(new Date()));
		el.setUserId(user.getId());
		el.setUserName(user.getName());
		int totalScore = 0;
		int userScore = 0;
		
		for(Question q:questionList) {
			totalScore += q.getScore();
			el.setQuestionTypeId(q.getType());
			QuestionLog ql = new QuestionLog();
			ql.setQuestionId(q.getId());
			ql.setQuestionAnwer(q.getAnswer());
			ql.setQuestionType(q.getType());
			ql.setUserId(user.getId());
			ql.setUserName(user.getName());
			ql.setScore(q.getScore());
			String[] answers = request.getParameterValues("answer_"+q.getId());
			
			if(answers == null) {
				logger.debug(q.getTitle());
				ql.setUserAnswer("");
				ql.setUserScore(0);
			}else {
				String answer = "";
				for(String a:answers) {
					answer += a;
				}
				logger.debug(q.getTitle()+":"+answer);
				answer=answer.trim();
				ql.setUserAnswer(answer);
				if(answer.equalsIgnoreCase(q.getAnswer())) {
					ql.setUserScore(q.getScore());
					userScore += q.getScore();
				}
			}
			questionLogService.addLog(ql);
		}
		el.setUserScore(userScore);
		el.setTotalScore(totalScore);
		QuestionType qt = questionTypeService.getById(el.getQuestionTypeId());
		if(qt != null) {
			el.setQuestionType(qt.getName());
		}
		request.setAttribute("examLog",el);
		request.setAttribute("questionNum", questionList.size());
		examLogService.addLog(el);
		session.removeAttribute("questionList");
		return "/front/examResult";
	}
	
	private String tempAnswer(HttpServletRequest request, HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Question> questionList = (List<Question>) session.getAttribute("questionList");
		if(questionList==null) {
			request.setAttribute("remind", "您已经参加过考试，请不要重复提交。");
			return "/front/examResult";
		}
		int hasAnswerNum = 0;
		if(questionList.size()>0) {
			questionInTestService.delete(user.getId(), questionList.get(0).getType());
		}
		for(Question q:questionList) {
			String[] answers = request.getParameterValues("answer_"+q.getId());
			if(answers != null) {
				String answer = "";
				for(String a:answers) {
					answer += a;
				}
				answer=answer.trim();
				QuestionInTest qtInTest = new QuestionInTest();
				qtInTest.setAnswer(answer);
				qtInTest.setQuestionId(q.getId());
				qtInTest.setQuestionTypeId(q.getType());
				qtInTest.setUserId(user.getId());
				questionInTestService.insert(qtInTest);
				hasAnswerNum++;
			}
		}
		request.setAttribute("remind",String.format("暂存成功，已做%d题，还有%d未做，下次可继续答题。", hasAnswerNum,questionList.size()-hasAnswerNum));
		
		return "/front/examResult";
	}
}
