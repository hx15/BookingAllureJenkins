package BookingTest;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BookingPOM.HomePage;
import BookingPOM.HotelPage;
import BookingPOM.ListOfHotels;
import Core.OpenBrowsers;
import Core.ReadCsvFile;
import Core.WriteCsvFile;
import io.qameta.allure.Allure;
public class TestWithDP {
	WebDriver driver;
	ArrayList<String> outputHeaders = new ArrayList<String>();
	ArrayList<ArrayList<String>> outputData = new ArrayList<ArrayList<String>>();
	@BeforeSuite
	public void beforeSuite() throws InterruptedException {
		//driver = OpenBrowsers.openchromeWithOptions();
		driver = OpenBrowsers.openBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		outputHeaders.add("location");
		outputHeaders.add("check-in");
		outputHeaders.add("check-out");
		outputHeaders.add("hotel");
		outputHeaders.add("review");
		outputHeaders.add("url");
		driver.manage().window().maximize();
	}

	@DataProvider
	public static Object[][] getData() throws Exception{

		List<String[]> lines = ReadCsvFile.readAllLines("input.csv");
		lines.remove(0);
		Object[][] data = new Object[lines.size()][lines.get(0).length];
		int index = 0;
		for(String[] line : lines) {
			data[index] = line;
			index++;
		}
		return data;
	}
	@Test(dataProvider = "getData")
	public void collectHotelReview(String location, String checkin_date, String checkout_date) throws InterruptedException {		
		ArrayList<String> currOutput = new ArrayList<String>();
		currOutput.add(location);
		currOutput.add(checkin_date);
		currOutput.add(checkout_date);
		HomePage bookingHomePage = new HomePage(driver);
		bookingHomePage.selectStartEndDate(location, checkin_date, checkout_date);
		ListOfHotels listOfHotelsPage = new ListOfHotels(driver);
		Allure.addAttachment("HotelsList", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
		listOfHotelsPage.clickOnFirstResult();
	    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		Thread.sleep(5000);
		HotelPage hotelPage = new HotelPage(driver);
	    System.out.println(driver.getCurrentUrl());
		driver.switchTo().window(tabs.get(tabs.size()-1));
		currOutput.add(driver.getCurrentUrl());
		currOutput.add(hotelPage.getHotelReview());
		currOutput.add(hotelPage.getHotelName());
		outputData.add(currOutput);
		Allure.addAttachment("HotelReview", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://www.booking.com/index.html");
	}
	
	@AfterSuite
	public void afterSuite() {
		List<String[]> data = new ArrayList<String[]>();
		for(ArrayList<String> row: outputData) {
			String[] row_data = new String[row.size()];
			for(int i= 0;i<row.size();i++) {
				row_data[i] = row.get(i);
			}
			data.add(row_data);
		}
		String[] headers = new String[outputHeaders.size()];
		for(int i= 0;i<outputHeaders.size();i++) {
			headers[i] = outputHeaders.get(i);
		}
		WriteCsvFile.writeDataLineByLine("output.csv", data, headers);
	}
}