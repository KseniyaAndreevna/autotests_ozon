package cart;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CartTests extends BaseTests {

    //test that in cart actually added chosen item
    @Test
    public void testAddingToCart() {
        homePage.enterSearchParameter("самокат");
        var searchPage = homePage.clickSearch();
        searchPage.scrollToItems();
        var productPage = searchPage.clickItem(searchPage.getRandomItemText());
        String itemName = productPage.getProductTitle();
        productPage.addToChart();
        var cartPage = productPage.goToChart();
        String itemDescription = cartPage.getItemDescription();
        assertEquals(itemName, itemDescription);
    }
}