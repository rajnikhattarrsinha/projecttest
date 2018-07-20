package net.enablers.tvstack.example.tests;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import net.thucydides.core.webdriver.WebDriverFacade;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class AppliTest {
    public static void main(String[] args) {

        // Open a Chrome browser.
        WebDriverFacade driver = (WebDriverFacade) getDriver();

        // Initialize the eyes SDK and set your private API key.
        Eyes eyes = new Eyes();
        eyes.setApiKey("Xi1O1DBviNxHYA111rY99YbqxsfCHwhrpUDd7w102z4uTkBw110");

        try {

            // Start the test and set the browser's viewport size to 800x600.
            eyes.open(driver.getProxiedDriver(), "tv stack", "Create a plan!",
                    new RectangleSize(800, 600));

            // Navigate the browser to the "hello world!" web-site.
            driver.get("http://ec2-35-177-207-240.eu-west-2.compute.amazonaws.com/");

            // Visual checkpoint #1.
            eyes.checkWindow("Landing page");

            // End the test.
            eyes.close();

        } finally {

            // Close the browser.
            driver.quit();

            // If the test was aborted before eyes.close was called, ends the test as aborted.
            eyes.abortIfNotClosed();
        }

    }

}
