package cn.edu.hist.partymanage.entity;
/*
* @author 宋
* @mail 860080937@qq.com
* @time 2017年3月6日 下午5:31:26
* 类说明:角色类,不存储数据库;
*/
//不定义数据库
public final class Role {
	public final static int OrganizationDepartment = 1;//组织部
	public final static int PartySecretary = 2;//党委书记
	public final static int BranchSecretary = 3;//党支部书记
	public final static int PartyMember = 4;//党员
	public final static int Activist = 5;//积极分子
	public static String getRole(int role){
		switch (role) {
		case 1:
			return "组织部";
		case 2:
			return "党委书记";
		case 3:
			return "党支部书记";
		case 4:
			return "党员";
		case 5:
			return "积极分子";
		default:
			return "未知角色";
		}
	}
}
