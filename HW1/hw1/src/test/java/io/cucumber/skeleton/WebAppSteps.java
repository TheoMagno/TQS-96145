package io.cucumber.skeleton;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebAppSteps {
    private final WebDriver driver = new FirefoxDriver();
    @Given("go to the WebApp home page")
    public void go_to_the_web_app_home_page() {
        driver.get("file:///D:/Faculdade/TQS/TQS-96145/HW1/WebApp/index.html");
    }
    @When("I search for {string}")
    public void search_for(String query) throws InterruptedException {
        WebElement element = driver.findElement(By.id("country"));
        element.sendKeys(query);
        element.submit();
    }
    @When("click on {string}")
    public void click_on_brazil(String country) {
        driver.findElement(By.id(country)).click();
    }
    @Then("country's name is {string} and continent is {string}")
    public void country_s_name_is_brazil(String country, String continent) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        assertEquals(driver.findElement(By.id("country_name")).getText(), country);
        assertEquals(driver.findElement(By.id("country_continent")).getText(), "Continent: "+continent);
    }
    @After()
    public void closeBrowser() {
        driver.quit();
    }
}
