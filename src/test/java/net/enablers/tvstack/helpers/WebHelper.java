package net.enablers.tvstack.helpers;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.LinkedHashMap;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import java.util.ArrayList;

public class WebHelper extends PageObject
{
    public static LinkedHashMap<String, String> dictTestData = new LinkedHashMap<String, String>();

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

    private void fn_AddTestDataforApplication(String TestId, String WorkSheetPath, String WorkSheetName)
    {
        dictTestData.clear();
        try
        {
            String strQuery = "Select * from "+WorkSheetName+" where TESTCASEID='"+TestId+"' and ExecutionFlag='YES'";
            Fillo fillo=new Fillo();
            Connection connection=fillo.getConnection(WorkSheetPath);
            Recordset recordset=connection.executeQuery(strQuery);
            ArrayList<String> colCount = recordset.getFieldNames();
            for(int i=0;i<recordset.getCount();i++)
            {
                recordset.next();
                for(int j=0;j<colCount.size();j++)
                {
                    String Keyname = colCount.get(j);
                    String ColValue =recordset.getField(Keyname).toString();
                    dictTestData.put(Keyname, ColValue);
                }
            }
            recordset.close();
            connection.close();
            return ;
        }
        catch (Exception e)
        {}
        return ;

    }
}
