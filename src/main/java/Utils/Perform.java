package Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.CasinoPage;
import PageObjects.LoginPageResponse;
import PageObjects.LoginWindow;
import PageObjects.MainPage;
import PageObjects.SessionExpiredWindow;

/**
 * This class performs various tasks for the test methods. It is a helper class
 * used to perform various tasks
 */
public class Perform {
	// private static RemoteWebDriver driver;

	/**
	 * This method creates an instance of webdriver mentioned in browsername in
	 * parameters
	 */
	public static RemoteWebDriver InitializeDriver(String browsername) {
		RemoteWebDriver driver = null;
		browsername = browsername != null ? browsername : "Chrome";
		if (browsername.equals("Chrome")) {
//			try {
//				System.setProperty("webdriver.chrome.driver",
//						System.getProperty("user.dir") + "/Drivers/ChromeDriver/chromedriver.exe");
//				driver = new ChromeDriver();
//			} catch (org.openqa.selenium.WebDriverException e) {
//				System.out
//						.println("The Chrome web browser might not be installed or did not find it. " + e.getMessage());
//			}
			try {
				DesiredCapabilities dc = DesiredCapabilities.chrome();
				URL url = new URL("http://localhost:4444/wd/hub");
				driver = new RemoteWebDriver(url, dc);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (browsername.equals("InternetExplorer")) {
//			try {
//				System.setProperty("webdriver.ie.driver",
//						System.getProperty("user.dir") + "/Drivers/IEDriver/IEDriverServer.exe");
//				driver = new InternetExplorerDriver();
//			} catch (org.openqa.selenium.WebDriverException e) {
//				System.out.println("The InternetExplorer web browser might not be installed or did not find it. "
//						+ e.getMessage());
//			}
			try {
				DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
				URL url = new URL("http://localhost:4444/wd/hub");
				driver = new RemoteWebDriver(url, dc);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (browsername.equals("FireFox")) {
//			try {
//				System.setProperty("webdriver.gecko.driver",
//						System.getProperty("user.dir") + "/Drivers/FireFoxDriver/geckodriver.exe");
//				driver = new FirefoxDriver();
//			} catch (org.openqa.selenium.WebDriverException e) {
//				System.out.println(
//						"The FireFox web browser might not be installed or did not find it. " + e.getMessage());
//			}
			try {
				DesiredCapabilities dc = DesiredCapabilities.firefox();
				URL url = new URL("http://localhost:4444/wd/hub");
				driver = new RemoteWebDriver(url, dc);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (browsername.equals("Opera")) {
//			try {
//				System.setProperty("webdriver.opera.driver",
//						System.getProperty("user.dir") + "/Drivers/OperaDriver/operadriver.exe");
//				OperaDriver browser = new OperaDriver();
//
//				driver = browser;
//			} catch (org.openqa.selenium.WebDriverException e) {
//				System.out
//						.println("The Opera web browser might not be installed or did not find it. " + e.getMessage());
//			}
			try {
				DesiredCapabilities dc = DesiredCapabilities.operaBlink();
				URL url = new URL("http://localhost:4444/wd/hub");
				driver = new RemoteWebDriver(url, dc);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		driver.manage().window().maximize();
		driver.get(Config.BaseURL);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"topBar\"]/div[1]/div[2]/button[1]")));
		return driver;
	}

	/**
	 * This method opens the url that is mentioned in the parameter of the method
	 */
	public static void OpenUrl(RemoteWebDriver driver, String Url) {
		driver.get(Url);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	/**
	 * This method opens the url with IsoftBet category in the query and returns all
	 * the games names present in this category
	 */
	public static String[] FindGameNamesInIsofbetCategory(RemoteWebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String scriptsection = "return document.querySelectorAll('#app > div.container___hBWrc-scss.oblt > main > div:nth-child(2) > div.page-content___3oo_o-scss.page-content_wide___1wN6X-scss.container___2stOa-scss > div')[0].";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.navigate().to(Config.BaseURL + "casino/slots?filter=iSoftBet");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#app > div.container___hBWrc-scss.oblt > main > div:nth-child(2) > div.page-content___3oo_o-scss.page-content_wide___1wN6X-scss.container___2stOa-scss > div")));
		String counter = js.executeScript(scriptsection + "childElementCount;").toString();
		int count = Integer.parseInt(counter);
		String[] value = new String[count];
		for (int i = 0; i < count; i++) {
			value[i] = js.executeScript(scriptsection + "childNodes[" + i + "].innerText;").toString();
			value[i] = value[i].substring(9, value[i].length());
		}
		MainPage mainPage = new MainPage(driver);
		mainPage.OptibetIcon.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#app > div.container___hBWrc-scss.oblt > main > div > div:nth-child(2) > div > div > div > div > div > div")));
		return value;
	}

	/**
	 * This method navigates to search button in Casino
	 */
	public static void GoToSearchButtonInCasino(RemoteWebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		CasinoPage casinoPage = new CasinoPage(driver);
		MainPage mainPage = new MainPage(driver);
		mainPage.Casino.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#app > div.container___hBWrc-scss.oblt > main > div:nth-child(2) > div.container___1OLFj-scss > div.categoryNav___cKdn8-scss > div > div > div > svg")));
		casinoPage.SearchButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"casino-modal-container\"]/div")));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"casino-modal-container\"]/div/div/input")));
	}

	/**
	 * This method finds the game passed in method to search button in Casino Page,
	 * lunches it and return the title of lunched game
	 */
	public static String FindAndLunchGame(RemoteWebDriver driver, String gameName) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		CasinoPage casinoPage = new CasinoPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		casinoPage.InputField.click();
		casinoPage.InputField.sendKeys(gameName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("#casino-modal-container > div > div > div.result___3X9jF-scss")));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"casino-modal-container\"]/div/div/div[2]/div[1]")));
		System.out.println("The game to be loaded is:" + gameName);
		String searchresultnos_str = js.executeScript(
				"return document.querySelectorAll('#casino-modal-container > div > div > div.result___3X9jF-scss')[0].childElementCount;")
				.toString();
		int searchresultnos_int = Integer.parseInt(searchresultnos_str);
		int k = 0;
		String[] findgameName = new String[searchresultnos_int];
		String modifygameName = gameName;
		for (int j = 0; j < searchresultnos_int; j++) {
			findgameName[j] = js.executeScript(
					"return document.querySelectorAll('#casino-modal-container > div > div > div.result___3X9jF-scss')[0].childNodes["
							+ j + "].children[0].pathname;")
					.toString();
			findgameName[j] = findgameName[j].substring(17, findgameName[j].length());
			findgameName[j] = findgameName[j].replace("-", "");

			modifygameName = modifygameName.toLowerCase();
			modifygameName = modifygameName.replace(" ", "");
			modifygameName = modifygameName.replace("'", "");
			if (findgameName[j].equals(modifygameName)) {
				k = j + 1;
			}
		}
		driver.findElement(By.xpath("//*[@id=\"casino-modal-container\"]/div/div/div[2]/div[" + k + "]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#app > div.container___hBWrc-scss.oblt > main > div > div > div.container___1jlW--scss > div > div > svg > path")));
		String getgameNamefromLuncher = casinoPage.LunchedGameTitle.getText();

//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		casinoPage.LunchedGameCloseButton.click();
		return getgameNamefromLuncher;
	}

	/**
	 * This method clicks the <<Go Back button in the browser
	 */
	public static void BackButtonAfterLogin(RemoteWebDriver driver) {
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
			// e.printStackTrace();
			System.out.println("This catch is optional for checking WelcomeDialogueClose");
		}
	}

	/**
	 * This method provides the string values username and password in the login
	 * dialogue window in the username and password field and clicks the login
	 * button.
	 */
	public static void ClickLogin(RemoteWebDriver driver, String username, String password) {
		GoToLoginPage(driver);
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
	public static void GoToLoginPage(RemoteWebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"topBar\"]/div[1]/div[2]")));
		MainPage mainPage = new MainPage(driver);
		mainPage.LoginButton.click();
		SessionExpiredWindow sessionExpired = new SessionExpiredWindow(driver);
		try {
			if (sessionExpired.SessionExpiredWindow.isDisplayed() == true) {
				sessionExpired.SessionExpiredWindowClose.click();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			// e.printStackTrace();
			System.out.println("This catch is optional for checking SessionExpiredWindow");
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

	}

	/**
	 * This method waits until all the elements are loaded into the webdriver after
	 * valid credentials
	 */
	public static void WaitElementsAfterValidLogin(RemoteWebDriver driver) {
		LoginPageResponse loginPageResponse = new LoginPageResponse(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"#app > div.container___hBWrc-scss.oblt > div.dialog__container___2uP4v-scss > div > div > div.dialog-close___2tE6n-scss > svg > path")));
			if (loginPageResponse.WelcomeDialogue.isDisplayed() == true) {

				loginPageResponse.WelcomeDialogueClose.click();

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			// e.printStackTrace();
			System.out.println("This catch is optional for checking WelcomeDialogueClose");
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#topBar > div.page-content___3oo_o-scss.topBarContent___3cr_D-scss > div.main___2Aq79-scss > div > span.container___1dsKe-scss.undefined > a")));
	}

	/**
	 * This method reads the text value in Login Dialogue Window from username field
	 * and returns the text that is present in this element in the form of string
	 */
	public static String ReadLoginResponseMessageUsernameField(RemoteWebDriver driver) {
		LoginWindow loginPage = new LoginWindow(driver);
		return loginPage.UserNameFieldError.getText();
	}

	/**
	 * This method reads the text value in Login Dialogue Window from password field
	 * and returns the text that is present in this element in the form of string
	 */
	public static String ReadLoginResponseMessagePasswordField(RemoteWebDriver driver) {
		LoginWindow loginPage = new LoginWindow(driver);
		return loginPage.PassWordFieldError.getText();
	}

	/**
	 * This method reads the color value for the Login Dialogue Window of username
	 * field which is then converted into hexvalue and returns the hexvalue is form
	 * of string
	 */
	public static String ReadLoginResponseMessageColorUsernameField(RemoteWebDriver driver) {
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
	public static String ReadLoginResponseMessageColorPasswordField(RemoteWebDriver driver) {
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
	public static String ReturnCurrentUrl(RemoteWebDriver driver) {
		return driver.getCurrentUrl();
	}

	/**
	 * This method checks if there are any alert in the web browser and returns it
	 * in the form of boolean value
	 */
	public static boolean isAlertPresent(RemoteWebDriver driver) {
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
	public static void ClickGoBack(RemoteWebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		LoginWindow loginPage = new LoginWindow(driver);
		try {
			if (loginPage.LoginDialogueWindow.isDisplayed() == true) {
				loginPage.LoginWindowClose.click();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			// e.printStackTrace();
			System.out.println("This catch is optional for checking login dialogue");
		}
		LoginPageResponse loginPageResponse = new LoginPageResponse(driver);
		try {
			if (loginPageResponse.LogOut.isDisplayed() == true) {
				loginPageResponse.LogOut.click();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			// e.printStackTrace();
			System.out.println("This catch is optional for logout");

		}
		SessionExpiredWindow sessionExpired = new SessionExpiredWindow(driver);
		try {
			if (sessionExpired.SessionExpiredWindow.isDisplayed() == true) {
				sessionExpired.SessionExpiredWindowClose.click();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			// e.printStackTrace();
			System.out.println("This catch is optional for checking SessionExpiredWindow");
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"topBar\"]/div[1]/div[2]")));
	}

	/**
	 * This method shut down the web driver instance or destroy the web driver
	 * instance(Close all the windows).
	 */
	public static void CloseDriver(RemoteWebDriver driver) {
		driver.quit();
	}
}
