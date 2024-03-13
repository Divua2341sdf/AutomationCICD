package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;


public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//add dependency of WebDriverManager for ECommerce project in pom.xml file
		
		//Step 1: invoking chrome driver
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		String productName = "ZARA COAT 3";
		
	    ////page object class: Abstract Component
		//Step 2: implicit wait before performing actions
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		

		//page object login - LandingPage
		driver.get("https://rahulshettyacademy.com/client");
		//Step 3: enter email- id and password and click on login button
		driver.findElement(By.id("userEmail")).sendKeys("divyasoni@gmail.com");  
		driver.findElement(By.id("userPassword")).sendKeys("Divya@soni123");
		driver.findElement(By.id("login")).click();
		
		
		////page object class: Abstract Component
		//wait until visibility of elements are located
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		

		//page object LIST OF products - ProductCatalogue
		//Step 4: Collect lists of products showing on the page
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		//Step 5: click on Add to cart button
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
	    ////page object class: Abstract Component
		//Step 6: Handle text/notification showing that product added successfully to cart and icon 0f add to cart is highlighted after that
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait till icon gets invisible
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		
		//page object class: AbstractComponent
		//Cart - click on top right side corner Cart button
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		
		//page object - CartPage
		//Step 7: Verify products name in a list and then verify whether product is ZARA COAT 3 using stream()
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		//returns true using anyMatch()
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		//Step 8: Checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		
		
		//page object - CheckOutPage
		//Step 9: Select Country
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		//wait until options for ind is showing
		//w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		//select india from the option
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		//Step 10: click on Place Order
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		
		
		
		//page object - ConfirmationPage
		//Step 11: verify Thank you for the order
		String output = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(output, "THANKYOU FOR THE ORDER.");
		
		
		
		
		
		
		

	}

}
