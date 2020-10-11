package LoginPageCheck;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Utils.Perform;

/**
 * This test class contains methods that runs before and after test suite
 */
public class ParentTestClass {

	/**
	 * This method runs shortly before the first test method that belongs to any of
	 * these groups is invoked. An instance of webdriver mentioned in browsername in
	 * parameters will get created through this method
	 */
	@BeforeSuite(groups = { "stable", "functionality", "userinterface", "security" })
	@Parameters({ "browser" })
	public void initialize(@Optional String browsername) {
		Perform.InitializeDriver(browsername);
	}

	/**
	 * This method run shortly after the last test method that belongs to any of
	 * these groups is invoked. It shut down the web driver instance or destroy the
	 * web driver instance(Close all the windows).
	 */
	@AfterSuite(groups = { "stable", "functionality", "userinterface", "security" })
	public static void close() {
		Perform.CloseDriver();
	}
}
