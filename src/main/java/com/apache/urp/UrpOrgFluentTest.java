package com.apache.urp;

import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;

import com.apache.urp.page.LoginPage;
import com.apache.urp.page.MainPage;
import com.apache.urp.page.YongHuManagePage;
import com.apache.urp.page.ZuZhiJiaGouPage;
import com.apache.urp.util.Account;

public class UrpOrgFluentTest extends BaseFluentTest {

	//后台管理>>组织架构模块
	
	public Account currentAccount;

	@Page
	protected LoginPage loginPage;// 登录页面
	@Page
	protected MainPage mainPage;// 主页面
	@Page
	protected ZuZhiJiaGouPage zuZhiJiaGouPage;// 组织架构页面
	@Page
	protected YongHuManagePage yongHuManagePage;// 组织架构页面

	/**
	 * 检测当前账号是否为待登录账号。当当前账号为空或者不等于待登录账号，去打开登录页面，并登录；
	 * @param account
	 */
	protected void checkOrLogin(Account account) {
		if (currentAccount == null || !account.equals(currentAccount)) {
			goTo(loginPage);
			loginPage.login(account);
			currentAccount = account;
		}
	}

	/**
	 * 组织架构新增
	 * @param account
	 * @param orgName
	 * @param telnumber
	 * @param address
	 */
	public void addZuZhiJiaGou(Account account, String orgName, String telnumber, String address) {
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

			// 将查询出来的结果，提取该组织名称
			String newZuZhiMingCheng = zuZhiJiaGouPage.getOrgName();
			System.out.println(orgName + "=" + newZuZhiMingCheng);

			// 新增的组织与查询出来的组织，比较标题是否一致，若一致，则说明新增成功。
			Assert.assertEquals(newZuZhiMingCheng, orgName, "组织名称新增不成功！");

		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot("D:\\新增组织架构.jpg");
			takeHtmlDump("D:\\新增组织架构.html");
			throw e;
		}

	}
	

	/**
	 * 组织架构编辑
	 * @param account
	 * @param orgName
	 * @param orgNameNewName
	 */
	public void editZuZhiJiaGou(Account account, String orgName, String orgNameNewName) {
		try {
			checkOrLogin(account);
			// 在主页面，点击一级菜单后台管理，点击二级菜单组织架构。
			mainPage.clickHouTaiManage();
			mainPage.clickZuZhiJiaGou();
			// 在组织架构页面，点击树“组织架构”
			zuZhiJiaGouPage.clickZuZhiJiaGou_shu();
			// 组织添加完成之后，输入“组织机构名称”，点击“查询”
			zuZhiJiaGouPage.selectByZuzhimingcheng(orgName);
			// 编辑组织架构
			zuZhiJiaGouPage.clickEdit();
			zuZhiJiaGouPage.edit(orgNameNewName);
			// 验证是否编辑成功
			zuZhiJiaGouPage.selectByZuzhimingcheng(orgNameNewName);// 组织添加完成之后，输入“组织机构名称”，点击“查询”
			String newZuZhiMingChengedit = zuZhiJiaGouPage.getOrgName();// 将查询出来的结果，提取该组织名称
			
			System.out.println(orgNameNewName + "=" + newZuZhiMingChengedit);
			Assert.assertEquals(newZuZhiMingChengedit, orgNameNewName, "组织机构编辑不成功！");

		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot("D:\\编辑组织架构.jpg");
			takeHtmlDump("D:\\编辑组织架构.html");
			throw e;
		}
	}

	/**
	 * 组织架构删除
	 * @param account
	 * @param zuzhimingchengedit
	 */
	public void deleteZuZhiJiaGou(Account account, String orgNameEdit) {
		try {
			checkOrLogin(account);

			// 在主页面，点击一级菜单后台管理，点击二级菜单组织架构。
			mainPage.clickHouTaiManage();
			mainPage.clickZuZhiJiaGou();

			// 在组织架构页面，点击树“组织架构”
			zuZhiJiaGouPage.clickZuZhiJiaGou_shu();

			// 输入“组织机构名称”，点击“查询”
			zuZhiJiaGouPage.selectByZuzhimingcheng(orgNameEdit);

			// 删除组织架构
			zuZhiJiaGouPage.clickDelete();

			// 验证是否删除成功。
			zuZhiJiaGouPage.selectByZuzhimingcheng(orgNameEdit);

			// 输入“组织机构名称”，点击“查询”
			String newOrgNameEdit = zuZhiJiaGouPage.getOrgName();// 将查询出来的结果，提取该组织名称
			
			System.out.println("删除之后的查询结果"+ "=" + newOrgNameEdit);
			Assert.assertNull(newOrgNameEdit, "删除不成功");
			

		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot("D:\\删除组织架构.jpg");
			takeHtmlDump("D:\\删除组织架构.html");
			throw e;
		}

	}
	
	public void grantZuZhiJiaGou(Account account,String orgName){
		checkOrLogin(account);
		// 在主页面，点击一级菜单后台管理，点击二级菜单组织架构。
		mainPage.clickHouTaiManage();
		mainPage.clickZuZhiJiaGou();
		// 在组织架构页面，点击树“组织架构”
		zuZhiJiaGouPage.clickZuZhiJiaGou_shu();
		// 组织添加完成之后，输入“组织机构名称”，点击“查询”
		zuZhiJiaGouPage.selectByZuzhimingcheng(orgName);
		
		// 给某组织组织授权
		zuZhiJiaGouPage.clickGrant();
		
	}

	
	public void logout() {
		// 退出
		mainPage.tuichu();
		currentAccount = null;
	}

	

}
