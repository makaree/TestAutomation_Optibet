package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents the POM (Page Object Model) of the main page. It is a
 * design pattern, popularly used in test automation that creates Object
 * Repository for web UI elements.
 */
public class MainPage {
	WebDriver Driver;

	public MainPage(WebDriver driver) {
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/// This element find the Login Button in the mainpage
	@FindBy(xpath = "//*[@id=\"topBar\"]/div[1]/div[2]/button[1]")
	public WebElement LoginButton;

}
