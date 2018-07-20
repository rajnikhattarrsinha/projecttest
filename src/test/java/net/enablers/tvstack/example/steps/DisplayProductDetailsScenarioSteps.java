package net.enablers.tvstack.example.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tvstack.example.model.ListingItem;
import net.enablers.tvstack.example.pages.EtsyPage;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import static net.enablers.tvstack.example.model.SessionVariables.SELECTED_LISTING;

public class DisplayProductDetailsScenarioSteps {

    @Steps
    BuyerSteps buyer;

    EtsyPage etsyPage;

    @Given("I have searched for '(.*)' in my region")
    public void givenIHaveSearchedFor(String searchTerm) {

        etsyPage.open();

        buyer.opens_home_page();
        buyer.searches_by_keyword(searchTerm);
        buyer.filters_by_local_region();
    }

    @When("I (?:have selected|select) item (\\d+)")
    public void whenISelectListingItem(int number) {
        ListingItem selectedListingItem = buyer.selects_listing(number);
        Serenity.setSessionVariable(SELECTED_LISTING).to(selectedListingItem);
    }

    @Then("I should see product description and price on the details page")
    public void thenIShouldSeeProductDescriptionAndPriceOnTheDetailsPage() {
        ListingItem selectedListingItem = Serenity.sessionVariableCalled(SELECTED_LISTING);
         buyer.should_see_product_details_for(selectedListingItem);
    }

    @Then("I should see a product rating")
    public void shouldSeeProductRating() {
        buyer.should_see_product_rating();
    }

    @Then("I should see social media links")
    public void shouldSeeSocialMediaLinks() {
        buyer.should_see_facebook_link();
        buyer.should_see_twitter_link();
        buyer.should_see_tumblr_link();
    }
}
