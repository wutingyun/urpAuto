package com.apache.urp;

import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;

import com.apache.urp.page.LoginPage;
import com.apache.urp.page.MainPage;
import com.apache.urp.page.YongHuManagePage;
import com.apache.urp.page.ZuZhiJiaGouPage;
import com.apache.urp.util.Account;

public class UrpOrgFluentTest extends BaseFluentTest {

	public Account currentAccount;

	@Page
	protected LoginPage loginPage;// 登录页面
	@Page
	protected MainPage mainPage;// 主页面
	@Page
	protected ZuZhiJiaGouPage zuZhiJiaGouPage;// 组织架构页面
	@Page
	protected YongHuManagePage yongHuManagePage;// 组织架构页面

	protected void waitMillis(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void checkOrLogin(Account account) {
		if (currentAccount == null || !account.equals(currentAccount)) {
			goTo(loginPage);
			loginPage.login(account);
			currentAccount = account;
		}
	}

	public void addZuZhiJiaGou(Account account, String orgName,
			String telnumber, String address) {
		try {
			checkOrLogin(account);
			// 在主页面，点击一级菜单后台管理，点击二级菜单组织架构。
			mainPage.clickHouTaiManage();
			mainPage.clickZuZhiJiaGou();

			// 在组织架构页面，点击树“组织架构”
			zuZhiJiaGouPage.clickZuZhiJiaGou_shu();
			// 在组织架构页面，新增组织。
			zuZhiJiaGouPage.add(orgName, telnumber, address);

			// 组织添加完成之后，输入“组织机构名称”，点击“查询”
			zuZhiJiaGouPage.selectByZuzhimingcheng(orgName);

			waitMillis(100);

			// 将查询出来的结果，提取该组织名称
			String newZuZhiMingCheng = zuZhiJiaGouPage.getZuzhimingcheng();
			System.out.println(orgName + "=" + newZuZhiMingCheng);

			// 新增的组织与查询出来的组织，比较标题是否一致，若一致，则说明新增成功。
			Assert.assertEquals(newZuZhiMingCheng, orgName, "组织名称新增不成功！");

		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot("D:\\主页面.jpg");
			takeHtmlDump("D:\\error.html");
			throw e;
		}

	}

	public void editZuZhiJiaGou(Account account, String orgName,
			String orgNameNewName) {
		try {
			checkOrLogin(account);
			// 在主页面，点击一级菜单后台管理，点击二级菜单组织架构。
			mainPage.clickHouTaiManage();
			mainPage.clickZuZhiJiaGou();
			waitMillis(100);
			// 在组织架构页面，点击树“组织架构”
			zuZhiJiaGouPage.clickZuZhiJiaGou_shu();
			waitMillis(100);
			// 组织添加完成之后，输入“组织机构名称”，点击“查询”
			zuZhiJiaGouPage.selectByZuzhimingcheng(orgName);
			waitMillis(200);
			// 编辑组织架构 
			zuZhiJiaGouPage.clickEdit();
			zuZhiJiaGouPage.edit(orgNameNewName);
			// 验证是否编辑成功
			waitMillis(100);
			zuZhiJiaGouPage.selectByZuzhimingcheng(orgNameNewName);// 组织添加完成之后，输入“组织机构名称”，点击“查询”
			waitMillis(100);
			String newZuZhiMingChengedit = zuZhiJiaGouPage.getZuzhimingcheng();// 将查询出来的结果，提取该组织名称
			Assert.assertEquals(newZuZhiMingChengedit, orgNameNewName,
					"组织机构编辑不成功！");

		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot("D:\\主页面.jpg");
			takeHtmlDump("D:\\error.html");
			throw e;
		}
	}

	public void logout() {
		// 退出
		mainPage.tuichu();
		currentAccount = null;
	}

}
