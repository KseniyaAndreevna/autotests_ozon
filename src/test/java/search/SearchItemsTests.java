package search;

import base.BaseTests;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SearchItemsTests extends BaseTests {

    @Test
    //test that result of search matches entered search request
    public void testSuccessfulSearch(){
        homePage.enterSearchParameter("самокат");
        var searchPage = homePage.clickSearch();
        var searchedItems = searchPage.getSearchedItemNames();
        for (WebElement searchedItem: searchedItems){
            assertTrue(searchedItem.getText().matches("^Самокат"));
        }

    }

    @Test
    //test that result of search matches search request selected from dropdown
    public void testSuccessfulSearchFromDropdown(){
        homePage.enterSearchParameter("самокат");

        List<WebElement> descriptions = homePage.clickSuggestedItemContained("для взрослых")
                                                .getTextDescription();

        for (WebElement description: descriptions){
            assertTrue(description.getText().contains("Целевая аудитория: Взрослая"));
        }

    }
    @Test
    //test result of search with filter by the brand
    public void testSuccessfulSelectionOfBrand(){
        homePage.enterSearchParameter("самокат");
        var searchPage = homePage.clickSearch();
        searchPage.scrollToItems();
        //click on random brand and return it's name
        String brand = searchPage.selectRandomBrand();

        var searchedItems = searchPage.getSearchedItemNames();
        for (WebElement searchedItem: searchedItems){
            assertTrue(searchedItem.getText().contains("brand"));
        }
        //searchPage.selectRandomBrand();

    }
}