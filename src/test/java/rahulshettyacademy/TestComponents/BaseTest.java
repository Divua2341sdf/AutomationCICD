package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver InitializeDriver() throws IOException {
		
		//Properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);   //argument - send as input stream object
		
		// if !=null condition is true, then second argument is executed and if condition is false then 3rd argument is executed
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");
		//prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();        // run in headless mode
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")){              // mvn test -PRegression -Dbrowser="chromeheadless"
				options.addArguments("headless");                   // run in headless mode
			}
			driver = new ChromeDriver(options);                 // run in headless mode
			driver.manage().window().setSize(new Dimension(1440,900));   //run in full screen
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			
			System.setProperty("wbdriver.gecko.driver", "C:/Users/DIVYA/geckodriver");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("egde")) {
			//WebDriver driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(System.getProperty("user.dir") + "//reports" + testCaseName + ".png"));
		return System.getProperty("user.dir") + "//reports" + testCaseName + ".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunchApplication() throws IOException {
		driver = InitializeDriver();
		landingpage = new LandingPage(driver);   //Page Object class - login
		landingpage.goTo();
		return landingpage;
		
	}
	
	@AfterMethod(alwaysRun=true) 
	public void TearDown() {
		driver.close();
	}
}
