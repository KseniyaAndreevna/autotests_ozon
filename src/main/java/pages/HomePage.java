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
    //private By suggestionItem = By.cssSelector(".suggestions-item.type-suggests");
    private By suggestionItem = By.cssSelector("a[href*='/search/?']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearchParameter(String itemName){
        driver.findElement(inputField).sendKeys(itemName);
        System.out.println("enterSearchParameter");
    }

    public SearchPage clickSearch() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(submitSearchButton)));
        //sleep(3);
        driver.findElement(submitSearchButton).click();
        System.out.println("clickSearch");
        return new SearchPage(driver);
    }

    public SearchPage clickSuggestedItemContained(String text){
        //wait for appearance of suggested items
        CountDownLatch latch = new CountDownLatch(1);
        Thread thread = new Thread(() -> {
            while (latch.getCount() > 0) {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                    System.out.println(new Date());
                } catch (Exception e) {}
            }
        });
        thread.start();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(suggestionItem)));
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(suggestionItem)));
        //sleep(1);
        //click on item with text coincidental with passed text
        latch.countDown();
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

        System.out.println("clickSuggestedItemContained");
        return new SearchPage(driver);
    }

    private void sleep(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
