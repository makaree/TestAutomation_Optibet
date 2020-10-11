package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents the POM (Page Object Model) of the Session Expired
 * Dialogue Window. It is a design pattern, popularly used in test automation
 * that creates Object Repository for web UI elements.
 */
public class SessionExpiredWindow {
	public SessionExpiredWindow(WebDriver driver) {
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/// This element find the Session Expired Dialogue Window
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > main > div.dialog__container___2uP4v-scss > div")
	public WebElement SessionExpiredWindow;

	/// This element find the close option in the Session Expired Dialogue Window
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > div.dialog__container___2uP4v-scss > div > div > div.dialog-close___2tE6n-scss > svg > path")
	public WebElement SessionExpiredWindowClose;

	/// This element find the Login button in the Session Expired Dialogue Window
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > div.dialog__container___2uP4v-scss > div > div > a > button")
	public WebElement LoginButton;
}
