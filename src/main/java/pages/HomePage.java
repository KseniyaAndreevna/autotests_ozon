package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class HomePage {
    private WebDriver driver;
    private By inputField = By.cssSelector("input[name='search']");
    private By submitSearchButton = By.cssSelector("button[type='submit']");
    private By searchSuggestions = By.cssSelector("section[data-widget='searchSuggestions']");
    private By suggestionItem = By.cssSelector("a[href*='/search/?']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearchParameter(String itemName) {
        driver.findElement(inputField).sendKeys(itemName);
        System.out.println("enterSearchParameter");
    }

    public SearchPage clickSearch() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(submitSearchButton)));
        driver.findElement(submitSearchButton).click();
        System.out.println("clickSearch");
        return new SearchPage(driver);
    }

    public SearchPage clickSuggestedItemContained(String text) {
        //wait for appearance of suggested items
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(searchSuggestions));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(suggestionItem)));
        List<WebElement> suggestedItems = driver.findElements(suggestionItem);

        boolean isItemFound = false;
        for (WebElement item: suggestedItems) {
            String name = item.getText();
            if (name.contains(text)) {
                isItemFound = true;
                item.click();
                break;
            }
        }

        if (!isItemFound) {
            throw new NotFoundException("Suggested items with text " + text + " not found.");
        }

        System.out.println("clickSuggestedItemContained");
        return new SearchPage(driver);
    }
}
