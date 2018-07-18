package com.apache.urpcase;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.fluentlenium.core.hook.wait.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.apache.urp.NewUrpOrgFluentTest;
import com.apache.urp.util.Account;
import com.apache.urp.util.AccountRead;
import com.apache.urp.util.CommonRandUtil;
import com.apache.urp.util.TelNumber;

@Wait(timeout = 5, timeUnit = TimeUnit.SECONDS)
public class NewZuzhijiagouCase01 extends NewUrpOrgFluentTest {
	/**
	 * 系统管理>>组织架构，组织架构的新增、编辑、删除
	 */
	List<Account> accounts;
	String zuzhimingcheng; // 组织名称
	String telnumber; // 电话
	String address; // 地址
	String zuzhimingchengedit;

	@BeforeTest
	public void beforeTest() {

		accounts = AccountRead.getAll();// 获取数据，用户名和密码
		zuzhimingcheng = "测试组织名称" + CommonRandUtil.getDate() + "-"
				+ CommonRandUtil.getRam();
		telnumber = TelNumber.getTel();
		address = "测试组织地址" + CommonRandUtil.getDate() + "-"
				+ CommonRandUtil.getRam();
		zuzhimingchengedit = "测试组织名称编辑" + CommonRandUtil.getDate() + "-"
				+ CommonRandUtil.getRam();

	}
	
	
	

	@Test
	public void zuZhiJiaGou() {

		addZuZhiJiaGou(accounts.get(0), zuzhimingcheng, telnumber, address);
		//logout();
		editZuZhiJiaGou(accounts.get(0), zuzhimingcheng, zuzhimingchengedit);
		logout();
	}
	



	//@Test
	public void deleteZuZhiJiaGou() {
		try {
			goTo(loginPage);
			loginPage.login(accounts.get(0));

			// 在主页面，点击一级菜单后台管理，点击二级菜单组织架构。
			mainPage.clickHouTaiManage();
			mainPage.clickZuZhiJiaGou();

			// 在组织架构页面，点击树“组织架构”
			zuZhiJiaGouPage.clickZuZhiJiaGou_shu();

			// 输入“组织机构名称”，点击“查询”
			zuZhiJiaGouPage.selectByZuzhimingcheng(zuzhimingchengedit);

			// 删除组织架构
			zuZhiJiaGouPage.clickDelete();

			// 验证是否删除成功。
			zuZhiJiaGouPage.selectByZuzhimingcheng(zuzhimingchengedit);

			// 输入“组织机构名称”，点击“查询”
			String newZuZhiMingChengedit = zuZhiJiaGouPage.getZuzhimingcheng();// 将查询出来的结果，提取该组织名称
			Assert.assertNull(newZuZhiMingChengedit, "删除不成功");

			// 退出
			mainPage.tuichu();

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
