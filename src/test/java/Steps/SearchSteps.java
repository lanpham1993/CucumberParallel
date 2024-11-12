package Steps;

import org.openqa.selenium.By;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
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

	@And("user enters search term {string}")
	public void user_enters_search_term(String searchText) {
		getDriver().findElement(By.name("q")).sendKeys(searchText);

	}

	@Then("clicks on search button")
	public void clicks_on_search_button() {
		getDriver().findElement(By.name("btnK")).click();

	}
}
