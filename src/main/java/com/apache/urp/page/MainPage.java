package com.apache.urp.page;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

@PageUrl("/")
public class MainPage extends FluentPage {
	@FindBy(xpath = "/html/body/div[1]/aside/section/ul/li[4]/a/span")
	private FluentWebElement clickTongZhiGongGao; // 点击一级菜单 通知公告
	
	@FindBy(xpath = "/html/body/div[1]/aside/section/ul/li[4]/ul/li[2]/a")
	private FluentWebElement clickFaSongLieBiao;// 点击二级菜单 发送列表

	@Page
	private FaSongLieBiaoPage fasongliebiaoPage; // 通知公告>>发送列表页面

	public MainPage clickTongZhiGongGao() {
		clickTongZhiGongGao.click();
		return this;
	}
	
	
	/*public void awaitUntilDisplay(FluentWebElement fluentWebElement) {
		 await().atMost(5, TimeUnit.SECONDS).until(fluentWebElement).displayed();
	}*/

	public void waitSec(int sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public FaSongLieBiaoPage clickFaSongLieBiao() {
		clickFaSongLieBiao.click();
		return fasongliebiaoPage;
	}

}
