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

public class UserZuManageCase01 extends UrpOrgFluentTest{

	/**
	 * 用户组管理模块：用户组新增、编辑、删除
	 * 
	 */
	List<Account> accounts;
	String userZuName; // 用户组名称
	String telnumber; // 电话
	String address; // 地址
	
	String youzhengbianma;//邮政编码
	String beizhu;//备注
	
	String userZuNameEdit; // 用户组名称编辑
	
	@BeforeTest
	public void beforeTest() {
		accounts = AccountRead.getAll();// 获取数据，用户名和密码
		userZuName = "用户组名称" + CommonRandUtil.getDate(); 
		telnumber = TelNumber.getTel();
		address = "用户组地址" + CommonRandUtil.getDate() ;
		
		youzhengbianma="311715" ;
		beizhu="用户组自动化测试"+ CommonRandUtil.getDate(); 
		
		userZuNameEdit = "用户组名称编辑" + CommonRandUtil.getDate(); 
		
	}
	
	@Test
	public void userZuManageCase() {

		//新增用户组
		addUserZu(accounts.get(0), userZuName, telnumber, address,youzhengbianma,beizhu);
		//编辑用户组
		editUserZu(accounts.get(0),userZuName,userZuNameEdit);
		//删除用户组
		deleteUserZu(accounts.get(0),userZuNameEdit);
		
	}
	
	@AfterTest
	public void afterTest() {

	}
	
	
}
