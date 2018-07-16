package com.apache.urpcase;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.hook.wait.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.apache.urp.BaseFluentTest;
import com.apache.urp.page.LoginPage;
import com.apache.urp.page.MainPage;
import com.apache.urp.page.YongHuManagePage;
import com.apache.urp.page.ZuZhiJiaGouPage;
import com.apache.urp.util.Account;
import com.apache.urp.util.AccountRead;
import com.apache.urp.util.CommonRandUtil;
import com.apache.urp.util.TelNumber;

@Wait(timeout = 5, timeUnit = TimeUnit.SECONDS)
public class ZuzhijiagouCase2 extends BaseFluentTest {
	/**
	 * 系统管理>>组织架构，组织架构的授权
	 */

	List<Account> accounts;

	@Page
	private LoginPage loginPage;// 登录页面
	@Page
	private MainPage mainPage;// 主页面
	@Page
	private ZuZhiJiaGouPage zuZhiJiaGouPage;// 组织架构页面
	@Page
	private YongHuManagePage yongHuManagePage;// 组织架构页面
	@Page
	private ZuzhijiagouCase01 zuzhijiagouCase1;// 组织架构用例1，引用该用例，是为了调用新增组织架构方法。

	@BeforeTest
	public void beforeTest() {
		accounts = AccountRead.getAll();// 获取数据，用户名和密码

	}

	/**
	 * 组织架构新增
	 */
	@Test
	public void addZuZhiJiaGou() {
	//	zuzhijiagouCase1.addZuZhiJiaGou();
	}

	/**
	 * 针对组织架构，对其进行授权
	 */
	@Test
	public void shouQuanZuZhiJiaGou() {
	//	zuzhijiagouCase1.addZuZhiJiaGou();
	}

	@AfterTest
	public void afterTest() {

	}
}
