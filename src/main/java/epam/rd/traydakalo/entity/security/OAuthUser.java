package epam.rd.traydakalo.entity.security;

import javax.persistence.*;

@Entity
public class OAuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String principalName;

    @Embedded
    private ClientRecord clientRecord;

    @Embedded
    private OAuth2Token accessToken;

    public OAuthUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public ClientRecord getClientRecord() {
        return clientRecord;
    }

    public void setClientRecord(ClientRecord clientRecord) {
        this.clientRecord = clientRecord;
    }

    public OAuth2Token getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(OAuth2Token accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OAuthUser oAuthUser = (OAuthUser) o;

        if (!principalName.equals(oAuthUser.principalName)) return false;
        if (!clientRecord.equals(oAuthUser.clientRecord)) return false;
        return accessToken.equals(oAuthUser.accessToken);
    }

    @Override
    public int hashCode() {
        int result = clientRecord.hashCode();
        result = 31 * result + accessToken.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OAuthUser{" +
                "id=" + id +
                ", principalName='" + principalName + '\'' +
                ", clientRecord=" + clientRecord +
                ", accessToken=" + accessToken +
                '}';
    }
}
