package com.example.DEMO.TESJO.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.DEMO.TESJO.utils.TestBase;

public class LoginPage extends TestBase {

	By usernameLocator = By.name("user-name");
	By passwordLocator = By.name("password");
	By loginButtonLocator = By.id("login-button");

	By sucessfullMessageLocator = By.xpath("//*[contains(text(), 'Products')]");
	By lockedOutMessageLocator = By.xpath("//div[@class='error-message-container error']/h3");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	private void setUserName(String username) {
		write(username, usernameLocator);
	}

	private void setPassword(String password) {
		write(password, passwordLocator);
	}

	private void clickOnLogin() {
		clickOnButton(loginButtonLocator);
	}

	public void setCredentials(String user, String password) {
		setUserName(user);
		setPassword(password);
		clickOnLogin();
	}

	public String getSucessfullMessage() {
		return getText(sucessfullMessageLocator);
	}

	public String getLockedOutMessage() {
		return getText(lockedOutMessageLocator);
	}

	public String getFailedMessage() {
		return getText(lockedOutMessageLocator);
	}
}
