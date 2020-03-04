package epam.rd.traydakalo.mapper;

import epam.rd.traydakalo.entity.security.ClientRecord;
import epam.rd.traydakalo.entity.security.OAuth2Token;
import epam.rd.traydakalo.entity.security.OAuthUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class OAuthUserOAuth2AuthorizedClientMapperTest {
    OAuthUser oAuthUser;
    OAuth2AuthorizedClient oAuth2AuthorizedClient;
    OAuthUserOAuth2AuthorizedClientMapper oAuthUserOAuth2AuthorizedClientMapper =
            Mappers.getMapper(OAuthUserOAuth2AuthorizedClientMapper.class);

    @BeforeEach
    public void init(){
        OAuth2Token oAuth2Token = new OAuth2Token();
        oAuth2Token.setTokenValue("ya29.ImDBB9xSkuZSrWtSlz2PEbMfDxVbsR39x-1QGx9RXWS145TTFweYkulsk4FQbE3QnGAGLd2hJNMRhd2V01wpWJsCaf77gcsg35d-1MI8BoW5JoRan1K2ivGiyGiTaZASK90");
        oAuth2Token.setIssuedAt(Instant.parse("2017-02-03T11:25:30.00Z"));
        oAuth2Token.setExpiresAt(Instant.parse("2020-02-03T11:25:30.00Z"));
        oAuth2Token.setTokenType("Bearer");

        OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                "ya29.ImDBB9xSkuZSrWtSlz2PEbMfDxVbsR39x-1QGx9RXWS145TTFweYkulsk4FQbE3QnGAGLd2hJNMRhd2V01wpWJsCaf77gcsg35d-1MI8BoW5JoRan1K2ivGiyGiTaZASK90",
                Instant.parse("2017-02-03T11:25:30.00Z"), Instant.parse("2020-02-03T11:25:30.00Z"));


        ClientRegistration clientRegistration = ClientRegistration
                .withRegistrationId("1")
                .clientId("2")
                .clientSecret("3")
                .clientName("4")
                .authorizationGrantType(new AuthorizationGrantType("Bearer"))
                .redirectUriTemplate("{baseUrl}/login/oauth2/{registrationId}")
                .build();

        ClientRecord clientRecord = new ClientRecord();
        clientRecord.setRegistrationId("1");
        clientRecord.setClientId("2");
        clientRecord.setClientSecret("3");
        clientRecord.setClientName("4");
        clientRecord.setRedirectUriTemplate("{baseUrl}/login/oauth2/{registrationId}");
        clientRecord.setAuthorizationGrantType("Bearer");


        oAuthUser = new OAuthUser();
        oAuthUser.setPrincipalName("113934858618049064669");
        oAuthUser.setClientRecord(clientRecord);
        oAuthUser.setAccessToken(oAuth2Token);

        oAuth2AuthorizedClient = new OAuth2AuthorizedClient(clientRegistration, "113934858618049064669", oAuth2AccessToken);
    }

    @Test
    public void shouldConvertOAuth2AuthorizedClientToOAuthUser(){
        OAuthUser oAuthUserActual = oAuthUserOAuth2AuthorizedClientMapper
                .oAuth2AuthorizedClientToOAuthUser(oAuth2AuthorizedClient);
        assertEquals(oAuthUser, oAuthUserActual);
    }

    @Test
    public void shouldConvertOauthUserToOAuthAuthorizedClient(){
        OAuth2AuthorizedClient oAuth2AuthorizedClientActual = oAuthUserOAuth2AuthorizedClientMapper
                .oAuthUserToOAuth2AuthorizedClient(oAuthUser);
        assertEquals(oAuth2AuthorizedClient.getPrincipalName(),oAuth2AuthorizedClientActual.getPrincipalName());
        assertEquals(oAuth2AuthorizedClient.getAccessToken().getTokenType(),oAuth2AuthorizedClientActual.getAccessToken().getTokenType());
        assertEquals(oAuth2AuthorizedClient.getAccessToken().getIssuedAt(),oAuth2AuthorizedClientActual.getAccessToken().getIssuedAt());
        assertEquals(oAuth2AuthorizedClient.getAccessToken().getExpiresAt(),oAuth2AuthorizedClientActual.getAccessToken().getExpiresAt());
        assertEquals(oAuth2AuthorizedClient.getAccessToken().getTokenType(),oAuth2AuthorizedClientActual.getAccessToken().getTokenType());
        assertEquals(oAuth2AuthorizedClient.getClientRegistration().getClientId(),oAuth2AuthorizedClientActual.getClientRegistration().getClientId());
        assertEquals(oAuth2AuthorizedClient.getClientRegistration().getRegistrationId(),oAuth2AuthorizedClientActual.getClientRegistration().getRegistrationId());
        assertEquals(oAuth2AuthorizedClient.getClientRegistration().getClientSecret(),oAuth2AuthorizedClientActual.getClientRegistration().getClientSecret());
        assertEquals(oAuth2AuthorizedClient.getClientRegistration().getClientName(),oAuth2AuthorizedClientActual.getClientRegistration().getClientName());
        assertEquals(oAuth2AuthorizedClient.getClientRegistration().getAuthorizationGrantType(),oAuth2AuthorizedClientActual.getClientRegistration().getAuthorizationGrantType());
    }
}