package net.enablers.tvstack.example.steps;

import net.enablers.tvstack.example.model.ListingItem;
import net.enablers.tvstack.example.model.OrderCostSummary;
import net.enablers.tvstack.example.pages.CartPage;
import net.enablers.tvstack.example.pages.EtsyPage;
import net.enablers.tvstack.example.pages.ListingPage;
import net.enablers.tvstack.example.pages.SearchResultsPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class BuyerSteps extends ScenarioSteps {

    EtsyPage etsyPage;
    SearchResultsPage searchResultsPage;
    ListingPage listingPage;
    CartPage cartPage;

    @Step
    public void opens_home_page() {
        etsyPage.open();
    }

    @Step
    public void searches_by_keyword(String keyword) {
        etsyPage.enterSearchTerms(keyword);
        etsyPage.search();
        etsyPage.dismissLocationMessage();
    }

    @Step
    public void should_see_results_summary_containing(String keyword) {
        assertThat(searchResultsPage.getSearchHeader()).containsIgnoringCase(keyword);
    }

    @Step
    public void searches_for_shop_called(String shopName) {
        etsyPage.searchForShopCalled(shopName);
    }

    @Step
    public void should_see_shop_search_result_summary_of(int count, String shopName) {
        assertThat(searchResultsPage.getResultSummary()).containsIgnoringCase(String.valueOf(count)).containsIgnoringCase(shopName);
    }

    @Step
    public ListingItem selects_listing(int articleNumber) {
        return searchResultsPage.selectListing(articleNumber);
    }

    @Step
    public void should_see_product_details_for(ListingItem selectedListingItem) {
        ListingItem displayedListingItem = listingPage.getDisplayedListing();
        assertThat(displayedListingItem).isEqualTo(selectedListingItem);
    }

    @Step
    public void adds_current_listing_to_cart() {
        listingPage.selectOptionIfPresent();
        listingPage.addToCart();
    }

    @Step
    public void should_see_item_in_cart(ListingItem selectedItem) {
        assertThat(cartPage.getOrderCostSummaries()
                        .stream().anyMatch(order -> order.getName().equals(selectedItem.getName()))).isTrue();
    }

    @Step
    public void should_see_total_including_shipping_for(ListingItem selectedItem) {
        OrderCostSummary orderCostSummary = cartPage.getOrderCostSummaryFor(selectedItem).get();

        double itemTotal = orderCostSummary.getItemTotal();
        double shipping = orderCostSummary.getShipping();

        assertThat(itemTotal).isEqualTo(selectedItem.getPrice());
        assertThat(shipping).isGreaterThan(0.0);
    }

    @Step
    public void should_see_product_rating() {
        assertThat(listingPage.getRating()).isGreaterThan(0);
    }

    @Step
    public void should_see_twitter_link() {
        listingPage.twitterIcon().shouldBeVisible();
    }

    @Step
    public void should_see_tumblr_link() {
        listingPage.tumblrIcon().shouldBeVisible();
    }

    @Step
    public void should_see_facebook_link() {
        listingPage.facebookIcon().shouldBeVisible();
    }


    @Step
    public void should_see_nonexistant_field() {
        assertThat(listingPage.clickImaginaryButton()).isFalse();
    }


    @Step
    public void filters_by_local_region() {
        searchResultsPage.filterByLocalRegion();
    }
}
