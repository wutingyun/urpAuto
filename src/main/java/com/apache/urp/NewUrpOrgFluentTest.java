package com.apache.urp;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import com.apache.urp.aop.OperationAopPage;
import com.apache.urp.aop.WaitHandler;
import com.apache.urp.newpage.LoginPage;
import com.apache.urp.newpage.MainPage;
import com.apache.urp.newpage.YongHuManagePage;
import com.apache.urp.newpage.ZuZhiJiaGouPage;
import com.apache.urp.operation.IOperation;
import com.apache.urp.util.Account;

public class NewUrpOrgFluentTest extends BaseFluentTest {

	public Account currentAccount;

	@Page
	protected LoginPage loginPage;// 登录页面
	@Page
	protected MainPage mainPage;// 主页面
	@Page
	protected ZuZhiJiaGouPage zuZhiJiaGouPage;// 组织架构页面
	@Page
	protected YongHuManagePage yongHuManagePage;// 组织架构页面

	protected void checkOrLogin(Account account) {
		if (currentAccount == null || !account.equals(currentAccount)) {
			goTo(loginPage);
			loginPage.login(account);
			currentAccount = account;
		}
	}

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
			String newZuZhiMingChengedit = zuZhiJiaGouPage.getZuzhimingcheng();// 将查询出来的结果，提取该组织名称
			Assert.assertEquals(newZuZhiMingChengedit, orgNameNewName, "组织机构编辑不成功！");

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

	// 全局页面操作间隔 切面方法
	@BeforeMethod
	public void waitAop() {
		List<Field> fieldList = new ArrayList<>();
		Class<?> tempClass = this.getClass();
		while (tempClass != null) {// 当父类为null的时候说明到达了最上层的父类(Object类).
			fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
			tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
		}

		for (Field field : fieldList) {
			field.setAccessible(true);
			;
			if (field.getType().getSuperclass() == OperationAopPage.class) {
				try {
					OperationAopPage page = (OperationAopPage) field.get(this);

					IOperation operation = page.getOperation();

					ClassLoader loader = operation.getClass().getClassLoader();
					// 获得目标对象所实现的接口的Class对象
					Class<?>[] interfaces = operation.getClass().getInterfaces();
					// 创建一个Handler对象，处理扩展业务
					WaitHandler handler = new WaitHandler(operation);
					/**
					 * 通过Proxy.newProxyInstance方法获得代理对象
					 * 
					 * @param loader     目标对象的类加载器对象
					 * @param interfaces 目标对象实现的所有接口的Class对象数组
					 * @param handler    处理扩展业务的对象
					 */
					page.setOperation((IOperation) Proxy.newProxyInstance(loader, interfaces, handler));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}