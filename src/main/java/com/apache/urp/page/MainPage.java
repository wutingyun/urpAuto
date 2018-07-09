package com.apache.urp.page;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

@PageUrl("/")
public class MainPage extends FluentPage {
	@FindBy(xpath = "/html/body/div[1]/aside/section/ul/li[2]/a/span")
	private FluentWebElement clickHouTaiManage; // 点击一级菜单 后台管理
	
	@FindBy(xpath = "/html/body/div[1]/aside/section/ul/li[2]/ul/li[2]/a")
	private FluentWebElement clickZuZhiJiaGou;// 点击二级菜单 组织架构
	
	@FindBy(xpath = "/html/body/div[1]/aside/section/ul/li[2]/ul/li[3]/a")
	private FluentWebElement clickYongHuManage;// 点击二级菜单 用户管理
	
	@FindBy(xpath = "/html/body/div[1]/header/nav/div/ul/li/a/span")
	private FluentWebElement clickZhangHao;// 点击首页面的右上角账号
	@FindBy(className = "btn btn-default btn-flat")
	private FluentWebElement clickTuiChu;// 点击首页面的右上角账号后，点击退出

	@Page
	private ZuZhiJiaGouPage zuZhiJiaGouPage; // 组织架构>>组织架构页面
	@Page
	private YongHuManagePage yongHuManagePage; // 用户管理>>用户管理页面
	

	/**
	 * 点击后台管理，进入后台管理模块
	 * @return
	 */
	public MainPage clickHouTaiManage() {
		clickHouTaiManage.click();
		return this;
	}
	

	/**
	 * 进入后台管理模块后， 点击组织架构，进入组织架构页面
	 * @return
	 */
	public ZuZhiJiaGouPage clickZuZhiJiaGou() {
		clickZuZhiJiaGou.click();
		return zuZhiJiaGouPage;
	}
	
	/**
	 * 进入后台管理模块后，点击用户管理，进入用户管理页面
	 * @return
	 */
	public YongHuManagePage clickYongHuManage() {
		clickYongHuManage.click();
		return yongHuManagePage;
	}


	/**
	 * 退出主页面
	 */
	public void tuichu() {
		clickZhangHao.click();
		clickTuiChu.click();
		
	}

}
