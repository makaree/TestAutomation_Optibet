package SearchIsoftBetCheck;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import LoginPageCheck.ParentTestClass;
import Utils.Config;
import Utils.Perform;

public class SearchandLunchTest extends ParentTestClass {
	RemoteWebDriver driver;

	/**
	 * This method is executed at the beginning of every test case which makes
	 * authentication to the login page with valid credentials
	 */
	@BeforeClass(groups = { "stable" })
	@Parameters({ "browser" })
	public void SelectisoftBetCategory(@Optional String browsername) {
		driver = Perform.InitializeDriver(browsername);
		Perform.ClickLogin(driver, Config.Credentials.Valid.Username, Config.Credentials.Valid.Password);
		Perform.WaitElementsAfterValidLogin(driver);
	}

	@Test(dataProvider = "IsoftGameNames", groups = {
			"stable" }, description = "This test checks the login function with valid username and valid password. It then verifies the valid login response.")
	public void SearchAndLunchIsofbetGames(String GameNames) {
		Perform.GoToSearchButtonInCasino(driver);
		String getgameNamefromLuncher = Perform.FindAndLunchGame(driver, GameNames);
		AssertJUnit.assertEquals(GameNames, getgameNamefromLuncher);
	}

	/**
	 * This method provides data for SearchAndLunchIsofbetGames test
	 */
	@DataProvider(name = "IsoftGameNames")
	public Object[] getIsoftGameNames() {
		String[] gameNames = Perform.FindGameNamesInIsofbetCategory(driver);
		return gameNames;
	}

	/**
	 * This method is executed at the end of every test case which navigates to
	 * login page if it is in other page
	 */
	@AfterClass(groups = { "stable" })
	public void GoBackToLoginPage() {
		Perform.ClickGoBack(driver);
		Perform.CloseDriver(driver);
	}
}
