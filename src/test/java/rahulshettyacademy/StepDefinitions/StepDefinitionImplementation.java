package rahulshettyacademy.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImplementation extends BaseTest{
	
	//declare it globally
	public LandingPage landingpage;
	public ProductCatalogue productCatalogue; // use it across all methods within this class
	public ConfirmationPage confirmationpage;
	
	@Given("I landed on E-Commerce Page.")
	public void I_landed_on_ecommerce_page() throws IOException {
		landingpage = LaunchApplication(); // call method initializedriver() which is mentioned in method LaunchApplication() 
	}
	
	@Given("^Logged in with password (.+) and username (.+)$") //when having parameters - this is syntax- (.+)  ^ $
	public void logged_in_username_password(String password, String username) {
		productCatalogue = landingpage.Login(password, username);
	}

	@When("^I add the product (.+) to cart$") //regular expression
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		CartPage cartpage = productCatalogue.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		
		CheckOutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.SelectCountry("india");
		confirmationpage = checkoutpage.submitOrder();
	}
	
	//indicates run time variable will come here - {string} - it will be captured and stored as an argument in the below method as String string
	@Then("{string} message is displayed on ConfirmationPage") //if you have string directly in the statement then
	public void display_confirmation_page(String string) {
		String confirmMessage = confirmationpage.getConfirmationPage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	
	//on run time- feature file will connect to your step definition file and run code in the order you mentioned
	
	@Then("^\"([^\"]*)\" message is displayed.")
	public void display_message(String strARG1) {
		Assert.assertEquals(strARG1, landingpage.getErrorMessage());
		driver.close();
	}

}

	
