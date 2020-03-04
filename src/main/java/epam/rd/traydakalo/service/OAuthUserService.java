package epam.rd.traydakalo.service;

import epam.rd.traydakalo.entity.security.OAuthUser;
import epam.rd.traydakalo.mapper.OAuthUserOAuth2AuthorizedClientMapper;
import epam.rd.traydakalo.repository.OAuthUserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;

@Service
public class OAuthUserService {
    OAuthUserRepository oAuthUserRepository;
    OAuthUserOAuth2AuthorizedClientMapper oAuthUserOAuth2AuthorizedClientMapper =
            Mappers.getMapper(OAuthUserOAuth2AuthorizedClientMapper.class);

    public OAuthUserService(OAuthUserRepository oAuthUserRepository) {
        this.oAuthUserRepository = oAuthUserRepository;
    }

    public OAuthUser geOAuthUserById(Long id){
        return oAuthUserRepository.findById(id).get();
    }

    public OAuth2AuthorizedClient loadAuthorizedClient(String clientRegistrationId, String principalName){
        OAuthUser oAuthUser = oAuthUserRepository.findByClientRecord_RegistrationIdAndPrincipalName(clientRegistrationId, principalName).get();
        return oAuthUserOAuth2AuthorizedClientMapper.oAuthUserToOAuth2AuthorizedClient(oAuthUser);
    }

    public OAuthUser saveOAuthUser(OAuth2AuthorizedClient oAuth2AuthorizedClient){
        OAuthUser oAuthUser = oAuthUserOAuth2AuthorizedClientMapper.oAuth2AuthorizedClientToOAuthUser(oAuth2AuthorizedClient);
        return oAuthUserRepository.save(oAuthUser);
    }
}
