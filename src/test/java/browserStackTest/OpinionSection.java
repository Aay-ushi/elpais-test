package browserStackTest;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import browserStackTest.pages.Article;

import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;


public class OpinionSection extends BaseTest{


	@Test
	public void testOpinionArticle(){
		WebDriver driver=getDriver();
		driver.get(baseURL+"/opinion");
		driver.manage().window().maximize();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='edition_head' and @data-edition='el-pais']")).isDisplayed());

		List<WebElement> articles = driver.findElements(By.xpath("//article//header//h2//a[@href]"));
		Set<String> articleLinks = new LinkedHashSet<>();
		for (WebElement article : articles) {
			try{
			String url=article.getAttribute("href");
			if(articleLinks.contains(url)){
				continue;
			}
			driver.navigate().to(url);
			printArticleTitleAndContent(driver);
			driver.navigate().back();
			articleLinks.add(url);
			}
			catch(StaleElementReferenceException e){
				continue;
			}

			
			if(articleLinks.size()==5){
				break;
			}
		}
	}

	public void printArticleTitleAndContent(WebDriver driver){
		
		Article article=new Article(driver);
		String title=article.getTitleElement().getText();
		System.out.println("Title: " + title);
		String body=article.getBodyElement().getText();
		System.out.println("Body: "+ body);
	}

}
