package net.enablers.tvstack.helpers;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class WebHelper extends PageObject
{
    public void WaitForPageLoad(int timeoutinsecond)
    {
        //@ Wait for browser Ready state.
        waitABit(100);
        JavascriptExecutor executor = (JavascriptExecutor)getDriver();
        try
        {
            for(int i =0;i<timeoutinsecond;i++)
            {
                String browserState = String.valueOf(executor.executeScript("return document.readyState"));
                if(browserState.equals("complete"))
                {
                    break;
                }
                else
                {
                    waitABit(1000);
                }
            }

        }
        catch (Exception e)
        {}
        //@ Wait for Loader
        try
        {
            for (int i = 0; i < timeoutinsecond; i++)
            {
                WebElement elementajax = getDriver().findElement(By.xpath("//*[@aria-label='Loading']"));
                if(elementajax.isDisplayed())
                {
                    waitABit(1000);
                }
                else
                {
                    break;
                }
            }
        }
        catch (Exception e)
        { }

        //@ Wait for Spinner
        try
        {
            for (int i = 0; i < timeoutinsecond; i++)
            {
                WebElement elementajax = getDriver().findElement(By.xpath("//*[contains(@class,'Spinner')]"));
                if(elementajax.isDisplayed())
                {
                    waitABit(1000);
                }
                else
                {
                    break;
                }
            }
        }
        catch (Exception e)
        { }

    }
}
