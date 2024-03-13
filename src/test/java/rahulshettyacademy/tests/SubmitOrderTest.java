package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;



public class SubmitOrderTest extends BaseTest {

		public 	String productName = "ZARA COAT 3";
	
		@Test(dataProvider="getData",groups="Purchase")
		//public void submitOrder(String password, String email, String productName) throws IOException, InterruptedException {
		public void submitOrder(HashMap <String,String> input) throws IOException, InterruptedException {	
			//submit -checkout order
			
			ProductCatalogue productCatalogue = landingpage.Login(input.get("password"),input.get("email"));
			
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(input.get("product"));
			CartPage cartpage = productCatalogue.goToCartPage();
			
	
			Boolean match = cartpage.verifyProductDisplay(input.get("product"));
			Assert.assertTrue(match);
			
			CheckOutPage checkoutpage = cartpage.goToCheckout();
			checkoutpage.SelectCountry("india");
			
			
			ConfirmationPage confirmationpage = checkoutpage.submitOrder();
			String confirmMessage = confirmationpage.getConfirmationPage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	 	
	@Test(dependsOnMethods={"submitOrder"})
	public void OrderHistoryTest() {
		
		//verify order from Orders list
		
		ProductCatalogue productCatalogue = landingpage.Login("Divya@soni123", "divyasoni@gmail.com");
		OrderPage orderpage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
			
	}
	
	//@DataProvider
	//public Object[][] getData() {
	//	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("use.dir")+"//src//test//")
	  //  return new Object[][] {{data.get(0)},{data.get(1)}};
	//}
	
	
	@DataProvider
	public Object[][] getData() {
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "divyasoni@gmail.com");
		map.put("password", "Divya@soni123");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "shetty@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("product", "ADIDAS ORIGINAL");
		
		
		return new Object[][] {{map},{map1}};
	}


	
	//@DataProvider
	//public Object[][] getData() {
		//return new Object[][] {{"Divya@soni123","divyasoni@gmail.com", "ZARA COAT 3"},{"Iamking@000","shetty@gmail.com","ADIDAS ORIGINAL"}};
	//}

}
