package SeleniumTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObjects.LoginPageResponse;
import PageObjects.LoginWindow;
import PageObjects.MainPage;
import Utils.Config;
import Utils.Config.Credentials;
import Utils.Perform;

/**
 * This test class is used to test the security of login page of optibet.lv. It
 * checks cookie, sql injection and css injection methods for security testing.
 * It also inherits the beforegroup and aftergroup properties from
 * ParentTestClass
 */
public class SecurityTests extends ParentTestClass {

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
		Perform.ClickLogin(Config.Credentials.Valid.Username, Config.Credentials.Valid.Password);
		Perform.WaitElementsAfterValidLogin();
		WebDriver driver = Perform.ReturnActiveDriverInstance();
		LoginPageResponse loginPageResponse = new LoginPageResponse(driver);
		AssertJUnit.assertEquals("The Account profile link section is not present",
				loginPageResponse.AccountProfileLink.isDisplayed(), true);
		AssertJUnit.assertEquals("The Balance Amount section is not present",
				loginPageResponse.DepositNow.isDisplayed(), true);
		AssertJUnit.assertEquals("The Logout option not present", loginPageResponse.LogOut.getText(), "Logout");
		String AuthentcatedUrl = driver.getCurrentUrl();
		// Step 2:Navigates back to the previous Login in page
		Perform.BackButtonAfterLogin();
		// Step 3:Navigates to the logged-in page to see if the cookie is still valid
		Perform.OpenUrl(AuthentcatedUrl);
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
		Perform.ClickLogin(Config.Credentials.Valid.Username, Config.Credentials.Valid.Password);
		Perform.WaitElementsAfterValidLogin();
		WebDriver driver = Perform.ReturnActiveDriverInstance();
		LoginPageResponse loginPageResponse = new LoginPageResponse(driver);
		AssertJUnit.assertEquals("The Account profile link section is not present",
				loginPageResponse.AccountProfileLink.isDisplayed(), true);
		AssertJUnit.assertEquals("The Balance Amount section is not present",
				loginPageResponse.DepositNow.isDisplayed(), true);
		AssertJUnit.assertEquals("The Logout option not present", loginPageResponse.LogOut.getText(), "Logout");
		String CurrentUrl = Perform.ReturnCurrentUrl();
		// Step 2:Visit a random pages
		Perform.OpenUrl("https://www.google.com/");
		// Step 3:Navigates to the logged-in page to see if the cookie is still valid
		Perform.OpenUrl(CurrentUrl);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#topBar > div.page-content___3oo_o-scss.topBarContent___3cr_D-scss > div.main___2Aq79-scss > div > span.container___1dsKe-scss.undefined > a")));
		AssertJUnit.assertEquals("The Account profile link section is not present",
				loginPageResponse.AccountProfileLink.isDisplayed(), true);
		AssertJUnit.assertEquals("The Balance Amount section is not present",
				loginPageResponse.DepositNow.isDisplayed(), true);
		AssertJUnit.assertEquals("The Logout option not present", loginPageResponse.LogOut.getText(), "Logout");
		// Step 4:Logs-out from the browser
		Perform.ClickGoBack();
		// Step 5:goes to the logged in page to see of the cookie session is still valid
		// or not
		Perform.OpenUrl(CurrentUrl);
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

		Perform.ClickLogin(Config.Credentials.Valid.Username, Config.Credentials.Valid.Password);
		Perform.WaitElementsAfterValidLogin();
		WebDriver driver = Perform.ReturnActiveDriverInstance();
		LoginPageResponse loginPageResponse = new LoginPageResponse(driver);
		AssertJUnit.assertEquals("The Account profile link section is not present",
				loginPageResponse.AccountProfileLink.isDisplayed(), true);
		AssertJUnit.assertEquals("The Balance Amount section is not present",
				loginPageResponse.DepositNow.isDisplayed(), true);
		AssertJUnit.assertEquals("The Logout option not present", loginPageResponse.LogOut.getText(), "Logout");
		String CurrentUrl = Perform.ReturnCurrentUrl();
		// Step 2:Close the browser
		Perform.CloseDriver();
		// Step 3:Open the browser and Reopen the logged in page to see of the cookie
		// session is still valid or not
		Perform.InitializeDriver(browsername);
		driver = Perform.ReturnActiveDriverInstance();
		Perform.OpenUrl(CurrentUrl);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"topBar\"]/div[1]/div[2]/button[1]")));
		MainPage mainPage = new MainPage(driver);
		AssertJUnit.assertEquals("The Account is logged in but should be logged out", mainPage.LoginButton.getText(),
				"Login");
	}

	/**
	 * This test performs SQL injection in username field. SQL injection is a code
	 * injection technique, used to attack data-driven applications, in which
	 * malicious SQL statements are inserted into an entry field for execution This
	 * test is data driven, for this test each of the test data tries to insert some
	 * section of sql query.
	 */
	@Test(dataProvider = "SQLQueryInjectionData", groups = { "stable",
			"security" }, description = "This test performs SQL injection"
					+ " in username field. SQL injection is a code injection technique, used to attack data-driven\r\n"
					+ " applications, in which malicious SQL statements are inserted into an entry\r\n"
					+ " field for execution This test is data driven, for this test each of the test\r\n"
					+ " data tries to insert some section of sql query. The test steps are:"
					+ " Step 1:Insert syntax of SQL query in username and password field"
					+ " Step 2:Verify the response of the login is failed and this injection does not affect anything")
	public void SQLInjectionToBypassAuthenticationInUsername(String usernamevlue) {
		// Step 1:Insert syntax of SQL query in username field
		Perform.ClickLogin(usernamevlue, Credentials.Valid.Password);
		// Step 2:Verify the response of the login is failed and this injection does not
		// affect anything
		WebDriver driver = Perform.ReturnActiveDriverInstance();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div[2]/div/div/form/div[1]/div")));
		AssertJUnit.assertEquals("The Username response message field is not red in color", Config.TextColor.redcolor,
				Perform.ReadLoginResponseMessageColorUsernameField());
	}

	/**
	 * This test performs SQL injection in password field. SQL injection is a code
	 * injection technique, used to attack data-driven applications, in which
	 * malicious SQL statements are inserted into an entry field for execution This
	 * test is data driven, for this test each of the test data tries to insert some
	 * section of sql query.
	 */
	@Test(dataProvider = "SQLQueryInjectionData", groups = { "stable",
			"security" }, description = "This test performs SQL injection"
					+ " in password field. SQL injection is a code injection technique, used to attack data-driven\r\n"
					+ " applications, in which malicious SQL statements are inserted into an entry\r\n"
					+ " field for execution This test is data driven, for this test each of the test\r\n"
					+ " data tries to insert some section of sql query. The test steps are:"
					+ " Step 1:Insert syntax of SQL query in username and password field"
					+ " Step 2:Verify the response of the login is failed and this injection does not affect anything")
	public void SQLInjectionToBypassAuthenticationInPassword(String passwordvalue) {
		// Step 1:Insert syntax of SQL query in password field
		Perform.ClickLogin("Wrongvalue", passwordvalue);
		// Step 2:Verify the response of the login is failed and this injection does not
		// affect anything
		WebDriver driver = Perform.ReturnActiveDriverInstance();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
		LoginWindow loginPage = new LoginWindow(driver);
		AssertJUnit.assertEquals("The Account profile link section is not present",
				loginPage.LoginDialogueWindow.isDisplayed(), true);
		AssertJUnit.assertEquals("The Balance Amount section is not present", loginPage.LoginButton.isDisplayed(),
				true);
	}

	/**
	 * This test performs Cross site scripting in username field. XSS attacks enable
	 * attackers to inject client-side scripts into web pages viewed by other users.
	 * A cross-site scripting vulnerability may be used by attackers to bypass
	 * access controls such as the same-origin policy. This test is data driven, for
	 * this test each of the test data tries to create alert using javascript code.
	 */
	@Test(dataProvider = "XSSInjectionData", groups = { "stable", "security" }, description = "* This test "
			+ "performs Cross site scripting in username  field. XSS\r\n"
			+ "attacks enable attackers to inject client-side scripts into web pages viewed\r\n"
			+ "by other users. A cross-site scripting vulnerability may be used by attackers\r\n"
			+ "to bypass access controls such as the same-origin policy. This test is data\r\n"
			+ "driven, for this test each of the test data tries to create alert using javascript code.The test steps are "
			+ "Step 1:Insert xss type scripts in username field\"\r\n"
			+ "Step 2:Verify the response of the login is failed and this injection does not affect anything")
	public void XSSInjectionInUsername(String usernamevlue) {

		// Step 1:Insert xss type scripts in username field
		Perform.ClickLogin(usernamevlue, Credentials.Valid.Password);
		// Step 2:Verify the response of the login is failed and this injection does not
		// affect anything
		AssertJUnit.assertEquals("Alert is present during XSS injection", false, Perform.isAlertPresent());
		WebDriver driver = Perform.ReturnActiveDriverInstance();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div[2]/div/div/form/div[1]/div")));
		AssertJUnit.assertEquals("The Username response message field is not red in color", Config.TextColor.redcolor,
				Perform.ReadLoginResponseMessageColorUsernameField());
	}

	/**
	 * This test performs Cross site scripting in password field. XSS attacks enable
	 * attackers to inject client-side scripts into web pages viewed by other users.
	 * A cross-site scripting vulnerability may be used by attackers to bypass
	 * access controls such as the same-origin policy. This test is data driven, for
	 * this test each of the test data tries to create alert using javascript code.
	 */
	@Test(dataProvider = "XSSInjectionData", groups = { "stable", "security" }, description = "* This test "
			+ "performs Cross site scripting in password field. XSS\r\n"
			+ "attacks enable attackers to inject client-side scripts into web pages viewed\r\n"
			+ "by other users. A cross-site scripting vulnerability may be used by attackers\r\n"
			+ "to bypass access controls such as the same-origin policy. This test is data\r\n"
			+ "driven, for this test each of the test data tries to create alert using javascript code.The test steps are "
			+ "Step 1:Insert xss type scripts in  password field\"\r\n"
			+ "Step 2:Verify the response of the login is failed and this injection does not affect anything")
	public void XSSInjectionInPassword(String passwordvalue) {

		// Step 1:Insert xss type scripts in password field
		Perform.ClickLogin("Wrongvalue", passwordvalue);
		// Step 2:Verify the response of the login is failed and this injection does not
		// affect anything
		AssertJUnit.assertEquals("Alert is present during XSS injection", false, Perform.isAlertPresent());
	}

	/**
	 * This test performs Cross site scripting in the url. XSS attacks enable
	 * attackers to inject client-side scripts into web pages viewed by other users.
	 * A cross-site scripting vulnerability may be used by attackers to bypass
	 * access controls such as the same-origin policy. This test create alert using
	 * javascript code in the url query parameter
	 */
	@Test(groups = { "stable", "security" }, description = "* This test performs Cross site scripting in url. XSS\r\n"
			+ "attacks enable attackers to inject client-side scripts into web pages viewed\r\n"
			+ "by other users. A cross-site scripting vulnerability may be used by attackers\r\n"
			+ "to bypass access controls such as the same-origin policy. This test is data\r\n"
			+ "driven, for this test each of the test data tries to create alert using javascript code. The test steps are\r\n"
			+ "Step 1:Insert xss type scripts in url field\"\r\n"
			+ "Step 2:Verify this injection does not affect anything")
	public void XSSInjectionInUrl() {
		String CurrentUrl = Config.BaseURL;
		// Step 1:Insert xss type scripts in url field
		Perform.OpenUrl(CurrentUrl + "casino?authShown=<script>alert(123)</script>");
		// Step 2:Verify this injection does not affect anything
		AssertJUnit.assertEquals("Alert is present during XSS injection", false, Perform.isAlertPresent());
		Perform.OpenUrl(CurrentUrl);
	}

	/**
	 * This method provides data for XSSInjectionInUsernameAndPassword testmethod.
	 * Each of the test data tries to create alert using javascript code.
	 */
	@DataProvider(name = "XSSInjectionData")
	public Object[] XSSInjectionDataValues() {
		Object[] myData = { "<script>alert(123)</script>", "\"><script>alert(document.cookie)</script>",
				"\"onfocus=\"alert(document.cookie)", "\"><ScRiPt>alert(document.cookie)</ScRiPt>",
				"\"%3cscript%3ealert(document.cookie)%3c/script%3e",
				"<scr<script>ipt>alert(document.cookie)</script>" };
		return myData;
	}

	/**
	 * This method provides data for SQLInjectionToBypassAuthentication testmethod.
	 * each of the test data tries to insert some section of sql query.
	 */
	@DataProvider(name = "SQLQueryInjectionData")
	public Object[] SQLQueryInjectionDataValues() {
		Object[] myData = { "'-'", "' '", "'&'", "'^'", "'*'", "' or ''-'", "' or '' '", "' or ''&'", "' or ''^'",
				"' or ''*'", "\"-\"", "\" \"", "\"&\"", "\"^\"", "\"*\"", "\" or \"\"-\"", "\" or \"\" \"",
				"\" or \"\"&\"", "\" or \"\"^\"", "\" or \"\"*\"", "or true--", "\" or true--", "' or true--",
				"\") or true--", "') or true--", "' or 'x'='x", "') or ('x')=('x", "')) or (('x'))=(('x",
				"\" or \"x\"=\"x", "\") or (\"x\")=(\"x", "\")) or ((\"x\"))=((\"x", "' or '1'='1", "' or 1='1",
				"' or 1=1 --", "' OR 1=1;--", "1' or 1=1 -- -" };
		return myData;
	}

	/**
	 * This method is executed at the end of every test case which navigates to
	 * login page if it is in other page
	 */
	@AfterMethod(groups = { "stable", "security" })
	public void GoBackToLoginPage() {

		Perform.ClickGoBack();
	}

}
