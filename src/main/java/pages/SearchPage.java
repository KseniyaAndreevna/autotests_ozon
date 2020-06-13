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
    private By searchedItemNames = By.cssSelector(".a2w9.tile-hover-target");
    private By RidexCheckbox = By.xpath("//label[div[span[text()=\"Ridex\"]]]/div/span");//
    private By brandNames = By.xpath("//div[div[contains(text(),'Бренды')]]//label//span");

//    //ValuteCursOnDate[VchCode[contains(text(), 'EUR')]]/Vcurs/text()

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getSearchedItemNames(){
        List<WebElement> itemDescription = driver.findElements(searchedItemNames);
        return itemDescription;
    }

    public void scrollToItems(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-widget='megaPaginator']")));
        WebElement itemsSectionElement = driver.findElement(By.xpath("//div[@data-widget='megaPaginator']"));
        String script = "arguments[0].scrollIntoView();";
        ((JavascriptExecutor)driver).executeScript(script, itemsSectionElement);
    }

    public ProductPage clickItem(String text){
        driver.findElement(By.partialLinkText(text)).click();
        return new ProductPage(driver);
    }

    public void selectRidexBrand(){
        try {
            driver.findElement(RidexCheckbox).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        delay5seconds();
    }

    public String selectRandomBrand(){
        delay5seconds();
        int randomBrandNumber = generateRandomNumberForBrand();
        By selectedBrand = By.xpath("(//div[div[contains(text(),'Бренды')]]//label//span)[" + randomBrandNumber + "]");
        String selectedBrandName = driver.findElement(selectedBrand).getText();
        driver.findElement(selectedBrand).click();
        return selectedBrandName;
    }


    //takes number of available brands on the page and generates random number
    private int generateRandomNumberForBrand(){
        List<WebElement> brands = driver.findElements(brandNames);
        Random rand = new Random();
        return rand.nextInt(brands.size()) + 1;
    }

    private void delay5seconds(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
