package com.apache.urp.page;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.FindBy;

import com.apache.urp.util.Account;

@PageUrl("/login")
public class LoginPage extends FluentPage {
	@FindBy(id = "username")
	private FluentWebElement usernameInput;   //登录页面，用户名
	@FindBy(id = "password")
	private FluentWebElement passwordInput;  //登录页面，密码
	@FindBy(xpath = "//*[@id=\"defaultForm\"]/div[2]/button")
	private FluentWebElement loginButton;   //登录页面，登录按钮
	@FindBy(css = "#formerror")
	private FluentWebElement errorLogin;//登录页面，登录不成功的错误信息
	
	@Page
	private MainPage mainPage;

	public LoginPage usernameTextIn(String text) {
		usernameInput.clear();
		usernameInput.write(text);
		return this;
	}

	public LoginPage passwordTextIn(String text) {
		passwordInput.clear();
		passwordInput.write(text);
		return this;
	}

	public MainPage loginButton() {
		loginButton.click();
		return mainPage;
	}

	public void login(Account account) {
		try {
			usernameTextIn(account.getUsername());
			passwordTextIn(account.getPassword());
			loginButton();

		} catch (UnhandledAlertException f) {
			try {
				String errorLoginMsg = errorLogin.text();
				System.out.println("登录失败提示：" + errorLoginMsg);
			} catch (NoAlertPresentException e) {
				e.printStackTrace();
			}
		}

	}
	
	

}
