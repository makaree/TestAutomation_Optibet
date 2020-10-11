package SearchIsoftBetCheck;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import LoginPageCheck.ParentTestClass;
import Utils.Config;
import Utils.Perform;

public class SearchandLunchTest extends ParentTestClass {
	/**
	 * This method is executed at the beginning of every test case which makes
	 * authentication to the login page with valid credentials
	 */
	@BeforeClass(groups = { "stable" })
	public void SelectisoftBetCategory() {
		Perform.ClickLogin(Config.Credentials.Valid.Username, Config.Credentials.Valid.Password);
		Perform.WaitElementsAfterValidLogin();
	}

	@Test(dataProvider = "IsoftGameNames", groups = {
			"stable" }, description = "This test checks the login function with valid username and valid password. It then verifies the valid login response.")
	public void SearchAndLunchIsofbetGames(String GameNames) {

		System.out.println(GameNames);

	}

	/**
	 * This method provides data for SearchAndLunchIsofbetGames test
	 */
	@DataProvider(name = "IsoftGameNames")
	public Object[] getIsoftGameNames() {
		String[] gameNames = Perform.FindGameNamesInIsofbetCategory();
		return gameNames;
	}

	/**
	 * This method is executed at the end of every test case which navigates to
	 * login page if it is in other page
	 */
	@AfterClass(groups = { "stable" })
	public void GoBackToLoginPage() {
		Perform.ClickGoBack();
	}
}
