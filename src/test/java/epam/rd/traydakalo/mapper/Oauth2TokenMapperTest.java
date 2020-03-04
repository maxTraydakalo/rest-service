package epam.rd.traydakalo.mapper;

import epam.rd.traydakalo.entity.security.OAuth2Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class Oauth2TokenMapperTest {
    OAuth2Token oAuth2Token;
    OAuth2AccessToken oAuth2AccessToken;
    Oauth2TokenMapper oauth2TokenMapper = Mappers.getMapper(Oauth2TokenMapper.class);

    @BeforeEach
    public void init() {
        oAuth2Token = new OAuth2Token();
        oAuth2Token.setTokenValue("ya29.ImDBB9xSkuZSrWtSlz2PEbMfDxVbsR39x-1QGx9RXWS145TTFweYkulsk4FQbE3QnGAGLd2hJNMRhd2V01wpWJsCaf77gcsg35d-1MI8BoW5JoRan1K2ivGiyGiTaZASK90");
        oAuth2Token.setIssuedAt(Instant.parse("2017-02-03T11:25:30.00Z"));
        oAuth2Token.setExpiresAt(Instant.parse("2020-02-03T11:25:30.00Z"));
        oAuth2Token.setTokenType("Bearer");

        oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                "ya29.ImDBB9xSkuZSrWtSlz2PEbMfDxVbsR39x-1QGx9RXWS145TTFweYkulsk4FQbE3QnGAGLd2hJNMRhd2V01wpWJsCaf77gcsg35d-1MI8BoW5JoRan1K2ivGiyGiTaZASK90",
                Instant.parse("2017-02-03T11:25:30.00Z"), Instant.parse("2020-02-03T11:25:30.00Z"));
    }

    @Test
    public void shouldConvertOAuth2AccessTokenToOAuth2Token() {
        OAuth2Token oAuth2TokenActual = oauth2TokenMapper.OAuth2AccessTokenToOAuth2Token(oAuth2AccessToken);
        assertEquals(oAuth2Token, oAuth2TokenActual);
    }

    @Test
    public void shouldConvertOAuth2TokenToOAuth2AccessToken() {
        OAuth2AccessToken oAuth2AccessTokenActual = oauth2TokenMapper.oAuth2TokenToOauth2AccessToken(oAuth2Token);
        assertEquals(oAuth2AccessToken, oAuth2AccessTokenActual);
    }

}