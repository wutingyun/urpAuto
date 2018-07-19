package com.apache.urp.page;

import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import com.apache.urp.aop.OperationAopPage;

@PageUrl("/")
public class MainPage extends OperationAopPage {

	@FindBy(css = "body > div.wrapper > aside > section > ul > li:nth-child(2)")
	// @FindBy(xpath = "/html/body/div[1]/aside/section/ul/li[2]/a/span")
	private FluentWebElement clickHouTaiManage; // 点击一级菜单 后台管理

	// body > div.wrapper > aside > section > ul > li.treeview.active > ul >
	// li.active > a
	@FindBy(css = "body > div.wrapper > aside > section > ul > li:nth-child(2) > ul > li:nth-child(2) > a")
	// @FindBy(xpath = "/html/body/div[1]/aside/section/ul/li[2]/ul/li[2]/a")
	private FluentWebElement clickZuZhiJiaGou;// 点击二级菜单 组织架构

	@FindBy(xpath = "/html/body/div[1]/aside/section/ul/li[2]/ul/li[3]/a")
	private FluentWebElement clickUserManage;// 点击二级菜单 用户管理

	@FindBy(css = "body > div.wrapper > header > nav > div > ul > li > a")
	private FluentWebElement clickZhangHao;// 点击首页面的右上角账号
	@FindBy(css = "body > div.wrapper > header > nav > div > ul > li > ul > li.user-footer > div.pull-right > a")
	private FluentWebElement clickTuiChu;// 点击首页面的右上角账号后，点击退出

	@Page
	private ZuZhiJiaGouPage zuZhiJiaGouPage; // 组织架构>>组织架构页面
	@Page
	private UserManagePage userManagePage; // 用户管理>>用户管理页面

	/**
	 * 点击后台管理，进入后台管理模块
	 * 
	 * @return
	 */
	public MainPage clickHouTaiManage() {
		String css = clickHouTaiManage.attribute("class");
	//	System.out.println(css);
		if (!css.contains("active")) {
			operation.handlerClick(clickHouTaiManage);
		}
		return this;
	}

	/**
	 * 进入后台管理模块后， 点击组织架构，进入组织架构页面
	 * 
	 * @return
	 */
	public ZuZhiJiaGouPage clickZuZhiJiaGou() {
		operation.handlerClick(clickZuZhiJiaGou);
		return zuZhiJiaGouPage;
	}

	/**
	 * 进入后台管理模块后，点击用户管理，进入用户管理页面
	 * 
	 * @return
	 */
	public UserManagePage clickUserManage() {
		operation.handlerClick(clickUserManage);
		return userManagePage;
	}

	/**
	 * 退出主页面
	 */
	public void tuichu() {
		operation.handlerClick(clickZhangHao);
		operation.handlerClick(clickTuiChu);
	}

}
