package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private WebDriver driver;
    private By itemDescription = By.xpath("(//div[@data-widget])[23]/div/div/a/span");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getItemDescription() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(itemDescription));
        System.out.println("getItemDescription");
        return driver.findElement(itemDescription).getText();
    }
}
