package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents the POM (Page Object Model) of the Forget Password window. It is a
 * design pattern, popularly used in test automation that creates Object
 * Repository for web UI elements.
 */
public class ForgotPasswordWindow {
	public ForgotPasswordWindow(WebDriver driver) {
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/// This element find the Forgot password Dialogue Window
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > div.dialog__container___2uP4v-scss > div")
	public WebElement ForgotPasswordDialogueWindow;

	/// This element find the close button in the Forgot password Dialogue Window 
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > div.dialog__container___2uP4v-scss > div > div > div.dialog-close___2tE6n-scss > svg > path")
	public WebElement ForgotPasswordWindowClose;

	/// This element find the title in Forgot password Dialogue Window
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > div.dialog__container___2uP4v-scss > div > div > div.dialog-title___mmlpH-scss")
	public WebElement PageTitle;

	/// This element find the content in the Forgot password Dialogue Window
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > div.dialog__container___2uP4v-scss > div > div > div.dialog-text___3oTQf-scss")
	public WebElement PageContent;

	/// This element find the email section in the Forgot password Dialogue Window
	@FindBy(name = "email")
	public WebElement UserName;

	/// This element find the Proceed button in Forgot password Dialogue Window
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > div.dialog__container___2uP4v-scss > div > div > div:nth-child(3) > form > button")
	public WebElement Proceed;

}
