package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents the POM (Page Object Model) of the Login Dialogue
 * Window. It is a design pattern, popularly used in test automation that
 * creates Object Repository for web UI elements.
 */
public class LoginWindow {
	WebDriver Driver;

	public LoginWindow(WebDriver driver) {
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/// This element find the Login Dialogue Window
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div[2]/div")
	public WebElement LoginDialogueWindow;

	/// This element finds the close button in the Login Dialogue Window
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > main > div.dialog__container___2uP4v-scss > div > div > div.dialog-close___2tE6n-scss > svg > path")
	public WebElement LoginWindowClose;

	/// This element finds the PageTitle in the Login Dialogue Window
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div[2]/div/div/div[1]")
	public WebElement PageTitle;

	/// This element find the content in the Login Dialogue Window
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div[2]/div/div/div[2]")
	public WebElement PageContent;

	/// This element find the username in the Login Dialogue Window
	@FindBy(name = "email")
	public WebElement UserName;

	/// This element find the username field error in the Login Dialogue Window
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div[2]/div/div/form/div[1]/div")
	public WebElement UserNameFieldError;

	/// This element find the password in the Login Dialogue Window
	@FindBy(name = "password")
	public WebElement PassWord;

	/// This element finds the username field error in the Login Dialogue Window
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div[2]/div/div/form/div[2]/div[1]/div")
	public WebElement PassWordFieldError;

	/// This element find the show password field in the Login Dialogue Window
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div[2]/div/div/form/div[2]/div[2]")
	public WebElement ShowPassword;

	/// This element find the LoginButton in the Login Dialogue Window
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div[2]/div/div/form/div[3]/button")
	public WebElement LoginButton;

	/// This element find the Forgot your password link in the Login Dialogue Window
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div[2]/div/div/form/div[3]/a")
	public WebElement ForgotPassoword;

	/// This element find the Signup link in the Login Dialogue Window
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div[2]/div/div/div[3]/a")
	public WebElement SignUp;

}
