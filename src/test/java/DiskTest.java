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
public class DiskTest {

    public void CRUDDiskTest() {
        WebDriver driver = Driver.getDriver();
        driver.get(Driver.getAddress());
        Assert.assertEquals(driver.getTitle(), "Главная");

        driver.findElement(By.linkText("Носители")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Список носителей"));
        Assert.assertEquals(driver.getCurrentUrl(), Driver.getAddress() + "list_disk?all=1");
        driver.findElement(By.linkText("Добавить носитель")).click();
        wait.until(ExpectedConditions.titleIs("Добавление носителя"));

        driver.findElement(By.id("name")).sendKeys("Диск 1");
        driver.findElement(By.id("type")).sendKeys("CD");
        driver.findElement(By.id("price")).clear();
        driver.findElement(By.id("price")).sendKeys("100");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Список носителей"));

        List<WebElement> tds = Element.getLastRowTDs(driver);
        assert tds.get(0).getText().contains("Диск 1");
        assert tds.get(1).getText().contains("CD");
        assert tds.get(2).getText().contains("100");

        tds.get(3).click();
        wait.until(ExpectedConditions.titleIs("Изменение носителя"));

        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("type")).clear();
        driver.findElement(By.id("price")).clear();
        driver.findElement(By.id("name")).sendKeys("Диск 2");
        driver.findElement(By.id("type")).sendKeys("DVD");
        driver.findElement(By.id("price")).sendKeys("500");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Список носителей"));

        tds = Element.getLastRowTDs(driver);
        assert tds.get(0).getText().contains("Диск 2");
        assert tds.get(1).getText().contains("DVD");
        assert tds.get(2).getText().contains("500");

        tds.get(4).click();
        wait.until(ExpectedConditions.titleIs("Список носителей"));
        tds = Element.getLastRowTDs(driver);
        if (tds != null && tds.size() > 0) {
            assert !(tds.get(0).getText().contains("Диск 2") &&
                    tds.get(1).getText().contains("DVD") &&
                    tds.get(2).getText().contains("500"));
        }

        driver.close();
    }

    public void FilmDiskTest(){

        WebDriver driver = Driver.getDriver();
        driver.get(Driver.getAddress());
        Assert.assertEquals(driver.getTitle(), "Главная");

        driver.findElement(By.linkText("Фильмы")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
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
        Assert.assertEquals(driver.getCurrentUrl(), Driver.getAddress() + "list_disk?all=1");
        driver.findElement(By.linkText("Добавить носитель")).click();
        wait.until(ExpectedConditions.titleIs("Добавление носителя"));

        driver.findElement(By.id("name")).sendKeys("Диск 3");
        driver.findElement(By.id("type")).sendKeys("CD");
        driver.findElement(By.id("price")).clear();
        driver.findElement(By.id("price")).sendKeys("100");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Список носителей"));

        List<WebElement> tds = Element.getLastRowTDs(driver);
        tds.get(5).click();
        assert driver.findElement(By.tagName("b")).getText().contains("Список фильмов на носителе");
        driver.findElement(By.linkText("Добавить фильм")).click();
        wait.until(ExpectedConditions.titleIs("Добавление фильма"));
        Select select = new Select(driver.findElement(By.id("filmId")));
        select.selectByVisibleText("Фильм Paramount, 1999");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Список фильмов"));

        driver.findElement(By.linkText("На главную")).click();
        wait.until(ExpectedConditions.titleIs("Главная"));
        driver.findElement(By.linkText("Фильмы")).click();
        wait.until(ExpectedConditions.titleIs("Список фильмов"));
        tds = Element.getLastRowTDs(driver);
        tds.get(4).click();
        wait.until(ExpectedConditions.titleIs("Список носителей"));
        tds = Element.getLastRowTDs(driver);
        assert tds.get(0).getText().contains("Диск 3");
        assert tds.get(1).getText().contains("CD");
        assert tds.get(2).getText().contains("100");

        driver.findElement(By.linkText("На главную")).click();
        wait.until(ExpectedConditions.titleIs("Главная"));
        driver.findElement(By.linkText("Носители")).click();
        wait.until(ExpectedConditions.titleIs("Список носителей"));

        tds = Element.getLastRowTDs(driver);
        tds.get(5).click();
        wait.until(ExpectedConditions.titleIs("Список фильмов"));

        tds = Element.getLastRowTDs(driver);
        if (tds != null && tds.size() > 0) {
            assert tds.get(0).getText().contains("Фильм");
            assert tds.get(1).getText().contains("Paramount, 1999");
        }

        tds.get(2).click();
        wait.until(ExpectedConditions.titleIs("Список фильмов"));
        tds = Element.getLastRowTDs(driver);
        if (tds != null && tds.size() > 0) {
            assert !(tds.get(0).getText().contains("Фильм") &&
                    tds.get(1).getText().contains("Paramount, 1999"));
        }

        driver.findElement(By.linkText("На главную")).click();
        wait.until(ExpectedConditions.titleIs("Главная"));
        driver.findElement(By.linkText("Носители")).click();
        wait.until(ExpectedConditions.titleIs("Список носителей"));
        tds = Element.getLastRowTDs(driver);
        tds.get(4).click();
        wait.until(ExpectedConditions.titleIs("Список носителей"));

        driver.close();
    }
}