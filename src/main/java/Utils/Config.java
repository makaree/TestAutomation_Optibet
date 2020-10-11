package Utils;

/**
 * This class contains various values for credentials, and text messages and
 * color
 */
public class Config {
	public static int ElementsWaitingTimeout = 5;
	public static String BaseURL = "https://www.optibet.lv/en/";

	/**
	 * This class contains various values for username and password
	 */
	public static class Credentials {
		/**
		 * This class contains valid values for username and password
		 */
		public static class Valid {
			public static String Username = "manz.karki@gmail.com";
			public static String Password = "Manish12345";
		}

		/**
		 * This class contains invalid values for username and password
		 */
		public static class Invalid {
			/**
			 * This class contains invalid values for username
			 */
			public static class Username {
				public static String ShortCharacters = "ADMIN ";
				public static String longCharacters = "adminabcdabcdaadsfasdfasdfasdfasdfasdfasdasdfasdf";
				public static String OnlyLetters = "ADMINadmin";
				public static String OnlyNumbers = "123456789";
				public static String OnlySpecialSymbols = "$#@%)(*$%#%?><";
				public static String NoSpecialSymbol = "12345 admin ads 34 ADMIN";
				public static String Allmix = "admin 12345 $#@";
			}

			/**
			 * This class contains invalid values for password
			 */
			public static class Password {
				public static String ShortCharacters = "ADMIN ";
				public static String longCharacters = "adminabcdsdfgsfdgsdfgsdfgsdfgsdfgsdfgsdfgdfsgabcda";
				public static String OnlyLetters = "ADMINadmin";
				public static String OnlyNumbers = "123456789";
				public static String OnlySpecialSymbols = "$#@%)(*$%#%?><";
				public static String NoSpecialSymbol = "12345 admin ads 34 ADMIN";
				public static String Allmix = "admin 12345 $#@";
			}
		}
	}

	/**
	 * This class contains valid values for Response in login dialogue window as
	 * TestMessages
	 */
	public static class TestMessages {
		public static String PasswordEmpty = "Password is required";
		public static String InvalidCredentials = "The username or password is incorrect";
		public static String LessThanSixChar = "Username can not be less than 6 characters";
		public static String MoreThanThirtytwoChar = "Username can not be more than 32 characters";
		public static String InvalidEmail = "Please enter a valid email";
		public static String InvalidCharacter = "Use Latin letters, numbers, and underscores only, with no spaces.";
	}

	/**
	 * This class contains valid values for color of the text
	 */
	public static class TextColor {
		public static String redcolor = "#dc2728";
	}

	/**
	 * This class contains valid values for font-family of various texts
	 */
	public static class Pagefont {
		// public static String font = "";
	}

	/**
	 * This class contains valid values for font-size of the text
	 */
	public static class Pagefontsize {
		// public static int titlefontsize = ;
	}

}
