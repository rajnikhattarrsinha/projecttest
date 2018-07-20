package net.enablers.tvstack.helpers;

import com.jayway.restassured.response.Response;
import cucumber.api.java.After;
import net.enablers.tvstack.services.ProjectsService;
import net.enablers.tvstack.steps.api.AccessTokenSteps;
import net.enablers.tvstack.steps.api.ManageProjectSteps;
import net.enablers.tvstack.utilities.AppliEyes;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static final Logger log = LoggerFactory.getLogger(Hooks.class);
    private Object projectId;
    private Response deletePlanResponse;
    public AppliEyes appliEyes;
    private String oktaAccessToken;

    @After
    public void tearDown() {
        projectId = ManageProjectSteps.getProjectId();
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        if (projectId != null) {
            deletePlanResponse = ProjectsService.deleteProject(oktaAccessToken, projectId);
            log.info("Project Deleted");
        }

        appliEyes.tearDown();
    }
}
