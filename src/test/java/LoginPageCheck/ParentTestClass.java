package LoginPageCheck;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * This test class contains methods that runs before and after test suite
 */
public class ParentTestClass {
	String command = System.getProperty("os.name").contains("Windows") ? "cmd.exe /c " : "/bin/bash ";

	/**
	 * This method runs shortly before the first test method
	 */
	@BeforeSuite(groups = { "stable", "functionality", "userinterface", "security" })
	public void initialize() throws Throwable {

		try {
			// start the docker grid
			System.out.println("start the docker grid");
			Runtime.getRuntime().exec(command + "start " + System.getProperty("user.dir") + "\\dockerStart.bat");
			Thread.sleep(60000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method run shortly after the last test method
	 */
	@AfterSuite(groups = { "stable", "functionality", "userinterface", "security" })
	public void close() {
		try {
			// start the docker grid
			Runtime.getRuntime().exec(command + "start " + System.getProperty("user.dir") + "\\dockerStop.bat");
			Thread.sleep(20000);
			Runtime.getRuntime().exec("taskkill /f /im cmd.exe"); // closes command prompt
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
