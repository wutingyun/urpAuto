package com.apache.urp.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WaitHandler implements InvocationHandler {
	// 定义目标对象的引用，并建立联系
	private Object target;

	public WaitHandler(Object target) {
		this.target = target;
	}

	/**
	 * 执行代理对象的业务方法时,会自动执行这个handler对象的invoke方法
	 * 
	 * @param proxy  指向代理对象
	 * @param method 指向接口中的方法对象
	 * @param args   指向method对象执行时需要的实际参数
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = method.invoke(target, args);
		waitMillis(200);// 单位  毫秒
		//统一操作间隔时间
		return result;
	}
	
	protected void waitMillis(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
