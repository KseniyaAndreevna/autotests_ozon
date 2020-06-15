package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;
    private By itemDescription = By.xpath("(//div[@data-widget])[23]/div/div/a/span");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getItemDescription(){
        sleep(5);
        return driver.findElement(itemDescription).getText();
    }

    private void sleep(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
