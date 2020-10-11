package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents the POM (Page Object Model) of the login response page.
 * It is a design pattern, popularly used in test automation that creates Object
 * Repository for web UI elements.
 */
public class LoginPageResponse {
	WebDriver Driver;

	public LoginPageResponse(WebDriver driver) {
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/// This element find the Welcome Dialogue in the Login Response Page
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div/div")
	public WebElement WelcomeDialogue;

	/// This element find the Welcome Dialogue Close option in the Login Response
	/// Page
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > div.dialog__container___2uP4v-scss > div > div > div.dialog-close___2tE6n-scss > svg > path")
	public WebElement WelcomeDialogueClose;

	/// This element find the Balance Amount in the Login Response Page
	@FindBy(css = "#topBar > div.page-content___3oo_o-scss.topBarContent___3cr_D-scss > div.main___2Aq79-scss > div > span.name___2Kezb-scss.truncatedText___2nqXH-scss > a")
	public WebElement AccountProfileLink;

	/// This element find the Balance Amount in the Login Response Page
	@FindBy(css = "#topBar > div.page-content___3oo_o-scss.topBarContent___3cr_D-scss > div.main___2Aq79-scss > div > span.container___1dsKe-scss.undefined > a")
	public WebElement BalanceAmount;

	/// This element find the Deposit now option in the Login Response Page
	@FindBy(css = "#topBar > div.page-content___3oo_o-scss.topBarContent___3cr_D-scss > div.main___2Aq79-scss > button.button-base___Zmw1k-scss.button___25A2P-scss.button_size-default___2lYeH-scss.button_intent-default___2Ljl_-scss.button_intent-primary___3CYta-scss.deposit___3DiwT-scss")
	public WebElement DepositNow;

	/// This element find the LogOut Button in the Login Response Page
	@FindBy(css = "#topBar > div.page-content___3oo_o-scss.topBarContent___3cr_D-scss > div.main___2Aq79-scss > button.button-base___Zmw1k-scss.button___25A2P-scss.button_size-default___2lYeH-scss.button_intent-default___2Ljl_-scss.logout___1-oU6-scss")
	public WebElement LogOut;

}
