package com.apache.urp.page;

import java.util.concurrent.TimeUnit;

import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import com.apache.urp.aop.OperationAopPage;

public class UserZuManagePage extends OperationAopPage {

	@FindBy(css = "#treeUl_1_span")
	private FluentWebElement userZu_shu; // 树“用户组”

	@FindBy(css = "#divrightcontent > section > div > div > div > div.box-header.with-border > a")
	private FluentWebElement addUserZuBtn; // 点击“新增用户组”按钮处

	@FindBy(css = "#name")
	private FluentWebElement userZuMingcheng; // 用户组名称
	@FindBy(css = "#tel")
	private FluentWebElement telephone; // 电话
	@FindBy(css = "#address")
	private FluentWebElement dizhi; // 地址
	@FindBy(css = "#placecode")
	private FluentWebElement placecode; // 邮政编码
	@FindBy(css = "#remark")
	private FluentWebElement remark; // 备注

	@FindBy(css = "#defaultForm > div.box-footer > button")
	private FluentWebElement baocun; // 点击“保存”按钮处

	@FindBy(name = "sname")
	private FluentWebElement sname; // 在用户组查询处	
	@FindBy(css = "#seachForm > div > div > button")
	private FluentWebElement selectBtn; // 点击“查询”处
	
	@FindBy(xpath = "//*[@id=\"divrightcontent\"]/section/div/div/div/div[2]/div/table/tbody/tr[2]/td[2]")
	private FluentWebElement getUserZuName; // 在查询结果的列表处，提取用户组名称
	
	@FindBy(xpath = "//*[@id=\"divrightcontent\"]/section/div/div/div/div[2]/div/table/tbody/tr[2]/td[5]/a[2]")
	private FluentWebElement bianji; // 编辑
	
	@FindBy(xpath = "//*[@id=\"divrightcontent\"]/section/div/div/div/div[2]/div/table/tbody/tr[2]/td[5]/a[3]")
	private FluentWebElement shanchu; // 删除
	
	
	/**
	 * 在用户组管理页面，点击树“用户组”
	 */
	public void clickUserZu_shu() {
		operation.handlerClick(userZu_shu);// 点击用户组树
	}

	/**
	 * 用户组管理模块：新增用户组
	 * 
	 * @param userZuName
	 * @param telnumber
	 * @param address
	 * @param youzhengbianma
	 * @param beizhu
	 */
	public void add(String userZuName, String telnumber, String address, String youzhengbianma, String beizhu) {
		operation.handlerClick(addUserZuBtn);// 新增用户组
		operation.handlerWrite(userZuMingcheng, userZuName);// 输入用户名
		operation.handlerWrite(telephone, telnumber);// 输入电话
		operation.handlerWrite(dizhi, address);// 输入地址
		operation.handlerWrite(placecode, youzhengbianma);// 输入 邮政编码
		operation.handlerWrite(remark, beizhu);// 输入 备注
		// 点击保存
		operation.handlerClick(baocun);
	}

	/**
	 * 用户组管理模块，通过用户组组，进行查询
	 * 
	 * @param userZuName
	 */
	public void selectByUserZuName(String userZuName) {
		// 在用户组查询处，输入用户组名称
		operation.handlerWrite(sname, userZuName);
		await().atMost(5, TimeUnit.SECONDS).until(sname).value(userZuName);// 等待搜索框弹出

		// 点击“查询”
		operation.handlerClick(selectBtn);

	}

	/**
	 * 通过查询结果，提取出新的用户组名称
	 * @return
	 */
	public String getUserZuName() {
		String newUserZuName;
		try {
			newUserZuName = getUserZuName.text();
			return newUserZuName;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 点击编辑按钮
	 */
	public void clickEdit() {
		// TODO Auto-generated method stub
		operation.handlerClick(bianji);
	}
	
	/**
	 * 编辑用户组
	 * @param userZuNameEdit
	 */
	public void edit(String userZuNameEdit) {
		// TODO Auto-generated method stub
		//输入修正之后的用户组名称
		operation.handlerWrite(userZuMingcheng, userZuNameEdit);
		//点击保存
		operation.handlerClick(baocun);
		
	}

	/**
	 * 删除用户组
	 */
	public void clickDelete() {
		operation.handlerClick(shanchu);
		
	}



}
