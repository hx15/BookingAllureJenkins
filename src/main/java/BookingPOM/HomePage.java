package BookingPOM;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.GenericLogEntry;

public class HomePage {
	WebDriver drvr;
	WebElement searchButtonElement;
	WebElement locationFieldElement;
	WebElement Table;
	WebElement checkInButtonElement;
	public HomePage(WebDriver drvr) {
		this.drvr = drvr;
		locationFieldElement = drvr.findElement(By.name("ss"));
		try {
			
			searchButtonElement = drvr.findElement(By.xpath("/html/body/div[1]/div[2]/div/form/div[1]/div[4]/div[2]/button/span[1]"));
			checkInButtonElement = drvr.findElement(By.xpath("/html/body/div[1]/div[2]/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div/div"));
		}
			catch (Exception e) {
				searchButtonElement = drvr.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div[1]/div[4]/button/span"));
				checkInButtonElement = drvr.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/form/div[1]/div[2]/div/div/button[1]"));
			}
		
	}
	
	public void clickOnCheckIn() {
		checkInButtonElement.click();
	}
	public void clickSearch() {
		searchButtonElement.click();
	}
	
	public void selectStartEndDate(String location, String start, String end) {
		locationFieldElement.sendKeys(location);
		JavascriptExecutor jsExecuter = (JavascriptExecutor) this.drvr;
		//jsExecuter.executeScript("arguments[0].click()", locationFieldElement);
		//jsExecuter.executeScript("arguments[0].click()", locationFieldElement);
		//WebElement firstOptionElement = drvr.findElement(By.xpath("//li[contains(@role, 'option') and contains(@data-i, '1')]"));
		//jsExecuter.executeScript("arguments[0].click()", firstOptionElement);
		//clickOnCheckIn();
		jsExecuter.executeScript("arguments[0].click()", checkInButtonElement);
		
		String[] startDayMonthYearStrings = start.trim().split("\\s+");
		String[] endDayMonthYearStrings = end.trim().split("\\s+");
		String neededStartDateNumberString = ConvertToNumberDate(startDayMonthYearStrings);
		String neededEndDateNumberString = ConvertToNumberDate(endDayMonthYearStrings);
		
		// CLICK ON CHECK IN DAY - FIND MONTH AND YEAR and CLICK ON REQUIRED DAY
		//getToTheRequiredTable(startDayMonthYearStrings [1], startDayMonthYearStrings [2]);
		String neededStartDayXPathString = String.format("//td[contains(@data-date, \'%s\')]", neededStartDateNumberString);
		WebElement startButtonElement = drvr.findElement(By.xpath(neededStartDayXPathString));
		jsExecuter.executeScript("arguments[0].click()", startButtonElement);
		
		// CLICK ON CHECK OUT DAY - FIND MONTH AND YEAR and CLICK ON REQUIRED DAY
		//getToTheRequiredTable(endDayMonthYearStrings [1], endDayMonthYearStrings [2]);
		System.out.println(endDayMonthYearStrings[0]);
		String neededEndDayXPathString = String.format("//td[contains(@data-date, \'%s\')]", neededEndDateNumberString);
		WebElement endButtonElement = drvr.findElement(By.xpath(neededEndDayXPathString));
		jsExecuter.executeScript("arguments[0].click()", endButtonElement);
		clickSearch();		
	}
	
	
	private void getToTheRequiredTable(String month, String year) {
		System.out.println(month);
		System.out.println(year);
	    WebElement monthAndYear = drvr.findElement(By.xpath("/html/body/div[1]/div[2]/div/form/div[1]/div[2]/div[2]/div/div/div[3]/div[1]/div"));
	    WebElement nextMonthButtonElement = drvr.findElement(By.xpath("/html/body/div[1]/div[2]/div/form/div[1]/div[2]/div[2]/div/div/div[2]"));
	    while(!monthAndYear.getText().toLowerCase().contains(month.toLowerCase()) && !monthAndYear.getText().toLowerCase().contains(year.toLowerCase())) {
	    	nextMonthButtonElement.click();
	    }
	 }
	
	
	private String ConvertToNumberDate(String[] date) {
		// input format for date: [<dayNumber> <monthText> <yearNumber>]
		// output format for date: <dayNumber>-<monthNumber>-<yearNumber>
		String monthNumberString = Constants.Configuration.monthToNumberHashMap.getOrDefault(date[1], null);
		String dateNumberString = String.join("-", date[2], monthNumberString, date[0]);
		System.out.println(dateNumberString);
		return dateNumberString;
	}
}
