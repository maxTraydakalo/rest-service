package epam.rd.traydakalo.service;

import epam.rd.traydakalo.entity.security.OAuthUser;
import epam.rd.traydakalo.exceptions.UserAlreadyRegisteredException;
import epam.rd.traydakalo.mapper.OAuthUserOAuth2AuthorizedClientMapper;
import epam.rd.traydakalo.repository.OAuthUserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OAuthUserService {
    OAuthUserRepository oAuthUserRepository;
    OAuthUserOAuth2AuthorizedClientMapper oAuthUserOAuth2AuthorizedClientMapper =
            Mappers.getMapper(OAuthUserOAuth2AuthorizedClientMapper.class);

    public OAuthUserService(OAuthUserRepository oAuthUserRepository) {
        this.oAuthUserRepository = oAuthUserRepository;
    }


    public OAuth2AuthorizedClient loadAuthorizedClient(String clientRegistrationId, String principalName) {
        OAuthUser oAuthUser = oAuthUserRepository
                .findByClientRecord_RegistrationIdAndPrincipalName(clientRegistrationId, principalName)
                .orElseThrow(UserAlreadyRegisteredException::new);
        return oAuthUserOAuth2AuthorizedClientMapper.oAuthUserToOAuth2AuthorizedClient(oAuthUser);
    }

    @Transactional
    public void saveOAuthUser(OAuth2AuthorizedClient oAuth2AuthorizedClient) {
        boolean isUserAlreadyRegistered = isUserAlreadyRegistered(
                oAuth2AuthorizedClient.getClientRegistration().getRegistrationId(),
                oAuth2AuthorizedClient.getPrincipalName());
        if (isUserAlreadyRegistered) {
            throw new UserAlreadyRegisteredException();
        }

        OAuthUser oAuthUser = oAuthUserOAuth2AuthorizedClientMapper.oAuth2AuthorizedClientToOAuthUser(oAuth2AuthorizedClient);
        oAuthUserRepository.save(oAuthUser);
    }

    public boolean isUserAlreadyRegistered(String clientRegistrationId, String principalName) {
        return oAuthUserRepository
                .findByClientRecord_RegistrationIdAndPrincipalName(clientRegistrationId, principalName)
                .isPresent();
    }

    public void deleteOAuthUser(String clientRegistrationId, String principalName) {
        OAuthUser oAuthUser = oAuthUserRepository
                .findByClientRecord_RegistrationIdAndPrincipalName(clientRegistrationId, principalName)
                .orElseThrow(UserAlreadyRegisteredException::new);
        oAuthUserRepository.deleteById(oAuthUser.getId());
    }
}
