package LoginPageCheck;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObjects.LoginWindow;
import PageObjects.MainPage;
import Utils.Config;
import Utils.Perform;

/**
 * This test class is used to test the Login Functionality of the Optibet.lv
 * website. It checks the login function with various valid and invalid
 * credentials and tab,enter functions. It also inherits the beforegroup and
 * aftergroup properties from ParentTestClass
 */
public class FunctionalityTests extends ParentTestClass {
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
	 * This test checks the tab and enter keys functionality in the login page. The
	 * tab key when pressed should select next element
	 */
	@Test(groups = { "stable",
			"functionality" }, description = ("This test checks the tab and enter keys functionality in the login page. The\r\n"
					+ "	 tab key when pressed should select next element The test steps of this test case are:"
					+ "Step 1: Click in username field and Send TAB keys in Username field. After tab key are sent it should select password field"
					+ "Step 2: Send TAB keys when in the password field. After tab keys are sent it sould select LoginButton"
					+ "Step 3: Send Enter keys when Login Button is selected. After enter keys are sent it should click LoginButton"))
	public void CheckTabAndEntrInLoginPage() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#topBar > div.page-content___3oo_o-scss.topBarContent___3cr_D-scss > div.main___ejdhD-scss > button.button-base___Zmw1k-scss.button___25A2P-scss.button_size-default___2lYeH-scss.button_intent-default___2Ljl_-scss.login___Ltkvq-scss.optionsButton")));
		MainPage mainPage = new MainPage(driver);
		mainPage.LoginButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		LoginWindow loginPage = new LoginWindow(driver);
		// Step 1: Click in username field and Send TAB keys in Username field. After
		// tab key are sent it should select password field
		loginPage.UserName.click();
		loginPage.UserName.sendKeys("apple@123.gmail.com");
		loginPage.UserName.sendKeys(Keys.TAB);
		AssertJUnit.assertEquals("The password element has not been selected", true,
				loginPage.PassWord.equals(driver.switchTo().activeElement()));
		// Step 2: Send TAB keys when in the password field. After tab keys are sent it
		// should select LoginButton
		loginPage.PassWord.sendKeys("WrongPassword");
		loginPage.PassWord.sendKeys(Keys.TAB);
		AssertJUnit.assertEquals("The LoginButton element has not been selected", true,
				loginPage.LoginButton.equals(driver.switchTo().activeElement()));
		// Step 3: Send Enter keys when Login Button is selected. After enter keys are
		// sent it should click LoginButton
		loginPage.LoginButton.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\"app\"]/div[1]/main/div[2]/div/div/form/div[2]/div[1]/div")));
		AssertJUnit.assertEquals("Username or password is incorrect is not present in response message",
				Config.TestMessages.InvalidCredentials, Perform.ReadLoginResponseMessagePasswordField(driver));
		AssertJUnit.assertEquals("The Password response message is not red in color", Config.TextColor.redcolor,
				Perform.ReadLoginResponseMessageColorPasswordField(driver));

	}

	/**
	 * This test checks the show password/ visiblity functionality in the login page
	 * in the password field.
	 */
	@Test(groups = { "stable",
			"functionality" }, description = ("This test checks the show password/ visiblity functionality in the login page The\r\n"
					+ "	 in the password field. The list of steps are"
					+ "Step 1: Click in username field and Send TAB keys in Username field. After tab key are sent it should select password field"
					+ "Step 2: Send TAB keys when in the password field. After tab keys are sent it sould select LoginButton"
					+ "Step 3: Send Enter keys when Login Button is selected. After enter keys are sent it should click LoginButton"))
	public void CheckShowPasswordVisiblityButton() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#topBar > div.page-content___3oo_o-scss.topBarContent___3cr_D-scss > div.main___ejdhD-scss > button.button-base___Zmw1k-scss.button___25A2P-scss.button_size-default___2lYeH-scss.button_intent-default___2Ljl_-scss.login___Ltkvq-scss.optionsButton")));
		MainPage mainPage = new MainPage(driver);
		mainPage.LoginButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		LoginWindow loginPage = new LoginWindow(driver);
		// Step 1: Pass a random value in Password field and check if it is masked (text
		// type is password or not)
		loginPage.UserName.click();
		loginPage.UserName.sendKeys("apple@123.gmail.com");
		loginPage.PassWord.click();
		loginPage.PassWord.sendKeys("SecretPassword");
		System.out.println(loginPage.PassWord.getAttribute("type"));
		AssertJUnit.assertEquals("The password is not masked",
				loginPage.PassWord.getAttribute("type").equals("password"), true);
		// Step 2: Click show password button and check is the password field is visible
		// now (text type is text or not)
		loginPage.ShowPassword.click();
		loginPage.ShowPassword.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		System.out.println(loginPage.PassWord.getAttribute("type"));
		AssertJUnit.assertEquals("The password is not masked", loginPage.PassWord.getAttribute("type").equals("text"),
				true);
	}

	/**
	 * This test checks the login function with Username Less Than Six Character and
	 * valid, invalid, empty password. This test is data driven, it checks the login
	 * with multiple data through dataprovider.
	 */
	@Test(dataProvider = "UsernameLessThanSixCharacter", groups = { "stable",
			"functionality" }, description = "This test checks the login function with username less than six char and valid, invalid and empty password. It then verifies the response message\r\n"
					+ "	  This test is data driven, it checks the login with multiple data through\r\n"
					+ "	  dataprovider.")
	public void UsernameLessThanSixChar(String usernamevlue, String passwordvalue) {

		Perform.ClickLogin(driver, usernamevlue, passwordvalue);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div[2]/div/div/form/div[1]/div")));
		AssertJUnit.assertEquals("Username can not be less than 6 characters is not present in response message",
				Config.TestMessages.LessThanSixChar, Perform.ReadLoginResponseMessageUsernameField(driver));
		if (passwordvalue == "") {
			AssertJUnit.assertEquals("Username can not be less than 6 characters is not present in response message",
					Config.TestMessages.PasswordEmpty, Perform.ReadLoginResponseMessagePasswordField(driver));
			AssertJUnit.assertEquals("The Password response message is not red in color", Config.TextColor.redcolor,
					Perform.ReadLoginResponseMessageColorPasswordField(driver));
		}
		AssertJUnit.assertEquals("The Username response message field is not red in color", Config.TextColor.redcolor,
				Perform.ReadLoginResponseMessageColorUsernameField(driver));
	}

	/**
	 * This test checks the login function with Username MoreThan Thirty two
	 * Character and valid, invalid, empty password. This test is data driven, it
	 * checks the login with multiple data through dataprovider.
	 */
	@Test(dataProvider = "UsernameMoreThanThirtytwoCharacter", groups = { "stable",
			"functionality" }, description = "This test checks the login function with username more than thirtytwo char and valid, invalid password. It then verifies the response message\r\n"
					+ "	  This test is data driven, it checks the login with multiple data through\r\n"
					+ "	  dataprovider.")
	public void UsernameMoreThanThirtytwoChar(String usernamevlue, String passwordvalue) {

		Perform.ClickLogin(driver, usernamevlue, passwordvalue);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div[2]/div/div/form/div[1]/div")));
		AssertJUnit.assertEquals("Username can not be more than 32 characters is not present in response message",
				Config.TestMessages.MoreThanThirtytwoChar, Perform.ReadLoginResponseMessageUsernameField(driver));
		if (passwordvalue == "") {
			AssertJUnit.assertEquals("Username can not be less than 6 characters is not present in response message",
					Config.TestMessages.PasswordEmpty, Perform.ReadLoginResponseMessagePasswordField(driver));
			AssertJUnit.assertEquals("The Password response message is not red in color", Config.TextColor.redcolor,
					Perform.ReadLoginResponseMessageColorPasswordField(driver));
		}
		AssertJUnit.assertEquals("The Username response message field is not red in color", Config.TextColor.redcolor,
				Perform.ReadLoginResponseMessageColorUsernameField(driver));
	}

	/**
	 * This test checks the login function with Username With Invalid Character and
	 * valid, invalid, empty password. This test is data driven, it checks the login
	 * with multiple data through dataprovider.
	 */
	@Test(dataProvider = "UsernameWithInvalidCharacter", groups = { "stable",
			"functionality" }, description = "This test checks the login function with username with invalid char and valid, invalid, empty password. It then verifies the response message\r\n"
					+ "	  This test is data driven, it checks the login with multiple data through\r\n"
					+ "	  dataprovider.")
	public void UsernameWithInvalidChar(String usernamevlue, String passwordvalue) {

		Perform.ClickLogin(driver, usernamevlue, passwordvalue);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div[2]/div/div/form/div[1]/div")));
		AssertJUnit.assertEquals("Use Latin letters, numbers, and underscores only, with no spaces. is not present",
				Config.TestMessages.InvalidCharacter, Perform.ReadLoginResponseMessageUsernameField(driver));
		if (passwordvalue == "") {
			AssertJUnit.assertEquals("Username can not be less than 6 characters is not present in response message",
					Config.TestMessages.PasswordEmpty, Perform.ReadLoginResponseMessagePasswordField(driver));
			AssertJUnit.assertEquals("The Password response message is not red in color", Config.TextColor.redcolor,
					Perform.ReadLoginResponseMessageColorPasswordField(driver));
		}
		AssertJUnit.assertEquals("The Username response message field is not red in color", Config.TextColor.redcolor,
				Perform.ReadLoginResponseMessageColorUsernameField(driver));
	}

	/**
	 * This test checks the login function with Username With Invalid email id and
	 * valid, invalid, empty password. This test is data driven, it checks the login
	 * with multiple data through dataprovider.
	 */
	@Test(dataProvider = "UsernameWithInvalidEmailID", groups = { "stable",
			"functionality" }, description = "This test checks the login function with username with invalid email id and valid, invalid, empty password. It then verifies the response message\r\n"
					+ "	  This test is data driven, it checks the login with multiple data through\r\n"
					+ "	  dataprovider.")
	public void UsernameWithInvalidEmail(String usernamevlue, String passwordvalue) {

		Perform.ClickLogin(driver, usernamevlue, passwordvalue);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div[2]/div/div/form/div[1]/div")));
		AssertJUnit.assertEquals("Please enter a valid email is not present in response",
				Config.TestMessages.InvalidEmail, Perform.ReadLoginResponseMessageUsernameField(driver));
		if (passwordvalue == "") {
			AssertJUnit.assertEquals("Username can not be less than 6 characters is not present in response message",
					Config.TestMessages.PasswordEmpty, Perform.ReadLoginResponseMessagePasswordField(driver));
			AssertJUnit.assertEquals("The Password response message is not red in color", Config.TextColor.redcolor,
					Perform.ReadLoginResponseMessageColorPasswordField(driver));
		}
		AssertJUnit.assertEquals("The Username response message field is not red in color", Config.TextColor.redcolor,
				Perform.ReadLoginResponseMessageColorUsernameField(driver));
	}

	/// This test is commented because it blocks the account after ruuning several
	/// times
//	/**
//	 * This test checks the login function with valid username and invalid password.
//	 * This test is data driven, it checks the login with multiple data through
//	 * dataprovider.
//	 */
//	@Test(dataProvider = "ValidUsernameInvalidPassword", groups = { "stable",
//			"functionality" }, description = "This test checks the login function with valid username and valid, invalid password. It then verifies the invalid login response message\r\n"
//					+ "	  This test is data driven, it checks the login with multiple data through\r\n"
//					+ "	  dataprovider.")
//	public void ValidUsernameAndInvalidPassword(String usernamevlue, String passwordvalue) {
//
//		Perform.ClickLogin(driver, usernamevlue, passwordvalue);
//		WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(
//				By.xpath("//*[@id=\"app\"]/div[1]/main/div[2]/div/div/form/div[2]/div[1]/div")));
//		AssertJUnit.assertEquals("Username or password is incorrect is not present in response message",
//				Config.TestMessages.InvalidCredentials, Perform.ReadLoginResponseMessagePasswordField(driver));
//		AssertJUnit.assertEquals("The Password response message is not red in color", Config.TextColor.redcolor,
//				Perform.ReadLoginResponseMessageColorPasswordField(driver));
//	}

	/**
	 * This test checks the login function with Invalid username and valid password.
	 * This test is data driven, it checks the login with multiple data through
	 * dataprovider.
	 */
	@Test(dataProvider = "InvalidUsernameValidPassword", groups = { "stable",
			"functionality" }, description = "This test checks the login function with invalid username and valid password. It then verifies the invalid login response message\r\n"
					+ "	  This test is data driven, it checks the login with multiple data through\r\n"
					+ "	  dataprovider.")
	public void InvalidUsernameAndValidPassword(String usernamevlue, String passwordvalue) {
		Perform.ClickLogin(driver, usernamevlue, passwordvalue);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\"app\"]/div[1]/main/div[2]/div/div/form/div[2]/div[1]/div")));
		AssertJUnit.assertEquals("Username or password is incorrect is not present in response message",
				Config.TestMessages.InvalidCredentials, Perform.ReadLoginResponseMessagePasswordField(driver));
		AssertJUnit.assertEquals("The Password response message is not red in color", Config.TextColor.redcolor,
				Perform.ReadLoginResponseMessageColorPasswordField(driver));

	}

	/**
	 * This test checks the login function with Invalid username and Invalid
	 * password. This test is data driven, it checks the login with multiple data
	 * through dataprovider.
	 */
	@Test(dataProvider = "InvalidUsernameInvalidPassword", groups = { "stable",
			"functionality" }, description = "This test checks the login function with invalid username and invalid password. It then verifies the invalid login response message\r\n"
					+ "	  This test is data driven, it checks the login with multiple data through\r\n"
					+ "	  dataprovider.")
	public void InvalidUsernameAndInvalidPassword(String usernamevlue, String passwordvalue) {
		Perform.ClickLogin(driver, usernamevlue, passwordvalue);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\"app\"]/div[1]/main/div[2]/div/div/form/div[2]/div[1]/div")));
		AssertJUnit.assertEquals("Username or password is incorrect is not present in response message",
				Config.TestMessages.InvalidCredentials, Perform.ReadLoginResponseMessagePasswordField(driver));
		AssertJUnit.assertEquals("The Password response message is not red in color", Config.TextColor.redcolor,
				Perform.ReadLoginResponseMessageColorPasswordField(driver));
	}

	/**
	 * This method provides data for UsernameLessThanSixChar test
	 */
	@DataProvider(name = "UsernameLessThanSixCharacter")
	public Object[][] getUsernameLessThanSixCharData() {
		Object[][] myData = { { "ASdfg", "asdf" }, { "a1!._", "" }, { "", Config.Credentials.Valid.Password },
				{ "A_123", "ASDF" }, { "a!.%^", "1234" }, { "", "" } };
		return myData;
	}

	/**
	 * This method provides data for UsernameWithInvalidCharacter test
	 */
	@DataProvider(name = "UsernameWithInvalidCharacter")
	public Object[][] getUsernameWithInvalidCharacterData() {
		Object[][] myData = { { "ASdf asdf", "asdf" }, { "asd!^1345", "" },
				{ "ABC()*9812abc", Config.Credentials.Valid.Password }, { "abcABV+", "ASDF" }, { "!ABab123", "1234" } };
		return myData;
	}

	/**
	 * This method provides data for UsernameMoreThanThirtytwoCharacter test
	 */
	@DataProvider(name = "UsernameMoreThanThirtytwoCharacter")
	public Object[][] getUsernameMoreThanThirtytwoCharacterData() {
		Object[][] myData = { { "asdfasdfasdfasdfasdfasdfasdfasdfa", "asdf" },
				{ "#%^&*()_+!#%^&*()_+!#%^&*(.)_+!!$%", "" },
				{ "asdfasdf234AV_.DGRRF3455512334545234", Config.Credentials.Valid.Password },
				{ "1234567ADFG56778123412341asdfasdsdfasdfa123", "ASDF" },
				{ "12345678912345677812341234123412342123", "1234" } };
		return myData;
	}

	/**
	 * This method provides data for UsernameWithInvalidEmailID test
	 */
	@DataProvider(name = "UsernameWithInvalidEmailID")
	public Object[][] getUsernameWithInvalidEmailIDData() {
		Object[][] myData = { { "asdfasdfasdfa@sdfasdfasdfasdfasdfa", "asdf" }, { "12323@gmail.comando aa", "" },
				{ "ABC1233@gmail.com@oa", Config.Credentials.Valid.Password },
				{ Config.Credentials.Valid.Username + "!ail.com", "ASDF" }, { "myname_apple@gma!il.com", "1234" } };
		return myData;
	}

	/**
	 * This method provides data for ValidUsernameAndInvalidPassword test
	 */
	@DataProvider(name = "ValidUsernameInvalidPassword")
	public Object[][] getValidUsernameInvalidPasswordData() {
		Object[][] myData = { { Config.Credentials.Valid.Username, Config.Credentials.Invalid.Password.Allmix } };
		return myData;
	}

	/**
	 * This method provides data for InvalidUsernameAndValidPassword test
	 */
	@DataProvider(name = "InvalidUsernameValidPassword")
	public Object[][] getInvalidUsernameValidPasswordData() {
		Object[][] myData = { { "ADMINISTRATOR", Config.Credentials.Valid.Password },
				{ "administrator", Config.Credentials.Valid.Password },
				{ "adminADMIN", Config.Credentials.Valid.Password },
				{ "admin123ADMin", Config.Credentials.Valid.Password },
				{ "aba.adf_AVC", Config.Credentials.Valid.Password },
				{ "asdfdas@123.com", Config.Credentials.Valid.Password },
				{ "!$%^&!@adfmin.com", Config.Credentials.Valid.Password } };
		return myData;
	}

	/**
	 * This method provides data for InvalidUsernameAndInvalidPassword test
	 */
	@DataProvider(name = "InvalidUsernameInvalidPassword")
	public Object[][] getInvalidUsernameInvalidPasswordData() {
		Object[][] myData = { { "ADMINISTRATOR", Config.Credentials.Invalid.Password.ShortCharacters },
				{ "administrator", Config.Credentials.Invalid.Password.longCharacters },
				{ "adminADMIN", Config.Credentials.Invalid.Password.OnlyLetters },
				{ "admin123ADMin", Config.Credentials.Invalid.Password.OnlyNumbers },
				{ "aba.adf_AVC", Config.Credentials.Invalid.Password.OnlySpecialSymbols },
				{ "asdfdas@123.com", Config.Credentials.Invalid.Password.NoSpecialSymbol },
				{ "!$%^&!@adfmin.com", Config.Credentials.Invalid.Password.Allmix } };
		return myData;
	}

	/**
	 * This method is executed at the end of every test case which navigates to
	 * login page if it is in other page
	 */
	@AfterMethod(groups = { "stable", "functionality" })
	public void GoBackToLoginPage() {
		Perform.ClickGoBack(driver);
	}

}
