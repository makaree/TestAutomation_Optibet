package LoginPageCheck;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import PageObjects.ForgotPasswordWindow;
import PageObjects.LoginWindow;
import PageObjects.SignUpWindow;
import Utils.Perform;

/**
 * This test class is used to test the User Interface of login page and login
 * response page of the optibet.lv. It checks if various elements are present in
 * the page, their font, font color and the style of the font. It also inherits
 * the beforegroup and aftergroup properties from ParentTestClass
 */
public class UserInterfaceTests extends ParentTestClass {

	/**
	 * This test checks if various elements in the login window are present once the
	 * window has been loaded. It checks if the elements are displayed or not.
	 */
	@Test(groups = { "stable",
			"userinterface" }, description = " This test checks if various elements in the login window are present once the\r\n"
					+ "window has been loaded. It checks if the elements are displayed or not.")
	public void CheckLoginPageElements() {
		Perform.GoToLoginPage();
		WebDriver driver = Perform.ReturnActiveDriverInstance();
		LoginWindow loginPage = new LoginWindow(driver);
		AssertJUnit.assertEquals("Login Page window is not displayed", true,
				loginPage.LoginDialogueWindow.isDisplayed());
		AssertJUnit.assertEquals("Login Page window close button is not displayed", true,
				loginPage.LoginWindowClose.isDisplayed());
		AssertJUnit.assertEquals("Page title is not displayed in Login Page window", true,
				loginPage.PageTitle.isDisplayed());
		AssertJUnit.assertEquals("Page content is not displayed in Login Page window", true,
				loginPage.PageContent.isDisplayed());
		AssertJUnit.assertEquals("Username field is not displayed in Login Page window", true,
				loginPage.UserName.isDisplayed());
		AssertJUnit.assertEquals("Password field is not displayed in Login Page window", true,
				loginPage.PassWord.isDisplayed());
		AssertJUnit.assertEquals("Show Password field is not displayed in Login Page window", true,
				loginPage.ShowPassword.isDisplayed());
		AssertJUnit.assertEquals("Sign Up option is not displayed in Login Page window", true,
				loginPage.SignUp.isDisplayed());
		AssertJUnit.assertEquals("Forgot Password option is not displayed in Login Page window", true,
				loginPage.ForgotPassoword.isDisplayed());
		AssertJUnit.assertEquals("Login Button is not displayed in Login Page window", true,
				loginPage.LoginButton.isDisplayed());
	}

	/**
	 * This test checks if various elements in the SignUp window are present once
	 * the window has been loaded. It checks if the elements are displayed or not.
	 */
	@Test(groups = { "stable",
			"userinterface" }, description = " This test checks if various elements in the SignUp window are present once the\r\n"
					+ "page has been loaded. It checks if the elements are displayed or not.")
	public void CheckSignUpElements() {
		Perform.GoToLoginPage();
		WebDriver driver = Perform.ReturnActiveDriverInstance();
		LoginWindow loginPage = new LoginWindow(driver);
		SignUpWindow signupPage = new SignUpWindow(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);

		loginPage.SignUp.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#app > div.container___hBWrc-scss.oblt > main > div.dialog__container___2uP4v-scss > div")));
		AssertJUnit.assertEquals("Sign Up window is not displayed", true, signupPage.SignUpWindow.isDisplayed());
		AssertJUnit.assertEquals("Sign Up window close button is not displayed", true,
				signupPage.SignUpWindowClose.isDisplayed());
		AssertJUnit.assertEquals("Page title is not displayed in Sign Up Page window", true,
				signupPage.PageTitle.isDisplayed());
		AssertJUnit.assertEquals("Username field is not displayed in Sign Up Page window", true,
				signupPage.UserName.isDisplayed());
		AssertJUnit.assertEquals("Password field is not displayed in Sign Up Page window", true,
				signupPage.password.isDisplayed());
		AssertJUnit.assertEquals("Show Password field is not displayed in Sign Up Page window", true,
				signupPage.AcceptPolicy.isDisplayed());
		signupPage.AcceptPolicy.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#app > div.container___hBWrc-scss.oblt > main > div.dialog__container___2uP4v-scss > div > div > div.signup___3YBek-scss.stepOne___2W8h_-scss > form > button")));
		AssertJUnit.assertEquals("Sign Up option is not displayed in Sign Up Page window", true,
				signupPage.SignUp.isDisplayed());
		signupPage.SignUpWindowClose.click();

	}

	/**
	 * This test checks if various elements in the forgetPassword window are present
	 * once the window has been loaded. It checks if the elements are displayed or
	 * not.
	 */
	@Test(groups = { "stable",
			"userinterface" }, description = " This test checks if various elements in the forgetPassword window are present once the\r\n"
					+ "window has been loaded. It checks if the elements are displayed or not.")
	public void CheckForgotPasswordPageElements() {
		Perform.GoToLoginPage();
		WebDriver driver = Perform.ReturnActiveDriverInstance();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		LoginWindow loginPage = new LoginWindow(driver);
		loginPage.ForgotPassoword.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("#app > div.container___hBWrc-scss.oblt > div.dialog__container___2uP4v-scss > div")));
		ForgotPasswordWindow forgetPassword = new ForgotPasswordWindow(driver);

		AssertJUnit.assertEquals("Forget Password window is not displayed", true,
				forgetPassword.ForgotPasswordDialogueWindow.isDisplayed());
		AssertJUnit.assertEquals("Forget Password window close button is not displayed", true,
				forgetPassword.ForgotPasswordWindowClose.isDisplayed());
		AssertJUnit.assertEquals("Page title is not displayed in Forget Password window", true,
				forgetPassword.PageTitle.isDisplayed());
		AssertJUnit.assertEquals("Page content is not displayed in Forget Password window", true,
				forgetPassword.PageContent.isDisplayed());
		AssertJUnit.assertEquals("Username field is not displayed in Forget Password window", true,
				forgetPassword.UserName.isDisplayed());

		forgetPassword.UserName.click();
		forgetPassword.UserName.sendKeys("abc@gmail.com");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#app > div.container___hBWrc-scss.oblt > div.dialog__container___2uP4v-scss > div > div > div:nth-child(3) > form > button")));
		AssertJUnit.assertEquals("Proceed Button is not displayed in Forget Password window", true,
				forgetPassword.Proceed.isDisplayed());
		forgetPassword.ForgotPasswordWindowClose.click();
	}

	/**
	 * This method is executed at the end of every test case which navigates to
	 * login page if it is in other page
	 */
	@AfterMethod(groups = { "stable", "userinterface" })
	public void GoBackToLoginPage() {

		Perform.ClickGoBack();
	}

}
