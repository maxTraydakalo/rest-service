package epam.rd.traydakalo.mapper;

import epam.rd.traydakalo.entity.security.OAuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

@Mapper(uses = {Oauth2TokenMapper.class, ClientRegistrationMapper.class})
public interface OAuthUserOAuth2AuthorizedClientMapper {
    ClientRegistrationMapper clientRegistrationMapper = Mappers.getMapper(ClientRegistrationMapper.class);
    Oauth2TokenMapper oAuth2TokenMapper = Mappers.getMapper(Oauth2TokenMapper.class);

    @Mappings({
            @Mapping(source = "principalName", target = "principalName"),
            @Mapping(source = "clientRegistration", target = "clientRecord"),
            @Mapping(source = "accessToken", target = "accessToken")
    })
    OAuthUser oAuth2AuthorizedClientToOAuthUser(OAuth2AuthorizedClient OAuth2AuthorizedClient);

    default OAuth2AuthorizedClient oAuthUserToOAuth2AuthorizedClient(OAuthUser oAuthUser) {
        return new OAuth2AuthorizedClient(
                clientRegistrationMapper.clientRecordToClientRegistration(oAuthUser.getClientRecord()),
                oAuthUser.getPrincipalName(),
                oAuth2TokenMapper.oAuth2TokenToOauth2AccessToken(oAuthUser.getAccessToken()));
    }
}

