package com.apache.urp;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fluentlenium.adapter.testng.FluentTestNg;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import com.apache.urp.aop.OperationAopPage;
import com.apache.urp.aop.WaitHandler;
import com.apache.urp.config.BrowserConfig;
import com.apache.urp.config.SeleniumBrowserConfigProperties;
import com.apache.urp.operation.IOperation;

public class BaseFluentTest extends FluentTestNg {

	SeleniumBrowserConfigProperties config = new SeleniumBrowserConfigProperties();

	@Override
	public WebDriver newWebDriver() {
		BrowserConfig browserConfig = getBrowserConfig();
		return browserConfig.resolveDriver(browserConfig);
	}

	@Override
	public String getBaseUrl() {
		return config.getPageUrl();
	}

	private BrowserConfig getBrowserConfig() {
		return config.getBrowserConfig();
	}

	// 全局页面操作间隔 切面方法
	@BeforeMethod
	public void waitAop() {
		List<Field> fieldList = new ArrayList<>();
		Class<?> tempClass = this.getClass();
		while (tempClass != null) {// 当父类为null的时候说明到达了最上层的父类(Object类).
			fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
			tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
		}

		for (Field field : fieldList) {
			field.setAccessible(true);
			if (field.getType().getSuperclass() == OperationAopPage.class) {
				try {
					OperationAopPage page = (OperationAopPage) field.get(this);

					IOperation operation = page.getOperation();

					ClassLoader loader = operation.getClass().getClassLoader();
					// 获得目标对象所实现的接口的Class对象
					Class<?>[] interfaces = operation.getClass()
							.getInterfaces();
					// 创建一个Handler对象，处理扩展业务
					WaitHandler handler = new WaitHandler(operation);
					/**
					 * 通过Proxy.newProxyInstance方法获得代理对象
					 * 
					 * @param loader
					 *            目标对象的类加载器对象
					 * @param interfaces
					 *            目标对象实现的所有接口的Class对象数组
					 * @param handler
					 *            处理扩展业务的对象
					 */
					page.setOperation((IOperation) Proxy.newProxyInstance(
							loader, interfaces, handler));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		BaseFluentTest exampleFluentTest = new BaseFluentTest();
		System.out.println(exampleFluentTest.getBaseUrl());
		// System.out.println(exampleFluentTest.newWebDriver());
	}

}
