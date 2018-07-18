package com.apache.urp.config;

import static org.openqa.selenium.remote.DesiredCapabilities.chrome;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public enum BrowserType {

	CHROME {
		@Override
		public WebDriver getWebDriver() {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			//ChromeDriver chromeDriver = new ChromeDriver();
			
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:/Users/Administrator/AppData/Local/Google/Chrome/Application/chrome.exe");
			ChromeDriver chromeDriver = new ChromeDriver(options);
			
			chromeDriver.manage().window().maximize();
			return chromeDriver;
		} 

		@Override
		protected DesiredCapabilities getBrowserCapabilities() {
			return chrome();
		}
	};

	protected abstract WebDriver getWebDriver();

	protected abstract DesiredCapabilities getBrowserCapabilities();

	public WebDriver getRemoteWebDriver(String hubLocation) {
		try {
			return new Augmenter().augment(new RemoteWebDriver(new URL(hubLocation), getBrowserCapabilities()));
		} catch (MalformedURLException e) {
			throw new RuntimeException("Invalid hub location: " + hubLocation, e);
		}
	}

	public static void main(String[] args) {
		BrowserType obj = BrowserType.valueOf("CHROME");
		System.out.println(obj);
	}

}
