package net.enablers.tvstack.example.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.example.model.ListingItem;
import net.enablers.tvstack.example.model.SessionVariables;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

/**
 * Created by john on 12/11/14.
 */
public class ShoppingCartScenarioSteps {

    @Steps
    BuyerSteps buyer;

    @When("I add it to the cart")
    public void addToCart() {
        buyer.adds_current_listing_to_cart();
    }

    @Then("the item should appear in the cart")
    public void shouldSeeSelectedItemInCart() {
        ListingItem selectedItem = (ListingItem) Serenity.getCurrentSession().get(SessionVariables.SELECTED_LISTING);
        buyer.should_see_item_in_cart(selectedItem);
    }

    @Then("the shipping cost should be included in the total price")
    public void shouldIncludeShippingCost() {
        ListingItem selectedItem = (ListingItem) Serenity.getCurrentSession().get(SessionVariables.SELECTED_LISTING);
        buyer.should_see_total_including_shipping_for(selectedItem);
    }

    double itemCost;

    @Given("I have selected an item that costs (.*)")
    public void itemCosts(double cost) {
        itemCost = cost;
    }

    @When("I review the total price")
    public void reviewTotalPrice() {
    }

    @Then("the price should be (.*) including (.*) tax")
    public void priceShouldBe(double total, double tax) {
    }

}
