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
public class ClientTest {

    public void CRUDClientTest() {
        WebDriver driver = Driver.getDriver();
        driver.get(Driver.getAddress());
        Assert.assertEquals(driver.getTitle(), "Главная");

        driver.findElement(By.linkText("Клиенты")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Список клиентов"));
        Assert.assertEquals(driver.getCurrentUrl(), Driver.getAddress() + "list_client");
        driver.findElement(By.linkText("Добавить клиента")).click();
        wait.until(ExpectedConditions.titleIs("Добавление клиента"));

        driver.findElement(By.id("name")).sendKeys("Иван Иванов");
        driver.findElement(By.id("email")).sendKeys("address@domain.com");
        driver.findElement(By.id("phone")).sendKeys("+700000000");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Список клиентов"));

        List<WebElement> tds = Element.getLastRowTDs(driver);
        assert tds.get(0).getText().contains("Иван Иванов");
        assert tds.get(1).getText().contains("address@domain.com");
        assert tds.get(2).getText().contains("+700000000");

        tds.get(3).click();
        wait.until(ExpectedConditions.titleIs("Изменение клиента"));

        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("phone")).clear();
        driver.findElement(By.id("name")).sendKeys("Петр Петров");
        driver.findElement(By.id("email")).sendKeys("newaddress@domain.com");
        driver.findElement(By.id("phone")).sendKeys("+800000000");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Список клиентов"));

        tds = Element.getLastRowTDs(driver);
        assert tds.get(0).getText().contains("Петр Петров");
        assert tds.get(1).getText().contains("newaddress@domain.com");
        assert tds.get(2).getText().contains("+800000000");

        tds.get(4).click();
        wait.until(ExpectedConditions.titleIs("Список клиентов"));
        tds = Element.getLastRowTDs(driver);
        if (tds != null && tds.size() > 0) {
            assert !(tds.get(0).getText().contains("Петр Петров") &&
            tds.get(1).getText().contains("newaddress@domain.com") &&
            tds.get(2).getText().contains("+800000000"));
        }

        driver.close();
    }
}