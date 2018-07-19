package com.apache.urpcase;

import java.util.List;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.apache.urp.UrpOrgFluentTest;
import com.apache.urp.util.Account;
import com.apache.urp.util.AccountRead;
import com.apache.urp.util.CommonRandUtil;
import com.apache.urp.util.TelNumber;

public class UserManageCase extends UrpOrgFluentTest{

	/**
	 * 后台管理>>用户管理，针对某组织，用户的新增、编辑、删除
	 */
	List<Account> accounts;
	String orgName; // 组织名称
	String telnumber; // 组织电话
	String address; // 组织地址
	
	String username;//用户名称
	String zhiwu;//用户职务
	
	
	
	@BeforeTest
	public void beforeTest() {
		accounts = AccountRead.getAll();// 获取数据，用户名和密码
		orgName = "测试组织名称" + CommonRandUtil.getDate(); 
		telnumber = TelNumber.getTel();
		address = "测试组织地址" + CommonRandUtil.getDate() ;
		
		username="测试用户"+ CommonRandUtil.getDate(); 
		zhiwu="职务"+ CommonRandUtil.getDate(); 
		
	}
	
	@Test
	public void userManageCase() {

		//新增组织架构
		String newOrgName=addZuZhiJiaGou01(accounts.get(0), orgName, telnumber, address);
		
		//针对新增的组织架构，对其新增用户
		addUser(accounts.get(0), newOrgName,username,zhiwu,telnumber);
		
	}
	
	@AfterTest
	public void afterTest() {

	}
	

}
