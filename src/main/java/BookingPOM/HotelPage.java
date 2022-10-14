package BookingPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HotelPage {
	WebDriver drvr;
	public HotelPage(WebDriver driver) {
		this.drvr = driver;
	}
	
	public String getHotelName() {
		WebElement hotelNameElement = drvr.findElement(By.xpath("/html/body/div[3]/div/div[5]/div[1]/div[1]/div/div[2]/div[12]/div[1]/div/div/div/h2"));
		return hotelNameElement.getText();
	}
	
	public String getHotelReview() {
		WebElement hotelReviewElement = drvr.findElement(By.xpath("/html/body/div[3]/div/div[5]/div[1]/div[1]/div/div[2]/div[14]/div/div/div[1]/div[2]/div/div[1]/a/div/div/div/div/div[2]/div[1]"));
		return hotelReviewElement.getText();
	}
	
	public String getUrl() {
		return drvr.getCurrentUrl();
	}
}
