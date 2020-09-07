import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import testUtils.Driver;
import testUtils.Element;

import java.util.List;

@Test
public class FilmTest {

    public void CRUDFilmTest() {
        WebDriver driver = Driver.getDriver();
        driver.get(Driver.getAddress());
        Assert.assertEquals(driver.getTitle(), "Главная");

        driver.findElement(By.linkText("Фильмы")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Список фильмов"));
        Assert.assertEquals(driver.getCurrentUrl(), Driver.getAddress() + "list_film");
        driver.findElement(By.linkText("Добавить фильм")).click();
        wait.until(ExpectedConditions.titleIs("Добавление фильма"));

        driver.findElement(By.id("name")).sendKeys("Фильм");
        driver.findElement(By.id("info")).sendKeys("Paramount, 1999");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Список фильмов"));

        List<WebElement> tds = Element.getLastRowTDs(driver);
        assert tds.get(0).getText().contains("Фильм");
        assert tds.get(1).getText().contains("Paramount, 1999");

        tds.get(2).click();
        wait.until(ExpectedConditions.titleIs("Изменение фильма"));

        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("info")).clear();
        driver.findElement(By.id("name")).sendKeys("Новый Фильм");
        driver.findElement(By.id("info")).sendKeys("20 Century Fox, 2000");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Список фильмов"));

        tds = Element.getLastRowTDs(driver);
        assert tds.get(0).getText().contains("Новый Фильм");
        assert tds.get(1).getText().contains("20 Century Fox, 2000");

        tds.get(3).click();
        wait.until(ExpectedConditions.titleIs("Список фильмов"));
        tds = Element.getLastRowTDs(driver);
        if (tds != null && tds.size() > 0) {
            assert !tds.get(0).getText().contains("Новый Фильм");
            assert !tds.get(1).getText().contains("20 Century Fox, 2000");
        }

        driver.close();
    }
}