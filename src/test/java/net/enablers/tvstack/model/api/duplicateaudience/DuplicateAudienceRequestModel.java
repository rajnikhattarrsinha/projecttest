package net.enablers.tvstack.model.api.duplicateaudience;

public class DuplicateAudienceRequestModel {
    private Audience audience;
    private Object projectId;

    public Audience getAudience() {
        return audience;
    }

    public void setAudience(Audience audience) {
        this.audience = audience;
    }

    public Object getProjectId() {
        return projectId;
    }

    public void setProjectId(Object projectId) {
        this.projectId = projectId;
    }
}
