package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {      //constructor - it will be called first when this class is called
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//Page Factory
	
	
	//PageFactory.initElements(driver, this); - initElement() will take care of initializing this elements
	@FindBy(id="userEmail")   //driver.findElement(By.id("userEmail"));
	WebElement userEmail;     //WebElement userEmail = 
	
	@FindBy(id="userPassword")   //driver.findElement(By.id("userPassword"));
	WebElement userPassword;     //WebElement userPassword = 
	
	@FindBy(id="login")   //driver.findElement(By.id("login"));
	WebElement submit;     //WebElement submit = 
	
	@FindBy(css="[class*= 'flyInOut']")   //error message  - incorrect email or password -> inspect -> selectors hub
	WebElement errorMessage;
	
	
	
	public void goTo() {  //get url here
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public ProductCatalogue Login(String password, String username) {
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver); 
		return productCatalogue;
	}
	
	
}
