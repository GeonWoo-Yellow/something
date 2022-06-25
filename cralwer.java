package my.project.crawlertest;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootApplication
public class CrawlerTestApplication {
	public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static String WEB_DRIVER_PATH = "chromedriver.exe";

	public static void main(String[] args) {
		SpringApplication.run(CrawlerTestApplication.class, args).getBean(CrawlerTestApplication.class).crawler();
	}

	public void crawler() {

		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		//세션 시작
		ChromeOptions options = new ChromeOptions();

		//페이지가 로드될 때까지 대기
		//Normal: 로드 이벤트 실행이 반환 될 때 까지 기다린다.
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//		options.setHeadless(true);
		WebDriver driver = new ChromeDriver(options);
//		options.re
//		System.out.println(driver.getPageSource());

		try{
//			driver.get("http://www.nktech.net/nk_tech/journal/journal_more.jsp");

			driver.get("https://heodolf.tistory.com/13");
			System.out.println(driver.getPageSource());



			driver.findElement(By.cssSelector("#scontents > div > div.dev_contents > div.list_ty1.min.mgb40 > ul > li:nth-child(2) > a")).click();

			WebElement publish = driver.findElement(By.className("pub_body"));
			List<WebElement> rowBoxList = publish.findElements(By.className("row_box"));

			for(WebElement rowBox: rowBoxList) {
				String yearOfPublish = rowBox.findElement(By.className("box_tit")).getText();
				List<WebElement> periodicList = rowBox.findElements(By.className("action_box"));
				System.out.println("year:" + yearOfPublish);
				for(WebElement periodic: periodicList) {
//					System.out.println(periodic.getAccessibleName());

//				 	periodic.toString()

					String nth = periodic.findElement(By.tagName("span")).getText();
					System.out.println("\t" + nth + " period." + periodic);

					periodic.click();


					System.out.println(driver.getPageSource());

					try {
						Thread.sleep(3000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}


					WebElement ty = driver.findElement(By.cssSelector("#scontents > div > div.dev_contents > div.list_ty1"));
					List<WebElement> list_box = ty.findElements(By.className("list_box"));

					for(WebElement box: list_box) {
						System.out.println(box.findElement(By.className("tit")).getText());
					}

					//driver.navigate().back();


//					List<WebElement> contents = periodic.findElements(By.className("list_box"));

//					for(WebElement content:  contents) {
//						String title = content.findElement(By.cssSelector("#scontents > div > div.dev_contents > div.list_ty1 > ul > li:nth-child(1) > a > div > strong")).getText();
//						String summary = content.findElement(By.cssSelector("#scontents > div > div.dev_contents > div.list_ty1 > ul > li:nth-child(1) > a > span")).getText();
//						String introduction = content.findElement(By.cssSelector("#scontents > div > div.dev_contents > div.list_ty1 > ul > li:nth-child(1) > a > p")).getText();
//
//						System.out.println("title:" + title + ", summary:" + summary + ", intro:" + introduction);
//					}
				}
				//break;
			}

		}finally {
//			driver.close();
		}
	}
}
