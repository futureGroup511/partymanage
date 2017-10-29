package cn.edu.hist.partymanage.util;

import cn.edu.hist.partymanage.entity.User;

/*
*@Auther 宋民举
*@Email 860080937@qq.com
*@Date 2017年4月27日
*@Description : User 类的小工具
*/
public class UserUtils {
	public static int getDepartmentId(User user){
		if(user==null) return 0;
		switch(user.getType()){
		case 1:
			return user.getOrganizationId();
		case 2:
			return user.getPartyId();
		default:
			return user.getBranchId();
		}
	}
}
