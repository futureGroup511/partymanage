package cn.edu.hist.partymanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午8:23:50
* 类说明
*/

@Entity
@Table(name="tb_questionType")
public class QuestionType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length=10)
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "QuestionType [id=" + id + ", name=" + name + "]";
	}
	
	
}
