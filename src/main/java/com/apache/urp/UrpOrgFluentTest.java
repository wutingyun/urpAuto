package com.apache.urp;

import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;

import com.apache.urp.page.LoginPage;
import com.apache.urp.page.MainPage;
import com.apache.urp.page.UserManagePage;
import com.apache.urp.page.UserZuManagePage;
import com.apache.urp.page.ZuZhiJiaGouPage;
import com.apache.urp.util.Account;

public class UrpOrgFluentTest extends BaseFluentTest {

	// 后台管理>>组织架构模块

	public Account currentAccount;

	@Page
	protected LoginPage loginPage;// 登录页面
	@Page
	protected MainPage mainPage;// 主页面
	@Page
	protected ZuZhiJiaGouPage zuZhiJiaGouPage;// 组织架构页面
	@Page
	protected UserManagePage userManagePage;// 用户管理页面
	@Page
	protected UserZuManagePage userZuManagePage;// 用户组管理页面

	/**
	 * 检测当前账号是否为待登录账号。当当前账号为空或者不等于待登录账号，去打开登录页面，并登录；
	 * 
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
	 * 组织架构模块：组织架构新增
	 * 
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
	 * 组织架构模块：组织架构新增
	 * 
	 * @param account
	 * @param orgName
	 * @param telnumber
	 * @param address
	 */
	public String addZuZhiJiaGou01(Account account, String orgName, String telnumber, String address) {
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
			String newOrgName = zuZhiJiaGouPage.getOrgName();
			System.out.println(orgName + "=" + newOrgName);

			// 新增的组织与查询出来的组织，比较标题是否一致，若一致，则说明新增成功。
			Assert.assertEquals(newOrgName, orgName, "组织名称新增不成功！");

			return newOrgName;

		} catch (Exception e) {

			e.printStackTrace();
			takeScreenShot("D:\\新增组织架构.jpg");
			takeHtmlDump("D:\\新增组织架构.html");
			throw e;
		}

	}

	/**
	 * 组织架构模块：组织架构编辑
	 * 
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
	 * 组织架构模块：组织架构删除
	 * 
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

			System.out.println("删除之后的查询结果" + "=" + newOrgNameEdit);
			Assert.assertNull(newOrgNameEdit, "删除不成功");

		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot("D:\\删除组织架构.jpg");
			takeHtmlDump("D:\\删除组织架构.html");
			throw e;
		}

	}

	/**
	 * 组织架构模块：组织授权
	 * 
	 * @param account
	 * @param orgName
	 */
	public void grantZuZhiJiaGou(Account account, String orgName) {
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

	/**
	 * 用户管理模块： 针对组织架构，为其新增用户 ---通过循环的方式，查找到组织架构为newOrgName的，并且点击它，在该组织下新增用户
	 * 
	 * @param account
	 * @param orgName
	 * @param username
	 * @param zhiwu
	 * @param telnumber
	 */
	public void addUser(Account account, String newOrgName, String username, String zhiwu, String telnumber) {
		checkOrLogin(account);
		// 在主页面，点击一级菜单后台管理，点击二级菜单用户管理。
		mainPage.clickHouTaiManage();
		mainPage.clickUserManage();
		// 在组织架构页面，点击树“组织架构”
		userManagePage.clickZuZhiJiaGou_shu();

		userManagePage.selectOrg(newOrgName);

		// 通过循环的方式，查找到组织架构为newOrgName的，并且点击它，在该组织下新增用户

	}

	/**
	 * 用户组模块：新增用户组
	 * 
	 * @param account
	 * @param userZuName
	 * @param telnumber
	 * @param address
	 * @param youzhengbianma
	 * @param beizhu
	 */
	public void addUserZu(Account account, String userZuName, String telnumber, String address, String youzhengbianma,
			String beizhu) {
		try {
			checkOrLogin(account);
			// 在主页面，点击一级菜单后台管理，点击二级菜单用户组 管理。
			mainPage.clickHouTaiManage();
			mainPage.clickUserZuManage();
			// 在组织架构页面，点击树“用户组”
			userZuManagePage.clickUserZu_shu();
			// 点击“新增组
			userZuManagePage.add(userZuName, telnumber, address, youzhengbianma, beizhu);

			// 用户组添加完成之后，输入“用户组名称”，点击“查询”
			userZuManagePage.selectByUserZuName(userZuName);

			// 提取查询出来的结果
			String newUserZuName = userZuManagePage.getUserZuName();
			// 将查询出来的结果与输入的数据做比较
			Assert.assertEquals(newUserZuName, userZuName, "用户组新增不成功！");
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot("D:\\新增用户组.jpg");
			takeHtmlDump("D:\\新增用户组.html");
			throw e;
		}
	}

	/**
	 * 用户组管理模块：用户组编辑
	 * 
	 * @param account
	 * @param userZuName
	 */
	public void editUserZu(Account account, String userZuName,String userZuNameEdit) {
		try {
			checkOrLogin(account);
			// 在主页面，点击一级菜单后台管理，点击二级菜单用户组 管理。
			mainPage.clickHouTaiManage();
			mainPage.clickUserZuManage();
			// 在组织架构页面，点击树“用户组”
			userZuManagePage.clickUserZu_shu();
			// 用户组添加完成之后，输入“用户组名称”，点击“查询”
			userZuManagePage.selectByUserZuName(userZuName);
			//点击“编辑”
			userZuManagePage.clickEdit();
			userZuManagePage.edit(userZuNameEdit);
			// 验证是否编辑成功
			userZuManagePage.selectByUserZuName(userZuNameEdit);
			String newUserZuNameEdit = userZuManagePage.getUserZuName();
			Assert.assertEquals(newUserZuNameEdit, userZuNameEdit, "用户组编辑不成功！");
			
		} catch (Exception e) {
			e.printStackTrace();
			takeScreenShot("D:\\编辑用户组.jpg");
			takeHtmlDump("D:\\编辑用户组.html");
			throw e;
		}
	}
	
	/**
	 * 用户组管理模块：用户组删除
	 * @param account
	 * @param userZuNameEdit
	 */
	public void deleteUserZu(Account account,String userZuNameEdit){
		try{
			checkOrLogin(account);
			// 在主页面，点击一级菜单后台管理，点击二级菜单用户组 管理。
			mainPage.clickHouTaiManage();
			mainPage.clickUserZuManage();
			// 在组织架构页面，点击树“用户组”
			userZuManagePage.clickUserZu_shu();
			// 用户组添加完成之后，输入“用户组名称”，点击“查询”
			userZuManagePage.selectByUserZuName(userZuNameEdit);
			//点击“删除”
			userZuManagePage.clickDelete();
			// 验证是否删除成功
			userZuManagePage.selectByUserZuName(userZuNameEdit);
			String newUserZuNameEdit = userZuManagePage.getUserZuName();
			Assert.assertNull(newUserZuNameEdit, "删除不成功");
			
		}catch (Exception e) {
			e.printStackTrace();
			takeScreenShot("D:\\删除用户组.jpg");
			takeHtmlDump("D:\\删除用户组.html");
			throw e;
		}
	}
	

	/**
	 * 系统退出至登录页面
	 */
	public void logout() {
		// 退出
		mainPage.tuichu();
		currentAccount = null;
	}

}
