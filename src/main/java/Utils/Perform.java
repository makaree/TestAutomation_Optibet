package Utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.LoginPageResponse;
import PageObjects.LoginWindow;
import PageObjects.MainPage;
import PageObjects.SessionExpiredWindow;

/**
 * This class performs various tasks for the test methods. It is a helper class
 * used to perform various tasks
 */
public class Perform {
	private static WebDriver driver;

	/**
	 * This method creates an instance of webdriver mentioned in browsername in
	 * parameters
	 */
	public static void InitializeDriver(String browsername) {
		browsername = browsername != null ? browsername : "FireFox";
		if (browsername.equals("Chrome")) {
			try {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/Drivers/ChromeDriver/chromedriver.exe");
				driver = new ChromeDriver();
			} catch (org.openqa.selenium.WebDriverException e) {
				System.out
						.println("The Chrome web browser might not be installed or did not find it. " + e.getMessage());
			}
		}
		if (browsername.equals("InternetExplorer")) {
			try {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "/Drivers/IEDriver/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			} catch (org.openqa.selenium.WebDriverException e) {
				System.out.println("The InternetExplorer web browser might not be installed or did not find it. "
						+ e.getMessage());
			}
		}
		if (browsername.equals("FireFox")) {
			try {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/Drivers/FireFoxDriver/geckodriver.exe");
				driver = new FirefoxDriver();
			} catch (org.openqa.selenium.WebDriverException e) {
				System.out.println(
						"The FireFox web browser might not be installed or did not find it. " + e.getMessage());
			}
		}
		if (browsername.equals("Opera")) {
			try {
				System.setProperty("webdriver.opera.driver",
						System.getProperty("user.dir") + "/Drivers/OperaDriver/operadriver.exe");
				OperaDriver browser = new OperaDriver();

				driver = browser;
			} catch (org.openqa.selenium.WebDriverException e) {
				System.out
						.println("The Opera web browser might not be installed or did not find it. " + e.getMessage());
			}
		}

		driver.manage().window().maximize();
		driver.get(Config.BaseURL);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"topBar\"]/div[1]/div[2]/button[1]")));
	}

	/**
	 * This method opens the url that is mentioned in the parameter of the method
	 */
	public static void OpenUrl(String Url) {
		driver.get(Url);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	/**
	 * This method clicks the <<Go Back button in the browser
	 */
	public static void BackButtonAfterLogin() {
		driver.navigate().back();
		LoginPageResponse loginPageResponse = new LoginPageResponse(driver);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"#app > div.container___hBWrc-scss.oblt > div.dialog__container___2uP4v-scss > div > div > div.dialog-close___2tE6n-scss > svg > path")));
			if (loginPageResponse.WelcomeDialogue.isDisplayed() == true) {

				loginPageResponse.WelcomeDialogueClose.click();

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("No such element was present" + e.getMessage());
		}
	}

	/**
	 * This method provides the string values username and password in the login
	 * dialogue window in the username and password field and clicks the login
	 * button.
	 */
	public static void ClickLogin(String username, String password) {
		GoToLoginPage();
		LoginWindow loginPage = new LoginWindow(driver);
		loginPage.UserName.clear();
		loginPage.UserName.sendKeys(username);
		loginPage.PassWord.clear();
		loginPage.PassWord.sendKeys(password);
		loginPage.LoginButton.click();
	}

	/**
	 * This method waits until all the elements are loaded into the login window
	 * loads this window
	 */
	public static void GoToLoginPage() {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"topBar\"]/div[1]/div[2]")));
		MainPage mainPage = new MainPage(driver);
		mainPage.LoginButton.click();
		SessionExpiredWindow sessionExpired = new SessionExpiredWindow(driver);
		try {
			if (sessionExpired.SessionExpiredWindow.isDisplayed() == true) {
				System.out.println("Found the element");
				sessionExpired.SessionExpiredWindowClose.click();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("No such element was present" + e.getMessage());
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

	}

	/**
	 * This method waits until all the elements are loaded into the webdriver after
	 * valid credentials
	 */
	public static void WaitElementsAfterValidLogin() {
		LoginPageResponse loginPageResponse = new LoginPageResponse(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"#app > div.container___hBWrc-scss.oblt > div.dialog__container___2uP4v-scss > div > div > div.dialog-close___2tE6n-scss > svg > path")));
			if (loginPageResponse.WelcomeDialogue.isDisplayed() == true) {

				loginPageResponse.WelcomeDialogueClose.click();

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("No such element was present" + e.getMessage());
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#topBar > div.page-content___3oo_o-scss.topBarContent___3cr_D-scss > div.main___2Aq79-scss > div > span.container___1dsKe-scss.undefined > a")));
	}

	/**
	 * This method returns the current instance of the webdriver that is currently
	 * executed in the browser
	 */
	public static WebDriver ReturnActiveDriverInstance() {
		return driver;
	}

	/**
	 * This method reads the text value in Login Dialogue Window from username field
	 * and returns the text that is present in this element in the form of string
	 */
	public static String ReadLoginResponseMessageUsernameField() {
		LoginWindow loginPage = new LoginWindow(driver);
		return loginPage.UserNameFieldError.getText();
	}

	/**
	 * This method reads the text value in Login Dialogue Window from password field
	 * and returns the text that is present in this element in the form of string
	 */
	public static String ReadLoginResponseMessagePasswordField() {
		LoginWindow loginPage = new LoginWindow(driver);
		return loginPage.PassWordFieldError.getText();
	}

	/**
	 * This method reads the color value for the Login Dialogue Window of username
	 * field which is then converted into hexvalue and returns the hexvalue is form
	 * of string
	 */
	public static String ReadLoginResponseMessageColorUsernameField() {
		LoginWindow loginPage = new LoginWindow(driver);
		String color = loginPage.UserNameFieldError.getCssValue("color");
		String color_hex[];
		if (color.contains("rgba")) {
			color = color.trim();
			color_hex = color.replace("rgba(", "").split(",");
			return String.format("#%02x%02x%02x", Integer.parseInt(color_hex[0].trim()),
					Integer.parseInt(color_hex[1].trim()), Integer.parseInt(color_hex[2].trim()));
		} else {
			color_hex = color.replace("rgb(", "").split(", ");
			color_hex[2] = color_hex[2].replace(")", "");
			return String.format("#%02x%02x%02x", Integer.parseInt(color_hex[0].trim()),
					Integer.parseInt(color_hex[1].trim()), Integer.parseInt(color_hex[2].trim()));
		}
	}

	/**
	 * This method reads the color value for the Login Dialogue Window of password
	 * field which is then converted into hexvalue and returns the hexvalue is form
	 * of string
	 */
	public static String ReadLoginResponseMessageColorPasswordField() {
		LoginWindow loginPage = new LoginWindow(driver);
		String color = loginPage.PassWordFieldError.getCssValue("color");
		String color_hex[];
		if (color.contains("rgba")) {
			color = color.trim();
			color_hex = color.replace("rgba(", "").split(",");
			return String.format("#%02x%02x%02x", Integer.parseInt(color_hex[0].trim()),
					Integer.parseInt(color_hex[1].trim()), Integer.parseInt(color_hex[2].trim()));
		} else {
			color_hex = color.replace("rgb(", "").split(", ");
			color_hex[2] = color_hex[2].replace(")", "");
			return String.format("#%02x%02x%02x", Integer.parseInt(color_hex[0].trim()),
					Integer.parseInt(color_hex[1].trim()), Integer.parseInt(color_hex[2].trim()));
		}
	}

	/**
	 * This method reads the font size for the given element which is then converted
	 * from px to pt and returns the value is form of integer
	 */
	public static int ReadFontSize(String fontsize) {
		return (int) (Float.parseFloat(fontsize.substring(0, 4)) / 1.33);
	}

	/**
	 * This method returns the current url that is running in the web browser in the
	 * form of string
	 */
	public static String ReturnCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * This method checks if there are any alert in the web browser and returns it
	 * in the form of boolean value
	 */
	public static boolean isAlertPresent() {
		boolean foundAlert = false;
		WebDriverWait wait = new WebDriverWait(driver, 0 /* timeout in seconds */);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		} catch (Exception eTO) {
			foundAlert = false;
		}
		return foundAlert;
	}

	/**
	 * This method navigates to main page if it is in any other pages/window.
	 */
	public static void ClickGoBack() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		LoginWindow loginPage = new LoginWindow(driver);
		try {
			if (loginPage.LoginDialogueWindow.isDisplayed() == true) {
				System.out.println("Found the element");
				loginPage.LoginWindowClose.click();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("No such element was present" + e.getMessage());
		}
		LoginPageResponse loginPageResponse = new LoginPageResponse(driver);
		try {
			if (loginPageResponse.LogOut.isDisplayed() == true) {
				System.out.println("Found the element");
				loginPageResponse.LogOut.click();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("No such element was present" + e.getMessage());
		}
		SessionExpiredWindow sessionExpired = new SessionExpiredWindow(driver);
		try {
			if (sessionExpired.SessionExpiredWindow.isDisplayed() == true) {
				System.out.println("Found the element");
				sessionExpired.SessionExpiredWindowClose.click();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("No such element was present" + e.getMessage());
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"topBar\"]/div[1]/div[2]")));
	}

	/**
	 * This method shut down the web driver instance or destroy the web driver
	 * instance(Close all the windows).
	 */
	public static void CloseDriver() {
		driver.quit();
	}
}
