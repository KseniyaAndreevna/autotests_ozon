package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private WebDriver driver;
    private By productTitle = By.xpath("//div[@data-widget='webProductHeading']/h1");
    private By addToChartButton = By.xpath("(//div[text()='Добавить в корзину'])[1]");
    private By goToChartButton = By.xpath("//div[text()='Перейти']");
    private By targetAuditoryDescription = By.xpath("//dl[dt/span[contains(text(),'Целевая аудитория')]]/dd");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductTitle() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(productTitle));
        System.out.println("getProductTitle");
        return driver.findElement(productTitle).getText();
    }

    public String getTargetAuditory() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(targetAuditoryDescription));
        System.out.println("getTargetAuditory");
        return driver.findElement(targetAuditoryDescription).getText();
    }

    public void addToChart() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(addToChartButton));
        driver.findElement(addToChartButton).click();
        System.out.println("addToChart");
    }

    public CartPage goToChart() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(goToChartButton));
        driver.findElement(goToChartButton).click();
        System.out.println("goToChart");
        return new CartPage(driver);
    }
}
