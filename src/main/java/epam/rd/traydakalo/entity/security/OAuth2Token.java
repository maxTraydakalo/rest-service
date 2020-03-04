package epam.rd.traydakalo.entity.security;

import javax.persistence.Embeddable;
import java.time.Instant;

@Embeddable
public class OAuth2Token {
    private String tokenValue;
    private String tokenType;
    Instant issuedAt;
    Instant expiresAt;

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Instant getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Instant issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiredAt) {
        this.expiresAt = expiredAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OAuth2Token that = (OAuth2Token) o;

        if (!tokenValue.equals(that.tokenValue)) return false;
        if (!issuedAt.equals(that.issuedAt)) return false;
        return expiresAt.equals(that.expiresAt);
    }

    @Override
    public int hashCode() {
        int result = tokenValue.hashCode();
        result = 31 * result + issuedAt.hashCode();
        result = 31 * result + expiresAt.hashCode();
        return result;
    }
}
