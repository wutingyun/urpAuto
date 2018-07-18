package com.apache.urp.operation;

import org.fluentlenium.core.domain.FluentWebElement;

public class OperationImpl implements IOperation {

	/**
	 * 动作接口的实现类
	 */
	@Override
	public void handlerWrite(FluentWebElement input, String value) {
		input.clear();
		input.write(value);
	}

	@Override
	public void handlerClear(FluentWebElement input) {
		input.clear();
	}

	@Override
	public void handlerClick(FluentWebElement element) {
		element.click();
	}

}
