package com.apache.urp.page;

import java.util.concurrent.TimeUnit;

import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import com.apache.urp.aop.OperationAopPage;

public class ZuZhiJiaGouPage extends OperationAopPage {

	@FindBy(css = "#treeUl_1_a")
	private FluentWebElement zuzhijiagou_shu; // 树“组织架构”

	@FindBy(xpath = "//*[@id=\"orgContent\"]/section/div/div/div/div[1]/a[1]")
	private FluentWebElement addZuzhijiagou; // 新增组织架构

	@FindBy(css = "#name")
	private FluentWebElement name; // 在新增组织架构页面，输入名称处
	@FindBy(css = "#tel")
	private FluentWebElement tel; // 在新增组织架构页面，输入电话号码处
	@FindBy(css = "#address")
	private FluentWebElement dizhi; // 在新增组织架构页面，输入地址处
	@FindBy(css = "#defaultOrgForm > div.box-footer > button")
	private FluentWebElement baocun; // 在新增组织架构页面，点击保存处

	@FindBy(css = "#seachOrgForm > div > input")
	private FluentWebElement sname; // 右上角的查询框处
	@FindBy(css = "#seachOrgForm > div > div > button")
	private FluentWebElement selectBtn; // 点击查询按钮处

	@FindBy(xpath = "//*[@id=\"orgContent\"]/section/div/div/div/div[2]/div/table/tbody/tr[2]/td[2]")
	private FluentWebElement getOrgName; // 在查询结果处，提取组织机构名称处

	@FindBy(xpath = "//*[@id=\"orgContent\"]/section/div/div/div/div[2]/div/table/tbody/tr[2]/td[5]/a[2]")
	private FluentWebElement editBtn; // 针对某组织架构，点击编辑按钮处。

	@FindBy(xpath = "//*[@id=\"orgContent\"]/section/div/div/div/div[2]/div/table/tbody/tr[2]/td[5]/a[3]")
	private FluentWebElement deleteBtn; // 针对某组织架构，点击删除按钮处。

	/**
	 * 在组织架构页面，点击树“组织架构”
	 */
	public void clickZuZhiJiaGou_shu() {
		operation.handlerClick(zuzhijiagou_shu);
	}

	/**
	 * 新增组织架构 功能
	 * 
	 * @param zuzhimingcheng
	 *            组织机构名称
	 * @param telnumber
	 *            组织机构电话
	 * @param address
	 *            组织机构地址
	 */
	public void add(String zuzhimingcheng, String telnumber, String address) {
		operation.handlerClick(addZuzhijiagou); // 点击“新增组织架构”
		operation.handlerWrite(name, zuzhimingcheng); // 在新增组织架构页面，输入组织名称
		operation.handlerWrite(tel, telnumber); // 在新增组织架构页面，输入组织电话
		operation.handlerWrite(dizhi, address); // 在新增组织架构页面，输入组织地址
		operation.handlerClick(baocun);// 保存
	}

	/**
	 * 根据组织机构名称来查询
	 * 
	 * @param zuzhimingcheng
	 */
	public void selectByZuzhimingcheng(String zuzhimingcheng) {
		operation.handlerWrite(sname, zuzhimingcheng);
		// sname.fill().with(zuzhimingcheng);
		await().atMost(3, TimeUnit.SECONDS).until(sname).value(zuzhimingcheng);// 等待搜索框弹出

		operation.handlerClick(selectBtn);
	}

	/**
	 * 提取该组织名称
	 * 
	 * @return
	 */
	public String getOrgName() {
		String newZuzhijigou;
		try {
			newZuzhijigou = getOrgName.text();
			return newZuzhijigou;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 点击编辑
	 */
	public void clickEdit() {
		operation.handlerClick(editBtn);
	}

	/**
	 * 编辑组织架构页面的组织机构名称
	 * 
	 * @param zuzhimingcheng2
	 */
	public void edit(String zuzhimingcheng2) {
		operation.handlerWrite(name, zuzhimingcheng2); // 输入修正的机构名称
		operation.handlerClick(baocun);// 保存
	}

	/**
	 * 删除组织架构
	 */
	public void clickDelete() {
		operation.handlerClick(deleteBtn);
	}

	/**
	 * 
	 */
	public void clickGrant() {

	}

}
