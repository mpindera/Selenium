import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String urlAddress;
        String title;
        StringBuilder contentOfTitle = new StringBuilder("");
        List<WebElement> webElementsList;

        HashMap<String, String> mapOfBox = new HashMap<>();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //opening page
        driver.get("https://en.wikipedia.org/wiki/Selenium_(software)");

        /*
        saving to variable (urlAddress) url address.
        Searched by: tag of node //img and attribute @alt.
        Got by attribute "src"
        */
        urlAddress = driver.findElement(By.xpath("//img[@alt='Selenium logo.svg']")).getAttribute("src");

        /*
        saving to variable (title) title.
        Searched by: tag of node //span , attribute @class and @id.
        */
        title = driver.findElement(By.xpath("//span[@class='mw-headline' and @id='History']")).getText();

        /*
        Creating a list from web element
        between paragraph (p), header (h2), attribute @id and header (h2), attribute @id.
        */
        webElementsList = driver.findElements(By.xpath("//p[preceding-sibling::h2[./span[@id='History']]" +
                " and following-sibling::h2[./span[@id='Components']]]"));

        //for all element in list (webElementsList) add to StringBuilder
        for (WebElement element : webElementsList) {
            contentOfTitle.append(element.getText()).append("\n");
        }

        //Add all parameters and values to HashMap (mapOfBox)
        List<WebElement> parametersLists = driver.findElements(By.xpath("//th[@scope='row']"));
        for (int i = 0; i < parametersLists.size() - 1; i++) {
            List<WebElement> parametersList = driver.findElements(By.xpath("//th[@class='infobox-label']"));
            List<WebElement> valuesList = driver.findElements(By.xpath("//td[@class='infobox-data']"));
            mapOfBox.put(parametersList.get(i).getText(), valuesList.get(i).getText());
        }

        //Wait 25 milliseconds.
        Thread.sleep(25);

        // Close the driver.
        driver.quit();

        // Object of Class WikiPage.
        WikiPage wikiPage = new WikiPage(urlAddress, title, contentOfTitle);
        System.out.println(wikiPage);

        //Print HashMap (mapOfBox).
        for (Map.Entry<String, String> mm : mapOfBox.entrySet()) {
            System.out.println("[ " + mm.getKey() + " ]" + " -> " + "{ " + mm.getValue() + " }");
        }
    }
}
