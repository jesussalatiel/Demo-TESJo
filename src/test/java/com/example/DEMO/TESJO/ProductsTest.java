package com.example.DEMO.TESJO;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
import com.example.DEMO.TESJO.pages.LoginPage;
import com.example.DEMO.TESJO.pages.ProductsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

class ProductsTest {

	private WebDriver driver = null;
	private LoginPage login = null;
	private ProductsPage products = null;

	private String URL = "https://www.saucedemo.com/";

	@BeforeMethod
	@Parameters({ "browser" })
	public void setup(String browser) {
		// @formatter:off

		/*
		 * Feature: Validar Panatalla Inventario
		 * Scenario: Validar que se encuentren presentes el Icono Carrito y Produtos 
		 * Given: Me encuentro Logeado en lapagina Inventario Then: Valido que este escrito productos "PRODUCTS" y valido que se encuentre el icono carrito
		 * 
		 * 
		 * Feature; Validar que se pueda agregar un producto al carrito 
		 * Scenario:Validar que el item "Sauce Labs Backpack" pueda agregarse al carrito 
		 * Given: Me encuentro Logeado en la pagina Inventario Then: Selecciono el elemento "Sauce Labs Backpack" y lo agrego al carrito Then: Valido que el elemento seleccionado se encuentre el carrito
		 */
		// @formatter:on

		if (browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else {
			System.out.println("No funciono me da ansiedad");
		}

		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		login = new LoginPage(driver);
		products = new ProductsPage(driver);
		login.setCredentials("standard_user", "secret_sauce");

	}

	@Test
	public void validarIconos() {
		assertTrue(products.isProductTitleDisplayed());
		assertTrue(products.isShoppingCarDisplayed());
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		login.closeBrowser();

	}
}
