package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Random;

public class SearchPage {
    private WebDriver driver;
    private By searchedItemNames = By.xpath("//div/div/a[contains(@class, 'tile-hover-target')][2]");
    private By brandNames = By.xpath("//div[div[contains(text(),'Бренды')]]//label//span");
    private By blockOfItems = By.xpath("//div[@data-widget='megaPaginator']");
    private By labelOfBrands = By.xpath("//span[contains(text(),'Бренды')]");

    private static final int numberOfVisibleItems = 8;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getSearchedItemNames() {
        List<WebElement> itemDescription = driver.findElements(searchedItemNames);
        System.out.println("getSearchedItemNames");
        return itemDescription;
    }

    public void scrollToItems() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //checked presence of block with founded items
        wait.until(ExpectedConditions.presenceOfElementLocated(blockOfItems));
        WebElement itemsSectionElement = driver.findElement(blockOfItems);
        String script = "arguments[0].scrollIntoView();";
        ((JavascriptExecutor)driver).executeScript(script, itemsSectionElement);
        System.out.println("scrollToItems");
    }

    public ProductPage clickItem(String text) {
        driver.findElement(By.partialLinkText(text)).click();
        System.out.println("clickItem " + text);
        return new ProductPage(driver);
    }

    public ProductPage clickRandomItem() {
        Random random = new Random();
        int rand = random.nextInt(numberOfVisibleItems) + 1;
        String stringXpathOfItem = "(//div/div/a[contains(@class, 'tile-hover-target')][2])[" + rand + "]";
        By locatorOfItem = By.xpath(stringXpathOfItem);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(locatorOfItem));
        driver.findElement(locatorOfItem).click();
        System.out.println("clickRandomItem");
        return new ProductPage(driver);
    }

    public String getRandomItemText() {
        Random random = new Random();
        int rand = random.nextInt(numberOfVisibleItems) + 1;
        String stringXpathOfItem = "(//div/div/a[contains(@class, 'tile-hover-target')][2])[" + rand + "]";
        By locatorOfItem = By.xpath(stringXpathOfItem);
        return driver.findElement(locatorOfItem).getText();
    }

    public String selectRandomBrand() {
        int randomBrandNumber = generateRandomNumberForBrand();
        String xpathBrand = "(//div[div[contains(text(),'Бренды')]]//label//span)[" + randomBrandNumber + "]";
        By selectedBrand = By.xpath(xpathBrand);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(selectedBrand));
        String selectedBrandName = driver.findElement(selectedBrand).getText();
        driver.findElement(selectedBrand).click();
        System.out.println("selectRandomBrand");
        return selectedBrandName;
    }

    public void waitForPageUpdating() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(labelOfBrands));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(labelOfBrands)));
        System.out.println("waitForPageUpdating");
    }

    //takes number of available brands on the page and generates random number
    private int generateRandomNumberForBrand() {
        List<WebElement> brands = driver.findElements(brandNames);
        Random rand = new Random();
        return rand.nextInt(brands.size()) + 1;
    }
}
