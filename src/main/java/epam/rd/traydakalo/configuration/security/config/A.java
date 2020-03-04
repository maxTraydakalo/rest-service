package epam.rd.traydakalo.configuration.security.config;

import epam.rd.traydakalo.service.OAuthUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Component;

@Component
public class A implements OAuth2AuthorizedClientService {
    OAuthUserService oAuthUserService;

    public A(OAuthUserService oAuthUserService) {
        this.oAuthUserService = oAuthUserService;
    }

    @Override
    public  OAuth2AuthorizedClient loadAuthorizedClient(String clientRegistrationId, String principalName) {
        return oAuthUserService.loadAuthorizedClient(clientRegistrationId, principalName);
    }

    //TODO create nested class for oauth user and
    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
        oAuthUserService.saveOAuthUser(authorizedClient);
    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {

    }
}
