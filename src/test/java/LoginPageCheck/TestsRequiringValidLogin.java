package LoginPageCheck;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObjects.LoginPageResponse;
import PageObjects.MainPage;
import Utils.Config;
import Utils.Perform;

public class TestsRequiringValidLogin {

	RemoteWebDriver driver;

	/**
	 * This method runs shortly before the first test method that belongs to any of
	 * these groups is invoked. An instance of webdriver mentioned in browsername in
	 * parameters will get created through this method
	 */
	@BeforeClass(groups = { "stable", "functionality", "userinterface", "security" })
	@Parameters({ "browser" })
	public void initialize(@Optional String browsername) {
		driver = Perform.InitializeDriver(browsername);
	}

	/**
	 * This method run shortly after the last test method that belongs to any of
	 * these groups is invoked. It shut down the web driver instance or destroy the
	 * web driver instance(Close all the windows).
	 */
	@AfterClass(groups = { "stable", "functionality", "userinterface", "security" })
	public void close() {
		Perform.CloseDriver(driver);
	}

	/**
	 * This test checks the login function with valid username and valid password.
	 */
	@Test(groups = { "stable",
			"functionality" }, description = "This test checks the login function with valid username and valid password. It then verifies the valid login response.")
	public void ValidUsernameAndValidPassword() {
		Perform.ClickLogin(driver, Config.Credentials.Valid.Username, Config.Credentials.Valid.Password);
		Perform.WaitElementsAfterValidLogin(driver);
		LoginPageResponse loginPageResponse = new LoginPageResponse(driver);
		AssertJUnit.assertEquals("The Account profile link section is not present",
				loginPageResponse.AccountProfileLink.isDisplayed(), true);
		AssertJUnit.assertEquals("The Balance Amount section is not present",
				loginPageResponse.DepositNow.isDisplayed(), true);
		AssertJUnit.assertEquals("The Logout option not present", loginPageResponse.LogOut.getText(), "Logout");
	}

	/**
	 * This test checks the back button and cookie session of the browser. It
	 * initially log-in to the login pages with valid username and password and
	 * creates a cookie session. It then navigates back to the previous page in the
	 * browser and then navigates to the logged-in page to see if the cookie is
	 * still valid.
	 */
	@Test(groups = { "stable",
			"security" }, description = "This test checks the back button and cookie session of the browser. The test steps are "
					+ "Step 1:Log-in to the login pages with valid username and password and creates a cookie session "
					+ "Step 2:Navigates back to the previous Login in page "
					+ "Step 3:Navigates to the logged-in page to see if the cookie is still valid.")
	public void BackButtonCheckAfterLogin() {
		// Step 1:Log-in to the login pages with valid username and password and creates
		// a cookie session
		Perform.ClickLogin(driver, Config.Credentials.Valid.Username, Config.Credentials.Valid.Password);
		Perform.WaitElementsAfterValidLogin(driver);
		LoginPageResponse loginPageResponse = new LoginPageResponse(driver);
		AssertJUnit.assertEquals("The Account profile link section is not present",
				loginPageResponse.AccountProfileLink.isDisplayed(), true);
		AssertJUnit.assertEquals("The Balance Amount section is not present",
				loginPageResponse.DepositNow.isDisplayed(), true);
		AssertJUnit.assertEquals("The Logout option not present", loginPageResponse.LogOut.getText(), "Logout");
		String AuthentcatedUrl = driver.getCurrentUrl();
		// Step 2:Navigates back to the previous Login in page
		Perform.BackButtonAfterLogin(driver);
		// Step 3:Navigates to the logged-in page to see if the cookie is still valid
		Perform.OpenUrl(driver, AuthentcatedUrl);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#topBar > div.page-content___3oo_o-scss.topBarContent___3cr_D-scss > div.main___2Aq79-scss > div > span.container___1dsKe-scss.undefined > a")));
		AssertJUnit.assertEquals("The Account profile link section is not present",
				loginPageResponse.AccountProfileLink.isDisplayed(), true);
		AssertJUnit.assertEquals("The Balance Amount section is not present",
				loginPageResponse.DepositNow.isDisplayed(), true);
		AssertJUnit.assertEquals("The Logout option not present", loginPageResponse.LogOut.getText(), "Logout");
	}

	/**
	 * This test checks the cookie session of the browser. It initially log-in to
	 * the login pages with valid username and password and creates a cookie
	 * session. It then visits a random pages and goes to the logged in page to
	 * check if the cookie session is valid. It then logs-out from the browser and
	 * goes to the logged in page to see of the cookie session is still valid or
	 * not.
	 */
	@Test(groups = { "stable",
			"security" }, description = "This test checks the cookie session in the browser. The test steps are "
					+ "Step 1:Log-in to the login pages with valid username and password and creates a cookie session"
					+ "Step 2:Visit a random page "
					+ "Step 3:Navigates to the logged-in page to see if the cookie is still valid."
					+ "Step 4:Logs-out from the browser"
					+ "Step 5:goes to the logged in page to see of the cookie session is still valid or not")
	public void CookieSessionCheckWithoutClosingBrowser() {
		// Step 1:Log-in to the login pages with valid username and password and creates
		// a cookie session
		Perform.ClickLogin(driver, Config.Credentials.Valid.Username, Config.Credentials.Valid.Password);
		Perform.WaitElementsAfterValidLogin(driver);
		LoginPageResponse loginPageResponse = new LoginPageResponse(driver);
		AssertJUnit.assertEquals("The Account profile link section is not present",
				loginPageResponse.AccountProfileLink.isDisplayed(), true);
		AssertJUnit.assertEquals("The Balance Amount section is not present",
				loginPageResponse.DepositNow.isDisplayed(), true);
		AssertJUnit.assertEquals("The Logout option not present", loginPageResponse.LogOut.getText(), "Logout");
		String CurrentUrl = Perform.ReturnCurrentUrl(driver);
		// Step 2:Visit a random pages
		Perform.OpenUrl(driver, "https://www.google.com/");
		// Step 3:Navigates to the logged-in page to see if the cookie is still valid
		Perform.OpenUrl(driver, CurrentUrl);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#topBar > div.page-content___3oo_o-scss.topBarContent___3cr_D-scss > div.main___2Aq79-scss > div > span.container___1dsKe-scss.undefined > a")));
		AssertJUnit.assertEquals("The Account profile link section is not present",
				loginPageResponse.AccountProfileLink.isDisplayed(), true);
		AssertJUnit.assertEquals("The Balance Amount section is not present",
				loginPageResponse.DepositNow.isDisplayed(), true);
		AssertJUnit.assertEquals("The Logout option not present", loginPageResponse.LogOut.getText(), "Logout");
		// Step 4:Logs-out from the browser
		Perform.ClickGoBack(driver);
		// Step 5:goes to the logged in page to see of the cookie session is still valid
		// or not
		Perform.OpenUrl(driver, CurrentUrl);
		MainPage mainPage = new MainPage(driver);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"topBar\"]/div[1]/div[2]/button[1]")));
		AssertJUnit.assertEquals("The Account is logged in but should be logged out", mainPage.LoginButton.getText(),
				"Login");
	}

	/**
	 * This test checks the cookie session of the browser. It initially log-in to
	 * the login pages with valid username and password and creates a cookie
	 * session. It then closes the browser and reopens the logged in page to see of
	 * the cookie session is still valid or not.
	 */
	@Test(groups = { "stable",
			"security" }, description = "This test checks the cookie session in the browser. The test steps are "
					+ "Step 1:Log-in to the login pages with valid username and password and creates a cookie session"
					+ "Step 2:Close the browser "
					+ "Step 3:Open the browser and Reopen the logged in page to see of the cookie session is still valid or not ")
	@Parameters({ "browser" })
	public void CookieSessionCheckAfterClosingBrowser(@Optional String browsername) {
		// Step 1:Log-in to the login pages with valid username and password and creates
		// a cookie session

		Perform.ClickLogin(driver, Config.Credentials.Valid.Username, Config.Credentials.Valid.Password);
		Perform.WaitElementsAfterValidLogin(driver);
		LoginPageResponse loginPageResponse = new LoginPageResponse(driver);
		AssertJUnit.assertEquals("The Account profile link section is not present",
				loginPageResponse.AccountProfileLink.isDisplayed(), true);
		AssertJUnit.assertEquals("The Balance Amount section is not present",
				loginPageResponse.DepositNow.isDisplayed(), true);
		AssertJUnit.assertEquals("The Logout option not present", loginPageResponse.LogOut.getText(), "Logout");
		String CurrentUrl = Perform.ReturnCurrentUrl(driver);
		// Step 2:Close the browser
		Perform.CloseDriver(driver);
		// Step 3:Open the browser and Reopen the logged in page to see of the cookie
		// session is still valid or not
		driver = Perform.InitializeDriver(browsername);
		Perform.OpenUrl(driver, CurrentUrl);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"topBar\"]/div[1]/div[2]/button[1]")));
		MainPage mainPage = new MainPage(driver);
		AssertJUnit.assertEquals("The Account is logged in but should be logged out", mainPage.LoginButton.getText(),
				"Login");
	}

	/**
	 * This method is executed at the end of every test case which navigates to
	 * login page if it is in other page
	 */
	@AfterMethod(groups = { "stable", "security" })
	public void GoBackToLoginPage() {

		Perform.ClickGoBack(driver);
	}

}
