package com.apache.urp.operation;

import org.fluentlenium.core.domain.FluentWebElement;

/**
 * 操作集合类
 * 
 * @author huenbin
 *
 */
public interface IOperation {

	public void handlerWrite(FluentWebElement input, String value);

	public void handlerClear(FluentWebElement input);

	public void handlerClick(FluentWebElement element);
}
