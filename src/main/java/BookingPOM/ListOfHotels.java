package BookingPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListOfHotels {
	WebDriver drvr;
	WebElement firstHotelResultElement;
	public ListOfHotels(WebDriver drvr) {
		this.drvr = drvr;
		try {
			firstHotelResultElement = drvr.findElement(By.xpath("/html/body/div[3]/div/div[3]/div[1]/div[1]/div[4]/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div/div[1]"));
		}
		catch(Exception e) {
			firstHotelResultElement = drvr.findElement(By.xpath("/html/body/div[2]/div/div[3]/div[1]/div[1]/div[4]/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div/div[1]/div[1]/div/div[1]/div/h3/a/div[1]"));
			
		}
	}
	
	
	public void clickOnFirstResult() {
		System.out.println("Clicked On first hotel result!");
		firstHotelResultElement.click();
	}
}