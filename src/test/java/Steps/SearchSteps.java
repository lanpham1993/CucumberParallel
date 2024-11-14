package Steps;

import java.io.IOException;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

import extent.ExtentManager;
import extent.ExtentTestManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class SearchSteps extends BaseSteps {

	protected Scenario scenario;
	static String scenarioName;
	static int x = 0;

	@Before
	public void before(Scenario scenario) {
		x = x + 1;
		this.scenario = scenario;
		scenarioName = scenario.getName();
		ExtentTestManager.startTest("Scenario No : " + x + " : " + scenario.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Scenario started : - " + scenario.getName());
	}

	@After
	public void after(Scenario scenario) throws IOException {

		if (scenario.isFailed()) {

			ExtentTestManager.logFail("Scenario Failed");
			ExtentTestManager.addScreenShotsOnFailure();
		} else {

			ExtentTestManager.scenarioPass();
		}

		ExtentManager.getReporter().flush();

		stopWebDriver();
	}

	@Given("launch browser {string}")
	public void launch_browser(String browserName) {
		startWebDriver(browserName);
		ExtentTestManager.logInfo("Launching Browser : " + browserName);

	}

	@When("user navigates to {string}")
	public void user_navigates_to(String url) {
		ExtentTestManager.logInfo("Navigating to the URL : " + url);
		navigate(url);

	}

	@And("user clicks on Tab {string}")
	public void user_clicks_on_tab(String category) {
		if (category.equals("Flight")) {
			getDriver().findElement(By.id("flights")).click();
			ExtentTestManager.logInfo("Navigating to Flight tab");

		}
	}

	@And("user inputs {string}, {string}, {string}, {string}, {string}")
	public void user_inputs(String departure, String arrival, String departureDate, String arrivalDate, String quantity)
			throws InterruptedException {

		// departure
		getDriver()
				.findElement(
						By.xpath("//div[contains(@class,'SegmentHorizontal-module__destination___paTRj')]//button[1]"))
				.click();
		getDriver().findElement(By.xpath("//span[@class='Tags-module__text___90E7G']")).click();
		getDriver().findElement(By.cssSelector("input[placeholder='Airport or city']")).sendKeys(departure);
		ExtentTestManager.logInfo("Entering the value for departure  : " + departure);

		Thread.sleep(3000);
		getDriver().findElement(By.cssSelector("li[data-ui-name]:first-child")).click();

		// arrival
		getDriver().findElement(By.xpath("//div[1]//div[1]//div[1]//div[1]//div[1]//button[3]")).click();
		getDriver().findElement(By.cssSelector("input[placeholder='Airport or city']")).sendKeys(arrival);
		ExtentTestManager.logInfo("Entering the value for arrival  : " + arrival);

		Thread.sleep(3000);
		getDriver().findElement(By.cssSelector("li[data-ui-name]:first-child")).click();

		// date
		getDriver().findElement(By.xpath("//button[contains(@placeholder,'Depart – Return')]")).click();
		String date = selectDate(departureDate);

		getDriver().findElement(By.cssSelector("span[data-date='" + date + "']")).click();
		ExtentTestManager.logInfo("Entering the value for departure date : " + departureDate);
		Thread.sleep(2000);
		date = selectDate(arrivalDate);
		getDriver().findElement(By.cssSelector("span[data-date='" + date + "']")).click();
		ExtentTestManager.logInfo("Entering the value for arrival date  : " + arrivalDate);
		Thread.sleep(2000);

		// quantity
		getDriver().findElement(By.xpath("//button[contains(@title,'Add or remove travelers')]")).click();
		Thread.sleep(2000);
		ExtentTestManager.logInfo("Clicking quantity of adults : " + quantity);
	}

	@And("clicks on search button")
	public void clicks_on_search_button() throws InterruptedException {
		getDriver().findElement(By.xpath("//span[contains(@class,'Button-module__text___sRRzg')]")).click();
		ExtentTestManager.logInfo("Clicking on Search button");
		Thread.sleep(2000);
	}

}
