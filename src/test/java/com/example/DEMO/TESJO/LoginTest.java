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

class LoginTest {

	private WebDriver driver = null;
	private LoginPage login = null;

	private String URL = "https://www.saucedemo.com/";

	@BeforeMethod
	@Parameters({ "browser" })
	public void setup(String browser) {
		// @formatter:off

		/*
		 * Feature: Inicio de Session en SauceDemo 
		 * Scenario: Ingresar datos correctos en Inicio de Session
		 * Given: Que me encuentro en la pagina de login de sauce lab
		 * When: Introduzco usuario, contraseña y presiono Login 
		 * Then: Me permite observar la pagina principal
		 * 
		 * Feature: Inicio de Session Incoorrecto en SauceDemo 
		 * Scenario: Ingresar datos incorrectos en Inicio de Session Given: Que me encuentro en la pagina de
		 * login de sauce lab When: Introduzco usuario, contraseña y presiono Login
		 * Then: Me permite observar un mensaje de error*
		 * 
		 * ** Reto: Llega mi PM y me dice que las pruebas deben de ser en multiples
		 * navegadores
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
	}

	@Test
	public void loginExitoso() throws InterruptedException {

		login.setCredentials("standard_user", "secret_sauce");

		// Validar Ingreso Exitoso si se muestra el texto "Products"
		Assert.assertEquals(login.getSucessfullMessage(), "Products".toUpperCase());

	}

	@Test
	public void loginNoExitoso() {

		login.setCredentials("standard_user", "incorrectPassword");

		// Validar mensaje de error "Epic sadface: Username and password do not match
		// any user in this service"
		String error = "Epic sadface: Username and password do not match any user in this service";
		Assert.assertEquals(login.getFailedMessage(), error);
	}

	@Test
	public void loginLockedOutUser() {

		login.setCredentials("locked_out_user", "secret_sauce");

		// Validar mensaje de error "Epic sadface: Username and password do not match
		// any user in this service"
		String error = "Epic sadface: Sorry, this user has been locked out.";
		Assert.assertEquals(login.getLockedOutMessage(), error);
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		login.closeBrowser();
	}
}
