package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Category {
    private WebDriver driver;
    private By itemDescription = By.xpath("//span[contains(text(), 'Целевая аудитория: ')]");

    public Category(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getTextDescription(){
        List<WebElement> descriptions = driver.findElements(itemDescription);
//        for (WebElement description: descriptions){
//            System.out.println(description.getText());
//        }
        return descriptions;
    }
}
