package search;

import base.BaseTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SearchItemsTests extends BaseTests {

    @Test
    //test that result of search matches entered search request
    public void testSuccessfulSearch(){
        homePage.enterSearchParameter("ложка");
        System.out.println("enterSearchParameter");
        var searchPage = homePage.clickSearch();
        System.out.println("clickSearch");
        searchPage.scrollToItems();
        System.out.println("scrollToItems");
        var searchedItems = searchPage.getSearchedItemNames();
        System.out.println("getSearchedItemNames");
        System.out.println("searchedItems = " + searchedItems);
        assertFalse(searchedItems.isEmpty());
        for (WebElement searchedItem: searchedItems){
            assertTrue(searchedItem.getText().matches("^Самокат"));
            System.out.println("matches");
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
        searchPage.scrollToItems();

        //collect descriptions of items
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        var searchedItems = searchPage.getSearchedItemNames();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //verify that all descriptions contain selected brand
        for (WebElement searchedItem: searchedItems){
            assertTrue(searchedItem.getText().contains("brand"));
        }

    }
}