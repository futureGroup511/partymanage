package cn.edu.hist.partymanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.context.internal.ThreadLocalSessionContext;

/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年2月28日 下午5:48:39
* 类说明
*/
@Entity
@Table(name="tb_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition="int default 0")
	private int type;//用户类型，1-5，必须为1-5，set方法需要做检查。
	@Column(nullable=false,length=40)
	private String phone="";//手机号，又作为账号
	
	@Column(nullable=true,length=255)
	private String password="";
	@Column(nullable=false,length=10)
	private String name="";
	@Column(nullable=true,length=5)
	private String sex="";
	@Column(columnDefinition="int default 0")
	private int birthday;//必须是八位数字，格式：19970813
	@Column(columnDefinition="int default 0")
	private int age;
	@Column(nullable=true,length=20)
	private String nation;//民族
	@Column(nullable=true,length=30)
	private String nativePlace;//籍贯
	
	@Column(nullable=true,length=10)
	private String education;//学历
	@Column(nullable=true,length=10)
	private String academicDegree;//学位
	@Column(nullable=true,length=20)
	private String title;//职称
	@Column(columnDefinition="int default 0")
	private int joinWorkDate;//入职时间,必须为8位数字：格式19970813
	@Column(columnDefinition="int default 0")
	private int joinPartyDate;//入党时间,必须为8位数字：格式19970813
	
	//学生党员私有信息
	@Column(columnDefinition="int default 0")
	private int grade;//年级,党员属性
	@Column(nullable=true,length=20)
	private String className;//班级，党员属性
	@Column(nullable=true,length=20)
	private String nowJob;//现任职务
	@Column(columnDefinition="int default 0")
	private int joinSchoolDate;//入学时间
	
	//共有属性
	@Column(columnDefinition="int default 0")
	private int organizationId;//组织部Id,不属于则留空,下同
	@Column(nullable=true,length=30)
	private String organizationName;
	@Column(columnDefinition="int default 0")
	private int partyId;//党委Id,不属于则留空,下同
	@Column(nullable=true,length=30)
	private String partyName;
	@Column(columnDefinition="int default 0")
	private int branchId;//党支部Id,不属于则留空,下同
	@Column(nullable=true,length=30)
	private String branchName;
	@Column(columnDefinition="bigint default 0")
	private long studySecond;//学习时长,秒
	@Column(nullable=true,length=20)
	private String studyTime;
	
	/**
	 * 
	 * 丁赵雷
	 */
	public User() {
		super();
	}
	
	
	/**
	 * @param type
	 * @param phone
	 * @param password
	 * @param name
	 * @param sex
	 * @param birthday
	 * @param age
	 * @param nation
	 * @param nativePlace
	 * @param education
	 * @param academicDegree
	 * @param title
	 * @param joinWorkDate
	 * @param joinPartyDate
	 * 书记等构造函数
	 */
	public User(int type, String phone, String password, String name, String sex, int birthday, int age,
			String nation, String nativePlace, String education, String academicDegree, String title, int joinWorkDate,
			int joinPartyDate) {
		super();
		this.type = type;
		this.phone = phone;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.age = age;
		this.nation = nation;
		this.nativePlace = nativePlace;
		this.education = education;
		this.academicDegree = academicDegree;
		this.title = title;
		this.joinWorkDate = joinWorkDate;
		this.joinPartyDate = joinPartyDate;
	}



	/**
	 * @param type
	 * @param phone
	 * @param password
	 * @param name
	 * @param sex
	 * @param birthday
	 * @param age
	 * @param nation
	 * @param nativePlace
	 * @param joinPartyDate
	 * @param grade
	 * @param className
	 * @param nowJob
	 * @param joinSchoolDate
	 * 党员及积极分子的构造函数
	 */
	public User(int type, String phone, String password, String name, String sex, int birthday, int age,
			String nation, String nativePlace, int joinPartyDate, int grade, String className, String nowJob,
			int joinSchoolDate) {
		super();
		this.type = type;
		this.phone = phone;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.age = age;
		this.nation = nation;
		this.nativePlace = nativePlace;
		this.joinPartyDate = joinPartyDate;
		this.grade = grade;
		this.className = className;
		this.nowJob = nowJob;
		this.joinSchoolDate = joinSchoolDate;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getBirthday() {
		return birthday;
	}

	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getAcademicDegree() {
		return academicDegree;
	}

	public void setAcademicDegree(String academicDegree) {
		this.academicDegree = academicDegree;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getJoinWorkDate() {
		return joinWorkDate;
	}

	public void setJoinWorkDate(int joinWorkDate) {
		this.joinWorkDate = joinWorkDate;
	}

	public int getJoinPartyDate() {
		return joinPartyDate;
	}

	public void setJoinPartyDate(int joinPartyDate) {
		this.joinPartyDate = joinPartyDate;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getNowJob() {
		return nowJob;
	}

	public void setNowJob(String nowJob) {
		this.nowJob = nowJob;
	}

	public int getJoinSchoolDate() {
		return joinSchoolDate;
	}

	public void setJoinSchoolDate(int joinSchoolDate) {
		this.joinSchoolDate = joinSchoolDate;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public int getPartyId() {
		return partyId;
	}

	public void setPartyId(int partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public long getStudySecond() {
		return studySecond;
	}

	public void setStudySecond(long studySecond) {
		this.studySecond = studySecond;
	}

	public String getStudyTime() {
		return studyTime;
	}

	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", type=" + type + ", phone=" + phone + ", password=" + password + ", name=" + name
				+ ", sex=" + sex + ", birthday=" + birthday + ", age=" + age + ", nation=" + nation + ", nativePlace="
				+ nativePlace + ", education=" + education + ", academicDegree=" + academicDegree + ", title=" + title
				+ ", joinWorkDate=" + joinWorkDate + ", joinPartyDate=" + joinPartyDate + ", grade=" + grade
				+ ", className=" + className + ", nowJob=" + nowJob + ", joinSchoolDate=" + joinSchoolDate
				+ ", organizationId=" + organizationId + ", organizationName=" + organizationName + ", partyId="
				+ partyId + ", partyName=" + partyName + ", branchId=" + branchId + ", branchName=" + branchName
				+ ", studySecond=" + studySecond + ", studyTime=" + studyTime + "]";
	}
	public String getTypeName(){
		switch (this.type) {
		case Role.OrganizationDepartment:
			return "组织部人员";
		case Role.PartySecretary:
			return "党委书记";
		case Role.BranchSecretary:
			return "党支部书记";
		case Role.PartyMember:
			return "党员";
		case Role.Activist:
			return "积极分子";
		default:
			return "未设置";
		}
	}
	/**
	* @Auther 宋民举
	* @email 860080937@qq.com
	* @Date 2017年4月28日
	* @return
	* @decoration :获取部门,先获取低级部门,若没有,得到高级部门
	* @careful
	*/
	public String getDepartmentName(){
		String[] departments = {this.getBranchName(),this.getPartyName(),this.getOrganizationName()};
		for(String d:departments){
			if(null==d ||"".equals(d)){
				continue;
			}
			return d;
		}
		return "";
	}
	/**
	* @Auther 宋民举
	* @email 860080937@qq.com
	* @Date 2017年5月7日
	* @return
	* @decoration :返回用户部门,这关系真是错综复杂
	* @careful
	*/
	public int getDepartmentId(){
		switch (type) {
		case 5:
			
			return this.branchId;
		case 4:
			return this.branchId;
		case 3:
			return this.branchId;
		case 2:
			return this.partyId;
		case 1:
			return this.organizationId;
		default:
		}
		return 0;
	}
	
}
