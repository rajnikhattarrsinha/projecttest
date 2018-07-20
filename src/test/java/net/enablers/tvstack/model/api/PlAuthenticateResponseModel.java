package net.enablers.tvstack.model.api;

import com.google.gson.annotations.SerializedName;

public class PlAuthenticateResponseModel {

        @SerializedName("IsAuthenticated")
        private boolean isAuthenticated;

        @SerializedName("AccessToken")
        private  String accessToken;

        @SerializedName("AuthenticationStatus")
        private  Number authenticationStatus;

        public boolean isAuthenticated() {
                return isAuthenticated;
        }

        public void setAuthenticated(boolean authenticated) {
                isAuthenticated = authenticated;
        }

        public String getAccessToken() {
                return accessToken;
        }

        public void setAccessToken(String accessToken) {
                this.accessToken = accessToken;
        }

        public Number getAuthenticationStatus() {
                return authenticationStatus;
        }

        public void setAuthenticationStatus(Number authenticationStatus) {
                this.authenticationStatus = authenticationStatus;
        }
}
