package rahulshettyacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;



public class ErrorValidationsTest extends BaseTest {
		
	    @Test(groups= {"ErrorHandling"},retryAnalyzer=rahulshettyacademy.TestComponents.Retry.class)
		//@Test
		public void loginErrorValidation() throws IOException, InterruptedException {
			
			landingpage.Login("Divya@soni123", "divyasoni@gmail.com");
			Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	   }
		
	   @Test
	   public void ProductErrorValidation() throws InterruptedException {
		   
		    String productName = "ZARA COAT 3";
			ProductCatalogue productCatalogue = landingpage.Login("Iamking@000", "rahulshetty@gmail.com");
			
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
			CartPage cartpage = productCatalogue.goToCartPage();
			
	
			Boolean match = cartpage.verifyProductDisplay("ZARA COAT 33");
			Assert.assertFalse(match);
			
			
	   }

}
