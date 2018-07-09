package com.apache.urp.page;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

public class ZuZhiJiaGouPage  extends FluentPage  {
	
	@FindBy(css = ".treeUl_1_span")
	private FluentWebElement zuzhijiagou_shu;   //树“组织架构”
	
	@FindBy(xpath= "//*[@id=\"orgContent\"]/section/div/div/div/div[1]/a[1]")
	private FluentWebElement addZuzhijiagou;   //新增组织架构
	
	@FindBy(css = ".name")
	private FluentWebElement name;   //在新增组织架构页面，输入名称处
	@FindBy(css = ".tel")
	private FluentWebElement tel;   //在新增组织架构页面，输入电话号码处
	@FindBy(css = ".address")
	private FluentWebElement dizhi;   //在新增组织架构页面，输入地址处
	@FindBy(className = "btn btn-primary")
	private FluentWebElement baocun;   //在新增组织架构页面，点击保存处
	
	@FindBy(css = "#sname")
	private FluentWebElement sname; //右上角的查询框处
	@FindBy(className = "btn btn-sm btn-default")
	private FluentWebElement selectBtn; //点击查询按钮处
	
	@FindBy(xpath = "//*[@id=\"orgContent\"]/section/div/div/div/div[2]/div/table/tbody/tr[2]/td[2]")
	private FluentWebElement getZuzhimingcheng; //在查询结果处，提取组织机构名称处
	
	@FindBy(xpath = "//*[@id=\"orgContent\"]/section/div/div/div/div[2]/div/table/tbody/tr[2]/td[5]/a[2]")
	private FluentWebElement editBtn; //针对某组织架构，点击编辑按钮处。
	
	@FindBy(xpath = "//*[@id=\"orgContent\"]/section/div/div/div/div[2]/div/table/tbody/tr[2]/td[5]/a[3]")
	private FluentWebElement deleteBtn; //针对某组织架构，点击删除按钮处。
	 
	
	/**
	 * 在组织架构页面，点击树“组织架构”
	 */
	public void clickZuZhiJiaGou_shu() {
		zuzhijiagou_shu.click();
	}
	
	/**
	 * 新增组织架构 功能
	 * @param zuzhimingcheng  组织机构名称
	 * @param telnumber 组织机构电话
	 * @param address  组织机构地址
	 */
	public void add(String zuzhimingcheng, String telnumber, String address) {
		addZuzhijiagou.click();  //点击“新增组织架构”
		name.write(zuzhimingcheng);  //在新增组织架构页面，输入组织名称
		tel.write(telnumber);//在新增组织架构页面，输入组织电话
		dizhi.write(address);//在新增组织架构页面，输入组织地址
		baocun.click();//保存
	}

	/**
	 * 根据组织机构名称来查询
	 * @param zuzhimingcheng
	 */
	public void selectByZuzhimingcheng(String zuzhimingcheng) {
		sname.write(zuzhimingcheng);
		selectBtn.click();
	}
	
	/**
	 * 提取该组织名称
	 * @return
	 */
	public String getZuzhimingcheng() {
		String newZuzhijigou=getZuzhimingcheng.text();
		return newZuzhijigou;
	}

	/**
	 * 点击编辑
	 */
	public void clickEdit() {
		editBtn.click();
	}
	
	/**
	 * 编辑组织架构页面的组织机构名称
	 * @param zuzhimingcheng2
	 */
	public void edit(String zuzhimingcheng2) {
		name.clear(); //清除机构名称内容
		name.write(zuzhimingcheng2); //输入修正的机构名称
		
	}

	/**
	 * 删除组织架构
	 */
	public void clickDelete() {
		deleteBtn.click();
		
	}

	

	

	
}
