package com.example.DEMO.TESJO.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.DEMO.TESJO.utils.TestBase;

public class ProductsPage extends TestBase {

	By shoppingCarLocator = By.className("shopping_cart_link");
	By mainTitleLocator = By.xpath("//*[contains(text(), 'Products')]");

	By addBackpackLocator = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']");
	By titleShoppingCar = By.className("inventory_item_name");
	By checkoutButtonLocator = By.id("checkout");
	By removeButtonLocator = By.id("remove-sauce-labs-backpack");

	public ProductsPage(WebDriver driver) {
		super(driver);
	}

	public boolean isShoppingCarDisplayed() {
		return isDisplayed(shoppingCarLocator);
	}

	public boolean isProductTitleDisplayed() {
		return isDisplayed(mainTitleLocator);
	}

	public void addItemToShoppingCar(String item) {
		if (item.contains("BackPack")) {
			clickOnButton(addBackpackLocator);
		}

	}

	public boolean verifyShoppingCar(String item) throws InterruptedException {
		if (getText(removeButtonLocator).equals("Remove")) {
			clickOnButton(removeButtonLocator);
		} else {
			clickOnButton(addBackpackLocator);
			Thread.sleep(1000);
			clickOnButton(By.xpath("//div[@id='shopping_cart_container']"));
			if (getText(titleShoppingCar).equals(item) && isDisplayed(checkoutButtonLocator)) {
				return true;
			}
		}
		return false;
	}
}
