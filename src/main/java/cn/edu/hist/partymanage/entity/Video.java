package cn.edu.hist.partymanage.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月4日 下午8:15:00
* 类说明
*/
@Entity
@Table(name="tb_video")
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length=100)
	private String role;//所属角色,以#分割,如#2#45#
	@Column(length=500)
	private String department;//所属角部门,以#分割,如#2#45#
	private boolean allRole;//是否所有角色可看
	private boolean allDepartment;//是否所有部门可看
	@Column(length=50)
	private String name;
	@Column(length=200)
	private String path;
	private int type;//类型
	private long watchNum;//观看量
	@Column(length=10)
	private String typeName;//修改类型时记得修改
	@Column(length=255)
	private String summary;//视频简介
	
	private Timestamp createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public boolean isAllRole() {
		return allRole;
	}

	public void setAllRole(boolean allRole) {
		this.allRole = allRole;
	}

	public boolean isAllDepartment() {
		return allDepartment;
	}

	public void setAllDepartment(boolean allDepartment) {
		this.allDepartment = allDepartment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getWatchNum() {
		return watchNum;
	}

	public void setWatchNum(long watchNum) {
		this.watchNum = watchNum;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

	@Override
	public String toString() {
		return "Video [id=" + id + ", role=" + role + ", department=" + department + ", allRole=" + allRole
				+ ", allDepartment=" + allDepartment + ", name=" + name + ", path=" + path + ", type=" + type
				+ ", watchNum=" + watchNum + ", typeName=" + typeName + ", summary=" + summary + ", createTime="
				+ createTime + "]";
	}
	/**
	* @Auther 宋民举
	* @email 860080937@qq.com
	* @Date 2017年5月2日
	* @param department
	* @return
	* @decoration :判断部门是否可看
	* @careful
	*/
	public boolean isDepartmentIn(int department){
		return this.department.contains("#"+department+"#");
	}
	/**
	* @Auther 宋民举
	* @email 860080937@qq.com
	* @Date 2017年5月2日
	* @param role
	* @return
	* @decoration :判断角色是否可看
	* @careful
	*/
	public boolean isRoleIn(int role){
		return this.role.contains("#"+role+"#");
	}
}
