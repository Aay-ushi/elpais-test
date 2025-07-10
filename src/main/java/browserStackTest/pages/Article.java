package browserStackTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.function.Function;

public class Article {
    private WebDriver driver;
		private FluentWait<WebDriver> wait; 

    public Article(WebDriver driver){
        this.driver = driver;
				this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))  // Maximum wait time
                .pollingEvery(Duration.ofSeconds(2))  // Polling interval
                .ignoring(Exception.class); 
    }

    public WebElement getTitleElement() {
        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//*[@id='main-content']//h1"));
            }
        });
    }

    public WebElement getBodyElement() {
        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                // Find and return the body element
                return driver.findElement(By.xpath("(//*[@data-dtm-region='articulo_cuerpo']//p)[1]"));
            }
        });
			}
}
