package Steps;

import org.openqa.selenium.By;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class SearchSteps extends BaseSteps {

	@After
	public void after() {
		stopWebDriver();
	}

	@Given("launch browser {string}")
	public void launch_browser(String browserName) {
		startWebDriver(browserName);
	}

	@When("user navigates to {string}")
	public void user_navigates_to(String url) {
		navigate(url);

	}

	@And("user clicks on Tab {string}")
	public void user_clicks_on_tab(String category) {
		if (category.equals("Flight")) {
			getDriver().findElement(By.id("flights")).click();
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
		Thread.sleep(3000);
		getDriver().findElement(By.cssSelector("li[data-ui-name]:first-child")).click();

		// arrival
		getDriver().findElement(By.xpath("//div[1]//div[1]//div[1]//div[1]//div[1]//button[3]")).click();
		getDriver().findElement(By.cssSelector("input[placeholder='Airport or city']")).sendKeys(arrival);
		Thread.sleep(3000);
		getDriver().findElement(By.cssSelector("li[data-ui-name]:first-child")).click();

		// date
		getDriver().findElement(By.xpath("//button[contains(@placeholder,'Depart – Return')]")).click();
		String date = selectDate(departureDate);

		getDriver().findElement(By.cssSelector("span[data-date='" + date + "']")).click();
		Thread.sleep(2000);
		date = selectDate(arrivalDate);
		getDriver().findElement(By.cssSelector("span[data-date='" + date + "']")).click();
		Thread.sleep(2000);
		// quantity
		getDriver().findElement(By.xpath("//button[contains(@title,'Add or remove travelers')]")).click();
		// getDriver().findElement(By.xpath("//div[contains(@class,'Adults-module__wrapper___YqBUC')]//button[2]")).click()
	}

//	@When("user click button search")
//	public void user_click_button_search() throws InterruptedException {
//		WebElement e = getDriver().findElement(By.name("q"));
//		e.sendKeys("test");
//		Thread.sleep(2000);
//		e.sendKeys(Keys.ENTER);
//		Thread.sleep(2000);
//
//	}

	@And("clicks on search button")
	public void clicks_on_search_button() throws InterruptedException {
		getDriver().findElement(By.xpath("//span[contains(@class,'Button-module__text___sRRzg')]")).click();
		Thread.sleep(2000);
	}

}
