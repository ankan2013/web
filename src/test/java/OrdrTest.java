import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import testUtils.Driver;
import testUtils.Element;

import java.util.List;

@Test
public class OrdrTest {
    public void CRUDOrdrTest() {
        WebDriver driver = Driver.getDriver();
        driver.get(Driver.getAddress());

        driver.findElement(By.linkText("Клиенты")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Список клиентов"));
        driver.findElement(By.linkText("Добавить клиента")).click();
        wait.until(ExpectedConditions.titleIs("Добавление клиента"));
        driver.findElement(By.id("name")).sendKeys("Иван Иванов");
        driver.findElement(By.id("email")).sendKeys("address@domain.com");
        driver.findElement(By.id("phone")).sendKeys("+700000000");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Список клиентов"));

        driver.findElement(By.linkText("На главную")).click();
        wait.until(ExpectedConditions.titleIs("Главная"));

        driver.findElement(By.linkText("Фильмы")).click();
        wait.until(ExpectedConditions.titleIs("Список фильмов"));
        driver.findElement(By.linkText("Добавить фильм")).click();
        wait.until(ExpectedConditions.titleIs("Добавление фильма"));
        driver.findElement(By.id("name")).sendKeys("Фильм");
        driver.findElement(By.id("info")).sendKeys("Paramount, 1999");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Список фильмов"));

        driver.findElement(By.linkText("На главную")).click();
        wait.until(ExpectedConditions.titleIs("Главная"));

        driver.findElement(By.linkText("Носители")).click();
        wait.until(ExpectedConditions.titleIs("Список носителей"));
        driver.findElement(By.linkText("Добавить носитель")).click();
        wait.until(ExpectedConditions.titleIs("Добавление носителя"));
        driver.findElement(By.id("name")).sendKeys("Диск 1");
        driver.findElement(By.id("type")).sendKeys("CD");
        driver.findElement(By.id("price")).clear();
        driver.findElement(By.id("price")).sendKeys("100");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Список носителей"));

        List<WebElement> tds = Element.getLastRowTDs(driver);
        tds.get(5).click();
        driver.findElement(By.linkText("Добавить фильм")).click();
        wait.until(ExpectedConditions.titleIs("Добавление фильма"));
        Select select = new Select(driver.findElement(By.id("filmId")));
        select.selectByVisibleText("Фильм Paramount, 1999");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Список фильмов"));

        driver.findElement(By.linkText("На главную")).click();
        wait.until(ExpectedConditions.titleIs("Главная"));

        driver.findElement(By.linkText("Новый заказ")).click();
        wait.until(ExpectedConditions.titleIs("Добавление заказа"));
        select = new Select(driver.findElement(By.id("clientId")));
        select.selectByVisibleText("Иван Иванов address@domain.com");
        select = new Select(driver.findElement(By.id("diskId")));
        select.selectByVisibleText("Диск 1 CD");
        driver.findElement(By.id("requestTime")).sendKeys("2020-03-04");
        driver.findElement(By.id("returnTime")).sendKeys("2020-04-04");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Главная"));

        tds = Element.getLastRowTDs(driver);
        if (tds != null && tds.size() > 0) {
            assert tds.get(0).getText().contains("Иван Иванов address@domain.com");
            assert tds.get(1).getText().contains("Диск 1 CD");
            assert tds.get(2).getText().contains("Фильм Paramount, 1999");
            assert tds.get(3).getText().contains("100");
            assert tds.get(4).getText().contains("2020-03-04");
            assert tds.get(5).getText().contains("2020-04-04");
            assert tds.get(6).getText().contains("false");
        }

        tds.get(7).click();
        wait.until(ExpectedConditions.titleIs("Изменение заказа"));
        driver.findElement(By.id("requestTime")).clear();
        driver.findElement(By.id("returnTime")).clear();
        driver.findElement(By.id("requestTime")).sendKeys("2020-03-05");
        driver.findElement(By.id("returnTime")).sendKeys("2020-04-05");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Главная"));

        tds = Element.getLastRowTDs(driver);
        if (tds != null && tds.size() > 0) {
            assert tds.get(0).getText().contains("Иван Иванов address@domain.com");
            assert tds.get(1).getText().contains("Диск 1 CD");
            assert tds.get(2).getText().contains("Фильм Paramount, 1999");
            assert tds.get(3).getText().contains("100");
            assert tds.get(4).getText().contains("2020-03-05");
            assert tds.get(5).getText().contains("2020-04-05");
            assert tds.get(6).getText().contains("false");
        }

        tds.get(9).click();
        wait.until(ExpectedConditions.titleIs("Главная"));
        tds = Element.getLastRowTDs(driver);
        assert tds.get(6).getText().contains("true");

        tds.get(8).click();
        wait.until(ExpectedConditions.titleIs("Главная"));
        tds = Element.getLastRowTDs(driver);
        if (tds != null && tds.size() > 0) {
            assert !(tds.get(0).getText().contains("Иван Иванов address@domain.com") &&
                    tds.get(1).getText().contains("Диск 1 CD") &&
                    tds.get(2).getText().contains("Фильм Paramount, 1999") &&
                    tds.get(3).getText().contains("100") &&
                    tds.get(4).getText().contains("2020-03-05") &&
                    tds.get(5).getText().contains("2020-04-05") &&
                    tds.get(6).getText().contains("true"));
        }

        driver.findElement(By.linkText("Носители")).click();
        wait.until(ExpectedConditions.titleIs("Список носителей"));
        tds = Element.getLastRowTDs(driver);
        tds.get(4).click();
        wait.until(ExpectedConditions.titleIs("Список носителей"));
        driver.findElement(By.linkText("На главную")).click();
        wait.until(ExpectedConditions.titleIs("Главная"));

        driver.findElement(By.linkText("Клиенты")).click();
        wait.until(ExpectedConditions.titleIs("Список клиентов"));
        tds = Element.getLastRowTDs(driver);
        tds.get(4).click();
        wait.until(ExpectedConditions.titleIs("Список клиентов"));
        driver.findElement(By.linkText("На главную")).click();
        wait.until(ExpectedConditions.titleIs("Главная"));

        driver.findElement(By.linkText("Фильмы")).click();
        wait.until(ExpectedConditions.titleIs("Список фильмов"));
        tds = Element.getLastRowTDs(driver);
        tds.get(3).click();
        wait.until(ExpectedConditions.titleIs("Список фильмов"));
        driver.findElement(By.linkText("На главную")).click();
        wait.until(ExpectedConditions.titleIs("Главная"));

        driver.close();

    }
}
