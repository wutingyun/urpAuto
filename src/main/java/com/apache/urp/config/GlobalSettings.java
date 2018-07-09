package com.apache.urp.config;

import java.io.FileInputStream;
import java.util.Properties;

public class GlobalSettings {


	/**
	 * 该类为对项目的整体参数配置类，，主要设置本地浏览器地址，浏览器类型，远程浏览器地址等，其读取config.properties 配置文件。
	 * @author Administrator
	 */
	public static Properties prop = getProperties();  //获取prop.properties文件里面所有内容，以键值对的方式展示。
	
	public static String url=prop.getProperty("selenium.get.url");//通过key，获取value

	public static String browserType1=prop.getProperty("selenium.browser.type");//通过key，获取value
	public static BrowserType browserType = BrowserType.valueOf(browserType1);//由String类型转换成BrowserType类型
	
	public static String useHub1=prop.getProperty("selenium.hub.enabled");//通过key，获取value
	public static boolean useHub=Boolean.getBoolean(useHub1);//由String类型转换成Boolean类型
	
	public static String hubLocation=prop.getProperty("selenium.hub.location");//通过key，获取value
	
	/**
	 *  用指定的键在此属性列表中搜索属性。也就是通过参数 key ，得到 key 所对应的 value。
	 * @param property
	 * @return
	 */
	public static String getProperty(String property) {
		return prop.getProperty(property);
	}

	/**
	 * 获取prop.properties文件里面所有内容 
	 * @return
	 */
	public static Properties getProperties() {
		Properties prop = new Properties();
		try {
			String dir_name="src\\main\\resources\\config.properties";
			FileInputStream file = new FileInputStream(dir_name);
			prop.load(file);
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	
	public static void main(String[] args) {
		Properties test01=GlobalSettings.getProperties();
		System.out.println(test01);
		
		/*String test02=GlobalSettings.getProperty("BrowserCoreType");
		System.out.println(test02);*/
	}
}
