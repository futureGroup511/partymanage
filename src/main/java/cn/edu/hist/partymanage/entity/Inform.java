package cn.edu.hist.partymanage.entity;
/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月5日 下午2:30:59
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
@Table(name="tb_inform")
public class Inform {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int autherId;
	@Column(length=100)
	private String role;//所属角色,以#分割,如#2#45#
	@Column(length=500)
	private String department;//所属角部门,以#分割,如#2#45#
	
	private boolean allRole;//是否所有角色可看
	private boolean allDepartment;//是否所有部门可看
	@Column(length=10)
	private String autherName;
	@Column(length=50)
	private String title;
	@Column(length=20470)
	private String content;
	
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

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the allRole
	 */
	public boolean isAllRole() {
		return allRole;
	}

	/**
	 * @param allRole the allRole to set
	 */
	public void setAllRole(boolean allRole) {
		this.allRole = allRole;
	}

	/**
	 * @return the allDepartment
	 */
	public boolean isAllDepartment() {
		return allDepartment;
	}

	/**
	 * @param allDepartment the allDepartment to set
	 */
	public void setAllDepartment(boolean allDepartment) {
		this.allDepartment = allDepartment;
	}

	public int getAutherId() {
		return autherId;
	}

	public void setAutherId(int autherId) {
		this.autherId = autherId;
	}

	public String getAutherName() {
		return autherName;
	}

	public void setAutherName(String autherName) {
		this.autherName = autherName;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Inform [id=" + id + ", autherId=" + autherId + ", role=" + role + ", department=" + department
				+ ", allRole=" + allRole + ", allDepartment=" + allDepartment + ", autherName=" + autherName
				+ ", title=" + title + ", content=" + content + ", createTime=" + createTime + "]";
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
