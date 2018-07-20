package net.enablers.tvstack.services;

import com.jayway.restassured.response.Response;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.admin.ClientsLeadRequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ClientsService extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(ClientsService.class);

    public static Response getClients(String oktaAccessToken) {
        return getTvstackAdminConfig(oktaAccessToken).when().get("/api/clients");
    }

    public static Response createLeadForClient(String oktaAccessToken, String clientId, List<ClientsLeadRequestModel> clientsLeadRequestModels) {

        Response createLeadClientResponse = null;

        for (ClientsLeadRequestModel leadRequestModel : clientsLeadRequestModels) {
            createLeadClientResponse = getTvstackAdminConfig(oktaAccessToken).body(gson().toJson(leadRequestModel)).post("/api/clients/" + clientId + "/leads");
        }
        return createLeadClientResponse;
    }

    public static Response deleteLeadForClient(String oktaAccessToken, String clientId, String userId) {
        return getTvstackAdminConfig(oktaAccessToken).delete("/api/clients/" + clientId + "/leads/" + userId);
    }
}
