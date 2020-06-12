package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private WebDriver driver;
    private By productTitle = By.xpath("//div[@data-widget='webProductHeading']/h1");
    private By addToChartButton = By.xpath("(//div[text()='Добавить в корзину'])[1]");
    private By goToChartButton = By.xpath("//div[text()='Перейти']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductTitle(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.findElement(productTitle).getText();
    }

    public void addToChart(){
//        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions.elementToBeClickable(addToChartButton));
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(addToChartButton).click();
    }

    public CartPage goToChart(){
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(goToChartButton).click();
        return new CartPage(driver);
    }
}
