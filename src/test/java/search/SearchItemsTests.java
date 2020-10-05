package search;

import base.BaseTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.Category;

import java.util.List;

import static org.testng.Assert.*;

public class SearchItemsTests extends BaseTests {

    @Test
    //test that result of search matches entered search request
    public void testSuccessfulSearch(){
        homePage.enterSearchParameter("ложка");
        var searchPage = homePage.clickSearch();
        searchPage.scrollToItems();
        var searchedItems = searchPage.getSearchedItemNames();
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
        var searchPage = homePage.clickSuggestedItemContained("для взрослых");
        searchPage.scrollToItems();
        var productPage = searchPage.clickRandomItem();
        String targetAuditory = productPage.getTargetAuditory();
        assertEquals(targetAuditory, "Взрослая");
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