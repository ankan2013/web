package testUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Element {

    public static List<WebElement> getLastRowTDs(WebDriver driver){
        List<WebElement> rows = driver.findElements(By.tagName("tr"));
        if (rows == null || rows.size() == 0){
            return null;
        }
        WebElement lastRow = rows.get(rows.size() - 1);
        return lastRow.findElements(By.tagName("td"));
    }
}
