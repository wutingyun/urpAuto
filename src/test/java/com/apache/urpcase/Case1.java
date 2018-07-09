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
import com.apache.urp.page.FaSongLieBiaoPage;
import com.apache.urp.page.LoginPage;
import com.apache.urp.page.MainPage;
import com.apache.urp.util.Account;
import com.apache.urp.util.AccountRead;
import com.apache.urp.util.CommonRandUtil;


@Wait(timeout = 5, timeUnit = TimeUnit.SECONDS)
public class Case1 extends BaseFluentTest {
	/**
	 * 门前三包>>通知公告>>发送列表，新增通知公告。
	 */
	
	String title;
	String content;
	List<Account> accounts;

	@Page
	private LoginPage loginPage;// 登录页面
	@Page
	private MainPage mainPage;// 主页面
	@Page
	private FaSongLieBiaoPage fasongliebiaoPage;// 发送列表

	@BeforeTest
	public void beforeTest() {

		accounts = AccountRead.getAll();//获取数据，用户名和密码
		
		title = "通知公告新增标题_自动化测试" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
		content = "通知公告新增内容_自动化测试" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
	}

	@Test
	public void addNoticeCase01() {
		try {
			// 通过登录页面，输入用户名和密码，进入主页面。
			goTo(loginPage);
			loginPage.login(accounts.get(0));

			// 在主页面，点击一级菜单通知公告，点击二级菜单发送列表。
			mainPage.clickTongZhiGongGao();
			mainPage.clickFaSongLieBiao();

			// 在发送列表页面，新增通知公告
			fasongliebiaoPage.add(title, content);

			// 公告添加完成之后，输入标题，点击查询
			fasongliebiaoPage.selectByTitle(title);
			// 将查询出来的结果，提取该名称
			String newTitle = fasongliebiaoPage.getTitle();
			// 将新增的标题与查询出来的标题一致，则说明新增成功。
			Assert.assertEquals(newTitle, title, "通知公告新增不成功");
			
		} catch (Exception e) {
			takeScreenShot("D:\\主页面.jpg");
			takeHtmlDump("D:\\error.html");
			throw e;
		}

	}

	@AfterTest
	public void afterTest() {

	}
}
