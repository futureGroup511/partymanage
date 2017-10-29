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
* @time 2017年3月4日 下午8:50:45
* 类说明
*/
@Entity
@Table(name="tb_article")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int type;
	@Column(length=10)
	private String typeName;
	@Column(length=100)
	private String role;//所属角色,以#分割,如#2#45#
	@Column(length=500)
	private String department;//所属角部门,以#分割,如#2#45#
	
	private boolean allRole;//是否所有角色可看
	private boolean allDepartment;//是否所有部门可看
	
	private int userId;
	@Column(length=10)
	private String userName;
	
	private long readNum;//阅读量
	
	@Column(length=100)
	private String firstImg;//仅存储图片Id,具体路径回头会添加到application中,现在有疑问先问宋民举
	
	@Column(length=255)
	private String title;
	@Column(length=2047)
	private String content;
	
	private Timestamp createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public long getReadNum() {
		return readNum;
	}

	public void setReadNum(long readNum) {
		this.readNum = readNum;
	}

	public String getFirstImg() {
		return firstImg;
	}

	public void setFirstImg(String firstImg) {
		this.firstImg = firstImg;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		return "Article [id=" + id + ", type=" + type + ", typeName=" + typeName + ", role=" + role + ", department="
				+ department + ", allRole=" + allRole + ", allDepartment=" + allDepartment + ", userId=" + userId
				+ ", userName=" + userName + ", readNum=" + readNum + ", firstImg=" + firstImg + ", title=" + title
				+ ", content=" + content + ", createTime=" + createTime + "]";
	}
	
	/**
	* @Auther 宋民举
	* @email 860080937@qq.com
	* @Date 2017年5月2日
	* @return
	* @decoration : 以数组的方式返回所有可看角色
	* @careful
	*/
	public String[] getRolesArray(){
		while(this.role.contains("##")){
			this.role = this.role.replaceAll("##", "#");
		}
		return this.role.split("#");
	}
	
	/**
	* @Auther 宋民举
	* @email 860080937@qq.com
	* @Date 2017年5月2日
	* @return
	* @decoration : 以数组方式返回所有可看部门
	* @careful
	*/
	public String[] getDepartmentsArray(){
		while(this.department.contains("##")){
			this.department = this.department.replaceAll("##", "#");
		}
		return this.role.split("#");
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
