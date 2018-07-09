package com.apache.urp;

import org.fluentlenium.adapter.testng.FluentTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.apache.urp.config.BrowserConfig;
import com.apache.urp.config.SeleniumBrowserConfigProperties;

public class BaseFluentTest extends FluentTestNg{

	SeleniumBrowserConfigProperties config =new SeleniumBrowserConfigProperties();

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

	public static void main(String[] args) {
		BaseFluentTest exampleFluentTest = new BaseFluentTest();
		System.out.println(exampleFluentTest.getBaseUrl());
	//	System.out.println(exampleFluentTest.newWebDriver());
	}

}
