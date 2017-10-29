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
* @time 2017年3月1日 上午7:31:15
* 类说明：部门
*/
@Entity
@Table(name="tb_department")
public class Department {
	
	public final static int Organization = 1;//组织部
	public final static int Party = 2;//党委
	public final static int Branch = 3;//党支部
	private final static String[] typeNames={"组织部","党委","党支部"};
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int type=0;//部门类型，对应3个静态变量
	private int createDate=0;//成立时间
	private int memberNum=0;//党员数量
	private int belongId=0;//所属于部门的Id
	
	@Column(length=30)
	private String typeName="";//设置type时会自动设置，详见type字段的set方法
	@Column(length=30)
	private String belongName="";//所属于部门的名字，修改部门时记得更新此属性
	@Column(length=30)
	private String name="";//部门名字
	@Column(length=30)
	private String branchType="";//支部类型，由用户书写，仅为普通字段，不做任何权限相关
	@Column(length=30)
	private String company="";//所在单位
	@Column(length=100)
	private String marks="";//备注
	
	@Column(length=10240)
	private String summary="";//党委简介
	
	public Department(){
		super();
	}

	public Department(int type, int createDate, int memberNum, int belongId, String belongName, String name,
			String branchType, String company, String marks) {
		super();
		this.type = type;
		this.createDate = createDate;
		this.memberNum = memberNum;
		this.belongId = belongId;
		this.belongName = belongName;
		this.name = name;
		this.branchType = branchType;
		this.company = company;
		this.marks = marks;
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
		if(type >0 && type<4){
			this.typeName = typeNames[type-1];
		}else{
			this.typeName = "未知";
		}
		
	}

	public int getCreateDate() {
		return createDate;
	}

	public void setCreateDate(int createDate) {
		this.createDate = createDate;
	}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	public int getBelongId() {
		return belongId;
	}

	public void setBelongId(int belongId) {
		this.belongId = belongId;
	}

	public String getBelongName() {
		return belongName;
	}

	public void setBelongName(String belongName) {
		this.belongName = belongName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public static int getOrganization() {
		return Organization;
	}

	public static int getParty() {
		return Party;
	}

	public static int getBranch() {
		return Branch;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", type=" + type + ", createDate=" + createDate + ", memberNum=" + memberNum
				+ ", belongId=" + belongId + ", belongName=" + belongName + ", name=" + name + ", branchType="
				+ branchType + ", company=" + company + ", marks=" + marks + ", summary=" + summary + "]";
	}
	
	
}
