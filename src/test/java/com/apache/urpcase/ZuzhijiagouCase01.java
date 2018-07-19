package com.apache.urpcase;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.fluentlenium.core.hook.wait.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.apache.urp.UrpOrgFluentTest;
import com.apache.urp.util.Account;
import com.apache.urp.util.AccountRead;
import com.apache.urp.util.CommonRandUtil;
import com.apache.urp.util.TelNumber;

@Wait(timeout = 5, timeUnit = TimeUnit.SECONDS)
public class ZuzhijiagouCase01 extends UrpOrgFluentTest {
	/**
	 * 系统管理>>组织架构，组织架构的新增、编辑、删除
	 */
	List<Account> accounts;
	String orgName; // 组织名称
	String telnumber; // 电话
	String address; // 地址
	String orgNameEdit;

	@BeforeTest
	public void beforeTest() {

		accounts = AccountRead.getAll();// 获取数据，用户名和密码
		orgName = "测试组织名称" + CommonRandUtil.getDate(); 
		telnumber = TelNumber.getTel();
		address = "测试组织地址" + CommonRandUtil.getDate() ;
		orgNameEdit = "测试组织名称编辑" + CommonRandUtil.getDate() ;

	}

	@Test
	public void zuZhiJiaGou() {

		addZuZhiJiaGou(accounts.get(0), orgName, telnumber, address);
		
		editZuZhiJiaGou(accounts.get(0), orgName, orgNameEdit);
		
		deleteZuZhiJiaGou(accounts.get(0),orgNameEdit);
		
	}
	
	@AfterTest
	public void afterTest() {

	}
}
