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
public class ZuzhijiagouCase01 extends BaseFluentTest {
	/**
	 * 系统管理>>组织架构，组织架构的新增、编辑、删除
	 */

	List<Account> accounts;
	String zuzhimingcheng; // 组织名称
	String telnumber; // 电话
	String address; // 地址
	String zuzhimingchengedit;

	@Page
	private LoginPage loginPage;// 登录页面
	@Page
	private MainPage mainPage;// 主页面
	@Page
	private ZuZhiJiaGouPage zuZhiJiaGouPage;// 组织架构页面
	@Page
	private YongHuManagePage yongHuManagePage;// 组织架构页面

	@BeforeTest
	public void beforeTest() {

		accounts = AccountRead.getAll();// 获取数据，用户名和密码
		zuzhimingcheng = "测试组织名称" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
		telnumber = TelNumber.getTel();
		address = "测试组织地址" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();
		zuzhimingchengedit = "测试组织名称编辑" + CommonRandUtil.getDate() + "-" + CommonRandUtil.getRam();

	}

	/**
	 * 组织架构新增
	 */
	@Test
	public void addZuZhiJiaGou() {
		try {
			// 通过登录页面，输入用户名和密码，进入主页面。
			goTo(loginPage);
			loginPage.login(accounts.get(0));

			// 在主页面，点击一级菜单后台管理，点击二级菜单组织架构。
			mainPage.clickHouTaiManage();
			mainPage.clickZuZhiJiaGou();

			// 在组织架构页面，点击树“组织架构”
			zuZhiJiaGouPage.clickZuZhiJiaGou_shu();
			// 在组织架构页面，新增组织。
			zuZhiJiaGouPage.add(zuzhimingcheng, telnumber, address);

			// 组织添加完成之后，输入“组织机构名称”，点击“查询”
			zuZhiJiaGouPage.selectByZuzhimingcheng(zuzhimingcheng);
			// 将查询出来的结果，提取该组织名称
			String newZuZhiMingCheng = zuZhiJiaGouPage.getZuzhimingcheng();
			// 新增的组织与查询出来的组织，比较标题是否一致，若一致，则说明新增成功。
			Assert.assertEquals(newZuZhiMingCheng, zuzhimingcheng, "组织名称新增不成功！");

		} catch (Exception e) {
			takeScreenShot("D:\\主页面.jpg");
			takeHtmlDump("D:\\error.html");
			throw e;
		}

	}

	/**
	 * 编辑组织架构
	 */

	@Test
	public void editZuZhiJiaGou() {
		try {
			// 通过登录页面，输入用户名和密码，进入主页面。
			goTo(loginPage);
			loginPage.login(accounts.get(0));

			// 在主页面，点击一级菜单后台管理，点击二级菜单组织架构。
			mainPage.clickHouTaiManage();
			mainPage.clickZuZhiJiaGou();

			// 在组织架构页面，点击树“组织架构”
			zuZhiJiaGouPage.clickZuZhiJiaGou_shu();

			// 组织添加完成之后，输入“组织机构名称”，点击“查询”
			zuZhiJiaGouPage.selectByZuzhimingcheng(zuzhimingcheng);

			// 编辑组织架构
			zuZhiJiaGouPage.clickEdit();
			zuZhiJiaGouPage.edit(zuzhimingchengedit);

			// 验证是否编辑成功
			zuZhiJiaGouPage.selectByZuzhimingcheng(zuzhimingchengedit);// 组织添加完成之后，输入“组织机构名称”，点击“查询”
			String newZuZhiMingChengedit = zuZhiJiaGouPage.getZuzhimingcheng();// 将查询出来的结果，提取该组织名称
			Assert.assertEquals(newZuZhiMingChengedit, zuzhimingchengedit, "组织机构编辑不成功！");

		} catch (Exception e) {
			takeScreenShot("D:\\主页面.jpg");
			takeHtmlDump("D:\\error.html");
			throw e;
		}

	}

	@Test
	public void deleteZuZhiJiaGou() {
		try {
			// 通过登录页面，输入用户名和密码，进入主页面。
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
			zuZhiJiaGouPage.selectByZuzhimingcheng(zuzhimingchengedit);// 输入“组织机构名称”，点击“查询”
			String newZuZhiMingChengedit = zuZhiJiaGouPage.getZuzhimingcheng();// 将查询出来的结果，提取该组织名称
			Assert.assertNull(newZuZhiMingChengedit, "删除不成功");

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
