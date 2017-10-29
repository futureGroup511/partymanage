package cn.edu.hist.partymanage.entity;
/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午9:52:26
* 类说明
*/

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_payLog")
public class PayLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int userId;
	@Column(length=20)
	private String userName;
	private int departmentId;//所属部门
	@Column(length=20)
	private String departmentName;
	private double payNum;//交钱的多少
	private double lastNum;//剩余多少
	private Timestamp createTime;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public double getPayNum() {
		return payNum;
	}
	public void setPayNum(double payNum) {
		this.payNum = payNum;
	}
	public double getLastNum() {
		return lastNum;
	}
	public void setLastNum(double lastNum) {
		this.lastNum = lastNum;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	//获得createTim的yyyy-MM-dd格式的字符串
		public String getCreateDate() {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//定义日期类型格式
			String date =format.format(createTime);//转换为字符串	  
			return date;
		}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}
