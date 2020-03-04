package epam.rd.traydakalo.mapper;

import epam.rd.traydakalo.entity.security.OAuth2Token;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import java.time.Instant;

@Mapper
public interface Oauth2TokenMapper {
    @Mappings({
            @Mapping(source = "tokenValue", target = "tokenValue"),
            @Mapping(source = "issuedAt", target = "issuedAt"),
            @Mapping(source = "expiresAt", target = "expiresAt"),
            @Mapping(source = "tokenType.value", target = "tokenType")
    })
    OAuth2Token OAuth2AccessTokenToOAuth2Token(OAuth2AccessToken oAuth2AccessToken);

    default OAuth2AccessToken oAuth2TokenToOauth2AccessToken(OAuth2Token oAuth2Token) {
        return new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                oAuth2Token.getTokenValue(),
                oAuth2Token.getIssuedAt(),
                oAuth2Token.getExpiresAt());
    }
}
