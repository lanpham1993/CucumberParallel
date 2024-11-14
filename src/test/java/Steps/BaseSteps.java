package Steps;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

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
		// driver.findElement(By.id("W0wltc")).click();

	}

	protected void stopWebDriver() {

		getDriver().quit();

	}

	protected String selectDate(String date) {

		// Parse the input to get the number of days to add
		int daysToAdd = Integer.parseInt(date.split("\\+")[1].trim());

		// Calculate the future date
		LocalDate futureDate = LocalDate.now().plusDays(daysToAdd);

		// Format the date as desired
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = futureDate.format(formatter);

		String year = futureDate.format(DateTimeFormatter.ofPattern("YYYY"));
		System.out.println("++++++  year " + year);

		String monthString = formattedDate.split("\\-")[1].trim(); // 12
		System.out.println("**** monthString" + monthString);

		while (true) {
			String displayMonth = getDriver()
					.findElements(By.cssSelector("div.Calendar-module__monthWrapper___T0gG4 h3")).get(0).getText();
			String[] displayDateArr = displayMonth.split(" "); // [December, 2024]

			int month = Month.valueOf(displayDateArr[0].toUpperCase()).getValue();// 12

			if (displayDateArr[1].equals(year) && month == Integer.parseInt(monthString)) {
				System.out.println("++++++ displayed year" + displayDateArr[1]);
				break;
			}
			getDriver().findElements(By.cssSelector(
					"span.Button-module__icon___JdtNR span.Icon-module__root___LRxqA.Icon-module__root--size-medium___5zEVc"))
					.get(0).click();
		}
		return formattedDate; // 2005-12-10

	}

}
