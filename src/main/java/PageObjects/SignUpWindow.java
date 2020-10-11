package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents the POM (Page Object Model) of the SignUp window. It is
 * a design pattern, popularly used in test automation that creates Object
 * Repository for web UI elements.
 */
public class SignUpWindow {
	public SignUpWindow(WebDriver driver) {
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/// This element find the SignUp Dialogue Window
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > main > div.dialog__container___2uP4v-scss > div")
	public WebElement SignUpWindow;

	/// This element find the close option in the SignUp window
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > main > div.dialog__container___2uP4v-scss > div > div > div.dialog-close___2tE6n-scss > svg > path")
	public WebElement SignUpWindowClose;

	/// This element find the Page Title in the SignUp window
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > main > div.dialog__container___2uP4v-scss > div > div > div.signup___3YBek-scss.stepOne___2W8h_-scss > div.dialog-title___mmlpH-scss")
	public WebElement PageTitle;

	/// This element find the username in the SignUp window
	@FindBy(name = "email")
	public WebElement UserName;

	/// This element find the password in the SignUp window
	@FindBy(name = "password")
	public WebElement password;

	/// This element find the accept policy in the SignUp window
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > main > div.dialog__container___2uP4v-scss > div > div > div.signup___3YBek-scss.stepOne___2W8h_-scss > form > div:nth-child(5) > div.form-field___2rWMa-scss > label > span.checkbox__box___KS1zS-scss")
	public WebElement AcceptPolicy;

	/// This element find the SignUp button in the SignUp window
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > main > div.dialog__container___2uP4v-scss > div > div > div.signup___3YBek-scss.stepOne___2W8h_-scss > form > button")
	public WebElement SignUp;
}
