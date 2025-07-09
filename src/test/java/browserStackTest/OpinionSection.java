package browserStackTest;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class OpinionSection extends BaseTest{

	@Test
	public void testOpinionArticle(){
		WebDriver driver=getDriver();
		driver.get(baseURL);
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='edition_head' and @data-edition='el-pais']")).isDisplayed());
		List<WebElement> opinionLinks = driver.findElements(By.xpath("//*[@data-dtm-region='portada_apertura']//a[contains(@href,'https://elpais.com/opinion/')]"));
		Set<String> articleLinks = new LinkedHashSet<>();
		for (WebElement link : opinionLinks) {
				String url = link.getAttribute("href");
				if(url.isEmpty() || url.equalsIgnoreCase("https://elpais.com/opinion/")){
					continue;
				}
				if (url.contains("/opinion/") && articleLinks.size() < 5 ) {
						articleLinks.add(url);
						driver.navigate().to(url);
						printArticleTitleAndContent(driver);
						driver.navigate().back();
				}
		}
	}

	public void printArticleTitleAndContent(WebDriver driver){
		WebElement titleElement=driver.findElement(By.xpath("//*[@id='main-content']//h1"));
		String title=titleElement.getText();
		System.out.println(title);
		WebElement bodyElement=driver.findElement(By.xpath("//*[@data-dtm-region='articulo_cuerpo']"));
		String body=bodyElement.getText();
		System.out.println(body);
	}

}
