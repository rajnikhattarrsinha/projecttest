package net.enablers.tvstack.helpers;
import cucumber.api.Transformer;

public class testData extends Transformer<String> {

    public String transform(String value)
    {
        if(WebHelper.dictTestData.containsKey(value))
        {
            value = WebHelper.dictTestData.get("value");
        }
        return value;
    }
}
