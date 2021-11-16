package com.example.DEMO.TESJO.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestBase {

	private WebDriver driver = null;
	private WebElement element = null;

	public TestBase(WebDriver driver) {
		this.driver = driver;
	}

	protected void write(String text, By locator) {
		element = driver.findElement(locator);
		element.clear();
		element.sendKeys(text);
	}

	protected void clickOnButton(By locator) {
		driver.findElement(locator).click();
	}

	protected String getText(By locator) {
		return driver.findElement(locator).getText();
	}

	public boolean isDisplayed(By locator) {
		return driver.findElement(locator).isDisplayed();
	}

	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
}
