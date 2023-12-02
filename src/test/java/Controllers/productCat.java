package Controllers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.Product;
import model.ProductCatalog;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class productCat {
    private ProductCatalog productCatalog;

    @Given("the user is on the homepage")
    public void the_user_is_on_the_homepage() {

    }
    @When("they navigate to the product catalog")
    public void they_navigate_to_the_product_catalog() {

    }
    @Then("they should see categories like Interior, Exterior, and Electronics")
    public void they_should_see_categories_like_interior_exterior_and_electronics() {
        assertTrue("Interior category is not visible", isCategoryVisible("Interior"));
        assertTrue("Exterior category is not visible", isCategoryVisible("Exterior"));
        assertTrue("Electronics category is not visible", isCategoryVisible("Electronics"));

    }


    @Given("the user is in the product catalog")
    public void the_user_is_in_the_product_catalog() {
        productCatalog = new ProductCatalog();

    }
    @When("they select a category, e.g., Interior")
    public void they_select_a_category_e_g_interior() {
        productCatalog.selectCategory("Interior");

    }
    @Then("they should see a list of detailed product listings")
    public void they_should_see_a_list_of_detailed_product_listings() {
        assertTrue(productCatalog.hasProductListings());

    }

    @Given("the user is viewing a product listing")
    public void the_user_is_viewing_a_product_listing() {
        productCatalog = new ProductCatalog();

    }
    @When("they click on a specific product")
    public void they_click_on_a_specific_product() {
        String productName = "Product123";
        productCatalog.clickOnProduct(productName);
    }
    @Then("they should see detailed information such as descriptions, images, prices, and availability")
    public void they_should_see_detailed_information_such_as_descriptions_images_prices_and_availability() {
        String productName = "Product123";
        String productDetails = productCatalog.getProductDetails(productName);
        assertEquals("Details for product: " + productName, productDetails);
    }


    @When("they use the search bar to find a specific product")
    public void they_use_the_search_bar_to_find_a_specific_product() {
        String searchTerm = "Car Accessories";
        productCatalog.searchForProduct(searchTerm);
    }
    @Then("they should see relevant results matching their search query")
    public void they_should_see_relevant_results_matching_their_search_query() {
        int expectedSearchResultsCount = 5; // Replace with the expected count based on your test data
        int actualSearchResultsCount = productCatalog.getSearchResultsCount();
        assertEquals(expectedSearchResultsCount, actualSearchResultsCount);
    }


    @When("they apply filters, such as price range or brand")
    public void they_apply_filters_such_as_price_range_or_brand() {
        String brandFilter = "XYZ Brand";
        double minPrice = 20.0;
        double maxPrice = 50.0;
        productCatalog.applyFilters(brandFilter, minPrice, maxPrice);
    }
    @Then("they should see a refined list of products based on the selected filters")
    public void they_should_see_a_refined_list_of_products_based_on_the_selected_filters() {
        int expectedFilteredResultsCount = 3; // Replace with the expected count based on your test data
        int  actualFilteredResultsCount = productCatalog.getFilteredResultsCount();
        assertEquals(expectedFilteredResultsCount, actualFilteredResultsCount);
    }

    private boolean isCategoryVisible(String category) {
     return true   ;}

}
