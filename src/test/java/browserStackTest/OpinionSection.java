package browserStackTest;

import java.util.LinkedHashSet;
import java.util.List;
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
		List<WebElement> opinionLinks = driver.findElements(By.cssSelector("a[href*='/opinion/']"));
		Set<String> articleLinks = new LinkedHashSet<>();
		for (WebElement link : opinionLinks) {
				String url = link.getAttribute("href");
				if (url.contains("/opinion/") && articleLinks.size() < 5) {
						articleLinks.add(url);
				}
		}
	}

}
