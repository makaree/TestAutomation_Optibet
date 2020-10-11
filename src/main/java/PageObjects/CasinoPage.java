package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents the POM (Page Object Model) of the page Casino. It is a
 * design pattern, popularly used in test automation that creates Object
 * Repository for web UI elements.
 */
public class CasinoPage {
	public CasinoPage(WebDriver driver) {
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/// This element find the Searchbutton icon in the page Casino
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > main > div:nth-child(2) > div.container___1OLFj-scss > div.categoryNav___cKdn8-scss > div > div > div > svg")
	public WebElement SearchButton;

	/// This element find the CloseButton after pressing search in the page Casino
	@FindBy(css = "#casino-modal-container > div > div > div.dialog-close___2tE6n-scss.close___1BoDz-scss > svg > path")
	public WebElement CloseButton;

	/// This element find the Inputfield after pressing search in the page Casino
	@FindBy(xpath = "//*[@id=\"casino-modal-container\"]/div/div/input")
	public WebElement InputField;

	/// This element find the elements of ResultField after pressing search in the
	/// page Casino
	@FindBy(css = "#casino-modal-container > div > div > div.result___3X9jF-scss")
	public WebElement ResultField;

	/// This element find the FirstResult in ResultField after pressing search in
	/// the page Casino
	@FindBy(xpath = "//*[@id=\"casino-modal-container\"]/div/div/div[2]/div[1]")
	public WebElement FirstResult;

	/// This element find the Title of the lunched game
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > main > div > div > div.container___1jlW--scss > span")
	public WebElement LunchedGameTitle;

	/// This element find the Window of the lunched game
	@FindBy(css = "//#app > div.container___hBWrc-scss.oblt > main > div > div")
	public WebElement LunchedGameWindow;

	/// This element find the close button in the Window of the lunched game
	@FindBy(css = "#app > div.container___hBWrc-scss.oblt > main > div > div > div.container___1jlW--scss > div > div > svg > path")
	public WebElement LunchedGameCloseButton;

}
