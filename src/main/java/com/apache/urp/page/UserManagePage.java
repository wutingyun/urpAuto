package com.apache.urp.page;

import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import com.apache.urp.aop.OperationAopPage;

public class UserManagePage extends OperationAopPage {

	@FindBy(css = "treeUl_1_span")
	private FluentWebElement zuzhijiagou_shu; // 树“组织架构”

	/**
	 * 在用户管理页面，点击树“组织架构”
	 */
	public void clickZuZhiJiaGou_shu() {
		operation.handlerClick(zuzhijiagou_shu);
	}

}
