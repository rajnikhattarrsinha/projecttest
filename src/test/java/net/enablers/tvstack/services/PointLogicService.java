package net.enablers.tvstack.services;

import com.jayway.restassured.response.Response;
import net.enablers.tvstack.helpers.ApiHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PointLogicService extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(PointLogicService.class);

    public static Response authenticate() {
        return getRequestSpecificationForPointLogicAuth().post();
    }

    public static Response getMarkets(String accessToken) {
        return getMarketsFromPointLogic(accessToken).when().get("/Markets?user=user1");
    }

}
