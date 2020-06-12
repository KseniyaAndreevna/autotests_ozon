package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;
    private By itemDescription = By.cssSelector("a.a7t8 span");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getItemDescription(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.findElement(itemDescription).getText();
    }
}
