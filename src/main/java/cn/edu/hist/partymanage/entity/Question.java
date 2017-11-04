package cn.edu.hist.partymanage.entity;

import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午8:23:34
* 类说明
*/
@Entity
@Table(name="tb_question")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int score=1;//分数.默认为5
	private int type;
	@Column(length=10)
	private String typeName;
	@Column(length=255)
	private String title;//题干
	@Column(length=255)
	private String aAnswer;//a答案
	@Column(length=255)
	private String bAnswer;
	@Column(length=255)
	private String cAnswer;
	@Column(length=255)
	private String dAnswer;
	@Column(length=5)
	private String answer;//答案,为a或者b,或者c,或者d,set方法默认存储小写
	@Column(length=255)
	private String analyse;//解析,分析
	
	//辅助属性,不加入数据库——————焦祥宇加
	@Transient
	private String myAnswer;//考生答案
	@Transient
	private int myScore;//考生得分
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getaAnswer() {
		return aAnswer;
	}
	public void setaAnswer(String aAnswer) {
		this.aAnswer = aAnswer;
	}
	public String getbAnswer() {
		return bAnswer;
	}
	public void setbAnswer(String bAnswer) {
		this.bAnswer = bAnswer;
	}
	public String getcAnswer() {
		return cAnswer;
	}
	public void setcAnswer(String cAnswer) {
		this.cAnswer = cAnswer;
	}
	public String getdAnswer() {
		return dAnswer;
	}
	public void setdAnswer(String dAnswer) {
		this.dAnswer = dAnswer;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer.toLowerCase();
	}
	public String getAnalyse() {
		return analyse;
	}
	public void setAnalyse(String analyse) {
		this.analyse = analyse;
	}
	
	
	public String getMyAnswer() {
		return myAnswer;
	}
	
	public void setMyAnswer(String myAnswer) {
		this.myAnswer = myAnswer;
	}
	
	
	public int getMyScore() {
		return myScore;
	}
	
	public void setMyScore(int myScore) {
		this.myScore = myScore;
	}
		
	@Override
	public String toString() {
		return "Question [id=" + id + ", score=" + score + ", type=" + type + ", typeName=" + typeName + ", title="
				+ title + ", aAnswer=" + aAnswer + ", bAnswer=" + bAnswer + ", cAnswer=" + cAnswer + ", dAnswer="
				+ dAnswer + ", answer=" + answer + ", analyse=" + analyse + ", myAnswer=" + myAnswer + ", myScore="
				+ myScore + "]";
	}
	public boolean isDuoxuan() {
		if(this.answer==null) {
			return false;
		}
		if(this.answer.length()>1) {
			return true;
		}
		return false;
	}
	
	//检查已经回答的答案是否选择A,宋民举
	public boolean isaCheck() {
		return myAnswer==null?false:myAnswer.toLowerCase().contains("a");
	}
	public boolean isbCheck() {
		return myAnswer==null?false:myAnswer.toLowerCase().contains("b");
	}
	public boolean iscCheck() {
		return myAnswer==null?false:myAnswer.toLowerCase().contains("c");
	}
	public boolean isdCheck() {
		return myAnswer==null?false:myAnswer.toLowerCase().contains("d");
	}
}
