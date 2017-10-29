package cn.edu.hist.partymanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_questionLog")
public class QuestionLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int userId;
	private int questionId;
	private int questionType;
	private int score;//题的分数
	private int userScore;//用户得分
	
	@Column(length=10)
	private String userName;
	@Column(length=10)
	private String userAnswer;
	@Column(length=10)
	private String questionAnwer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getQuestionType() {
		return questionType;
	}
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAnswer() {
		return userAnswer;
	}
	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
	public String getQuestionAnwer() {
		return questionAnwer;
	}
	public void setQuestionAnwer(String questionAnwer) {
		this.questionAnwer = questionAnwer;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getUserScore() {
		return userScore;
	}
	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}
	@Override
	public String toString() {
		return "QuestionLog [id=" + id + ", userId=" + userId + ", questionId=" + questionId + ", questionType="
				+ questionType + ", userName=" + userName + ", userAnswer=" + userAnswer + ", questionAnwer="
				+ questionAnwer + "]";
	}


}
