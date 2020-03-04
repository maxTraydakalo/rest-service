package epam.rd.traydakalo.mapper;

import epam.rd.traydakalo.entity.security.ClientRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Mapper(componentModel = "spring")
public interface ClientRegistrationMapper {
    //    @Mappings({
//            @Mapping(source = "registrationId", target = "registrationId"),
//            @Mapping(source = "clientId", target = "clientId"),
//            @Mapping(source = "clientSecret", target = "clientSecret"),
//            @Mapping(source = "clientName", target = "clientName")
//    })
//    ClientRegistration clientRecordToClientRegistration(ClientRecord clientRecord);
    default ClientRegistration clientRecordToClientRegistration(ClientRecord clientRecord) {
        return ClientRegistration
                .withRegistrationId(clientRecord.getRegistrationId())
                .clientId(clientRecord.getClientId())
                .clientSecret(clientRecord.getClientSecret())
                .clientName(clientRecord.getClientName())
                .authorizationGrantType(new AuthorizationGrantType(clientRecord.getAuthorizationGrantType()))
                .redirectUriTemplate(clientRecord.getRedirectUriTemplate())
                .authorizationUri(clientRecord.getAuthorizationUri())
                .tokenUri(clientRecord.getTokenUri())
                .userInfoUri(clientRecord.getUserInfoEndpoint())
                .build();
    }

    @Mappings({
            @Mapping(source = "registrationId", target = "registrationId"),
            @Mapping(source = "clientId", target = "clientId"),
            @Mapping(source = "clientSecret", target = "clientSecret"),
            @Mapping(source = "clientName", target = "clientName"),
            @Mapping(source = "authorizationGrantType.value", target = "authorizationGrantType"),
            @Mapping(source = "redirectUriTemplate", target = "redirectUriTemplate"),
            @Mapping(source = "providerDetails.authorizationUri", target = "authorizationUri"),
            @Mapping(source = "providerDetails.tokenUri", target = "tokenUri"),
            @Mapping(source = "providerDetails.userInfoEndpoint.uri", target = "userInfoEndpoint")
    })
    ClientRecord ClientRegistrationToClientRecord(ClientRegistration clientRegistration);
}
