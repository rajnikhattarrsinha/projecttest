package net.enablers.tvstack.services;

import com.jayway.restassured.response.Response;
import net.enablers.tvstack.helpers.ApiHelper;
import net.enablers.tvstack.model.api.DuplicateRequestModel;
import net.enablers.tvstack.model.api.ProjectRequestModel;
import net.enablers.tvstack.model.api.ProjectUpdateRequestModel;
import net.enablers.tvstack.utilities.RandomGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProjectsService extends ApiHelper {
    private static final Logger log = LoggerFactory.getLogger(ProjectsService.class);

    private static String projectName, projectUpdatedName, projectDuplicateName;

    public static String getProjectName() {
        return projectName;
    }

    public static String getProjectUpdatedName() {
        return projectUpdatedName;
    }

    public static String getProjectDuplicateName() {
        return projectDuplicateName;
    }

    public static void setProjectDuplicateName(String projectDuplicateName) {
        ProjectsService.projectDuplicateName = projectDuplicateName;
    }

    public static Response logIntoPointLogicServer(String oktaToken) {
        return getTvstackConfig(oktaToken).post("/api/login");
    }

//    public static Response logIntoPointLogicServer() {
//        return getTvstackConfig().post("/api/login");
//    }

    public static Response createProject(String oktaToken, List<ProjectRequestModel> projectRequestModels, Object clientId) {
        Response createProjectResponse = null;

        for (ProjectRequestModel projectRequestModel : projectRequestModels) {
            projectName = projectRequestModel.getName() + RandomGenerator.randomAlphanumeric(3);
            projectRequestModel.setName(projectName);
            projectRequestModel.setClientId(clientId.toString());
            projectRequestModel.setStartDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
            projectRequestModel.setEndDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
            createProjectResponse = getTvstackConfig(oktaToken).body(gson().toJson(projectRequestModel)).post("/api/projects");
        }
        return createProjectResponse;
    }

    public static Response updateProject(String oktaToken, List<ProjectUpdateRequestModel> updateRequestModels, Object projectId) {
        Response updateProjectResponse = null;

        for (ProjectUpdateRequestModel updateRequestModel : updateRequestModels) {
            projectUpdatedName = updateRequestModel.getName() + RandomGenerator.randomAlphanumeric(3);
            updateRequestModel.setName(projectUpdatedName);
            updateRequestModel.setId(projectId.toString());
            updateRequestModel.setStartDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
            updateRequestModel.setEndDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
            updateProjectResponse = getTvstackConfig(oktaToken).body(gson().toJson(updateRequestModel)).put("/api/projects");
        }
        return updateProjectResponse;
    }

    public static Response deleteProject(String oktaToken, Object projectId) {
        return getTvstackConfig(oktaToken).delete("/api/projects/" + projectId.toString());
    }

    public static Response duplicteProject(String oktaToken, List<DuplicateRequestModel> duplicatePlanRequestModels, Object projectId) {
        Response duplicateProjectResponse = null;

        for (DuplicateRequestModel duplicatePlanRequestModel : duplicatePlanRequestModels) {
            projectDuplicateName = duplicatePlanRequestModel.getName() + RandomGenerator.randomAlphanumeric(3);
            duplicatePlanRequestModel.setName(projectDuplicateName);

            duplicateProjectResponse = getTvstackConfig(oktaToken).body(gson().toJson(duplicatePlanRequestModel)).post("/api/projects/duplicate/" + projectId.toString());
        }
        return duplicateProjectResponse;
    }

    public static Response createProjectWithInvalidValues(String oktaToken, List<ProjectRequestModel> projectRequestModels, Object clientId) {
        Response createProjectResponse = null;


        for (ProjectRequestModel projectRequestModel : projectRequestModels) {

            if (StringUtils.isNotEmpty(projectRequestModel.getName())) {
                projectName = projectRequestModel.getName() + RandomGenerator.randomAlphanumeric(3);
                projectRequestModel.setName(projectName);
            }

            System.out.println(projectRequestModel.getClientId());

            if (projectRequestModel.getClientId() != null) {
                projectRequestModel.setClientId(clientId);
            }

            if (projectRequestModel.getMarketId() != null) {
                projectRequestModel.setMarketId(String.valueOf(projectRequestModel.getMarketId()));
            }

            if (projectRequestModel.getBudget() != null) {
                projectRequestModel.setBudget(projectRequestModel.getBudget());
            }

            if (!(projectRequestModel.getStartDate().isEmpty())) {
                projectRequestModel.setStartDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
            }
            if (!(projectRequestModel.getEndDate().isEmpty())) {
                projectRequestModel.setEndDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
            }

            createProjectResponse = getTvstackConfig(oktaToken).body(gson().toJson(projectRequestModel)).post("/api/projects");
        }
        return createProjectResponse;
    }

    public static Response definitiveProject(String oktaToken, Object projectId) {
        return getTvstackConfig(oktaToken).put("/api/projects/" + projectId + "/definitive");
    }
}
