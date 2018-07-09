package com.apache.urp.page;

import java.util.concurrent.TimeUnit;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

@PageUrl("/")
public class FaSongLieBiaoPage extends FluentPage {

	@FindBy(xpath = "//*[@id=\"divmaincontent\"]/section/div/div/div/div[1]/a")
	private FluentWebElement clickAddButton; // 在发送列表页面，点击 新增 按钮
	
	@FindBy(id = "title")
	private FluentWebElement inputTitle; // 在通知公告添加页面，新增标题。
	
	@FindBy(id = "content")
	private FluentWebElement inputContent;// 在通知公告添加页面，新增内容。
	
	@FindBy(css = ".input-group-addon")
	private FluentWebElement clickSelectAccepter;//在通知公告添加页面，在接收者处,点击“选择”。
	
	@FindBy(id = "treeUlSub_1_span")
	private FluentWebElement SelectAccepters;//在选择接收者页面，点击第一个“所有人”。
	
	@FindBy(xpath = "//*[@id=\"mymodalbody\"]/section/div[2]/button")
	private FluentWebElement confirmSelectAccepters;//在选择接收者页面，点击第一个“所有人”后，点击“确定”。
	
	@FindBy(xpath = "//*[@id=\"defaultForm\"]/div[3]/button")
	private FluentWebElement clickKeep;//在通知公告添加页面，点击“保存”。
	
	@FindBy(name = "sname")
	private FluentWebElement inputSname;// 在发送列表页面，右上角处查询框输入标题
	
	@FindBy(xpath = "//*[@id=\"seachForm\"]/div/div/button")
	private FluentWebElement search;// 在发送列表页面，右上角处查询框输入标题后，点击查询
	
	@FindBy(xpath = "//*[@id=\"divmaincontent\"]/section/div/div/div/div[2]/div/table/tbody/tr[2]/td[2]")
	private FluentWebElement getTitle; // 在发送列表页面，获取标题
	
	@FindBy(xpath = "//*[@id=\"divmaincontent\"]/section/div/div/div/div[2]/div/table/tbody/tr[2]/td[4]")
	private FluentWebElement getStatus;// 在发送列表页面，获取状态
	
	@FindBy(xpath = "//*[@id=\"divmaincontent\"]/section/div/div/div/div[2]/div/table/tbody/tr[2]/td[6]/a")
	private FluentWebElement clickChaKanButton;// 在发送列表页面，点击 查看 按钮
	
	@FindBy(id = "mymodal")
	private FluentWebElement addDialog;// 在发送列表页面，右上角处查询框输入标题后，点击查询
	
	
	/**
	 * 新增通知公告
	 * @param title
	 * @param content
	 * @return
	 */
	public FaSongLieBiaoPage add(String title, String content) {
		clickAddButton.click();
		inputTitle.write(title);
		inputContent.write(content);
		clickSelectAccepter.click();
		SelectAccepters.click();
		confirmSelectAccepters.click();
		await().atMost(5, TimeUnit.SECONDS).until(addDialog).not().displayed();
		clickKeep.click();
		return this;
	}

	/**
	 * 通过公告的名称，进行查询
	 * @param title
	 */
	public void selectByTitle(String title) {
		inputSname.write(title);
		search.click();
	}
	
	public String getTitle() {
		return getTitle.text();
	}

	

}
