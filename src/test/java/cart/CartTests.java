package cart;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CartTests extends BaseTests {

    @Test
    public void testAddingToCart(){
        homePage.enterSearchParameter("самокат");
        var searchPage = homePage.clickSearch();
        searchPage.scrollToItems();
        //var productPage = searchPage.clickItem("Самокат Roces");
        var productPage = searchPage.clickItem(searchPage.selectRandomItem());
        String itemName = productPage.getProductTitle();
        productPage.addToChart();
        var cartPage = productPage.goToChart();

        assertEquals(itemName,cartPage.getItemDescription());

    }
}
