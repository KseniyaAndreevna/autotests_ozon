package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {
    private WebDriver driver;
    private By inputField = By.cssSelector("input[name='search']");
    private By submitSearchButton = By.cssSelector("button[type='submit']");
    private By suggestionItem = By.cssSelector(".suggestions-item.type-suggests");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearchParameter(String itemName){
        driver.findElement(inputField).sendKeys(itemName);
    }

    public SearchPage clickSearch() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(submitSearchButton)));
        //sleep(3);
        driver.findElement(submitSearchButton).click();
        return new SearchPage(driver);
    }

    public Category clickSuggestedItemContained(String text){
        //wait for appearance of suggested items
        WebDriverWait wait = new WebDriverWait(driver, 5);
        //wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(suggestionItem)));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(suggestionItem)));
        sleep(1);
        //click on item with text coincidental with passed text
        List<WebElement> suggestedItems = driver.findElements(suggestionItem);

        boolean isItemFound = false;
        for (WebElement item: suggestedItems){
            String name = item.getText();
            if (name.contains(text)){
                isItemFound = true;
                item.click();
                break;
            }
        }
        if (!isItemFound){
            throw new NotFoundException("Suggested items with text " + text + " not found.");
        }

        return new Category(driver);
    }

    private void sleep(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
