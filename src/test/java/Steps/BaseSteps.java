package Steps;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseSteps {

	private WebDriver driver;
	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {

		return dr.get();
	}

	public static void setWebDriver(WebDriver driver) {

		dr.set(driver);
	}

	protected void startWebDriver(String browser) {

		if (browser.equals("firefox")) {

			driver = new FirefoxDriver();
		} else if (browser.equals("chrome")) {

			driver = new ChromeDriver();
		}

		setWebDriver(driver);
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	protected void navigate(String url) {

		getDriver().navigate().to(url);
		driver.findElement(By.id("W0wltc")).click();

	}

	protected void stopWebDriver() {

		getDriver().quit();

	}

}
