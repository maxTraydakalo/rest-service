package epam.rd.traydakalo.entity.security;

import javax.persistence.Embeddable;

@Embeddable
public class ClientRecord {
    private String registrationId;
    private String clientId;
    private String clientSecret;
    private String clientName;
    private String authorizationGrantType;
    private String redirectUriTemplate;
    private String authorizationUri;
    private String tokenUri;
    private String userInfoEndpoint;

    public ClientRecord() {
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAuthorizationGrantType() {
        return authorizationGrantType;
    }

    public void setAuthorizationGrantType(String authorizationGrantType) {
        this.authorizationGrantType = authorizationGrantType;
    }

    public String getRedirectUriTemplate() {
        return redirectUriTemplate;
    }

    public void setRedirectUriTemplate(String redirectUriTemplate) {
        this.redirectUriTemplate = redirectUriTemplate;
    }

    public String getAuthorizationUri() {
        return authorizationUri;
    }

    public void setAuthorizationUri(String authorizationUri) {
        this.authorizationUri = authorizationUri;
    }

    public String getTokenUri() {
        return tokenUri;
    }

    public void setTokenUri(String tokenUri) {
        this.tokenUri = tokenUri;
    }

    public String getUserInfoEndpoint() {
        return userInfoEndpoint;
    }

    public void setUserInfoEndpoint(String userInfoEndpoint) {
        this.userInfoEndpoint = userInfoEndpoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientRecord that = (ClientRecord) o;

        if (!registrationId.equals(that.registrationId)) return false;
        if (!clientId.equals(that.clientId)) return false;
        if (!clientSecret.equals(that.clientSecret)) return false;
        return clientName.equals(that.clientName);
    }

    @Override
    public int hashCode() {
        int result = registrationId.hashCode();
        result = 31 * result + clientId.hashCode();
        result = 31 * result + clientSecret.hashCode();
        result = 31 * result + clientName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ClientRecord{" +
                "registrationId='" + registrationId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", clientName='" + clientName + '\'' +
                ", authorizationGrantType='" + authorizationGrantType + '\'' +
                '}';
    }
}
