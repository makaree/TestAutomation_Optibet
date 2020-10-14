package LoginPageCheck;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

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
	@BeforeSuite(groups = { "stable", "functionality", "userinterface", "security", "searchandlunch" })
	public void initialize() throws Throwable {

		try {
			// start the docker grid
			System.out.println("start the docker grid");
			Runtime.getRuntime().exec(command + "start " + System.getProperty("user.dir") + "\\dockerStart.bat");
			Thread.sleep(60000);
			if (checkconnection() == false) {
				for (int i = 0; i < 10; i++) {
					System.out.println("waiting for software to load is for" + i + "min");
					Thread.sleep(60000);
					if (checkconnection() == true) {
						break;
					}
				}
				Thread.sleep(60000);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method run shortly after the last test method
	 */
	@AfterSuite(groups = { "stable", "functionality", "userinterface", "security", "searchandlunch" })
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

	public static boolean checkconnection() {
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress("0.0.0.0", 4444), 1800);
			System.out.println(true);
			return true;

		} catch (IOException e) {
			System.out.println(false);
			return false; // Either timeout or unreachable or failed DNS lookup.
		}
	}
}
