package search;

import base.BaseTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SearchItemsTests extends BaseTests {

    //test that result of search matches entered search request
    @Test
    public void testSuccessfulSearch() {
        String searchItem = "самокат";
        homePage.enterSearchParameter(searchItem);
        var searchPage = homePage.clickSearch();
        searchPage.scrollToItems();
        var searchedItems = searchPage.getSearchedItemNames();
        assertFalse(searchedItems.isEmpty());
        for (WebElement searchedItem: searchedItems) {
            assertTrue(searchedItem.getText().toLowerCase().contains(searchItem),
                    "Item that does not contain \"" + searchItem + "\" in it's name was found: " + searchedItem.getText() + " /");
        }

    }

    //test that result of search matches search request selected from dropdown
    @Test
    public void testSuccessfulSearchFromDropdown() {
        homePage.enterSearchParameter("самокат");
        var searchPage = homePage.clickSuggestedItemContained("для взрослых");
        searchPage.scrollToItems();
        var productPage = searchPage.clickRandomItem();
        String targetAuditory = productPage.getTargetAuditory();
        assertEquals(targetAuditory, "Взрослая", "Target auditory is not " + "Взрослая /");
    }

    //test that result of search matches with filter by the brand
    @Test
    public void testSuccessfulSelectionOfBrand() {
        homePage.enterSearchParameter("самокат");
        var searchPage = homePage.clickSearch();
        searchPage.scrollToItems();
        String brand = searchPage.selectRandomBrand().toLowerCase();
        System.out.println("Brand is: " + brand);
        searchPage.waitForPageUpdating();
        var searchedItems = searchPage.getSearchedItemNames();
        //verify that all descriptions contain selected brand
        for (WebElement searchedItem: searchedItems) {
            assertTrue(searchedItem.getText().toLowerCase().contains(brand),
                    "No brand \"" + brand + "\" was found in text [" + searchedItem.getText().toLowerCase() + "]/");
        }
    }
}