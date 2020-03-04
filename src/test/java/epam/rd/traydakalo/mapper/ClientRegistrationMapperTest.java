package epam.rd.traydakalo.mapper;

import epam.rd.traydakalo.entity.security.ClientRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class ClientRegistrationMapperTest {
    ClientRegistrationMapper clientRegistrationMapper;
    ClientRegistration clientRegistration;
    ClientRecord clientRecord;

    @BeforeEach
    public void init() {
        clientRegistrationMapper =
                Mappers.getMapper(ClientRegistrationMapper.class);

        clientRegistration = ClientRegistration
                .withRegistrationId("1")
                .clientId("2")
                .clientSecret("3")
                .clientName("4")
                .authorizationGrantType(new AuthorizationGrantType("Bearer"))
                .redirectUriTemplate("{baseUrl}/login/oauth2/{registrationId}")
                .authorizationUri("{baseUrl}/login/oauth2/{registrationId}")
                .tokenUri("{baseUrl}/login/oauth2/{registrationId}")
                .userInfoUri("{baseUrl}/login/oauth2/{registrationId}")
                .build();

        clientRecord = new ClientRecord();
        clientRecord.setRegistrationId("1");
        clientRecord.setClientId("2");
        clientRecord.setClientSecret("3");
        clientRecord.setClientName("4");
        clientRecord.setRedirectUriTemplate("{baseUrl}/login/oauth2/{registrationId}");
        clientRecord.setAuthorizationUri("{baseUrl}/login/oauth2/{registrationId}");
        clientRecord.setTokenUri("{baseUrl}/login/oauth2/{registrationId}");
        clientRecord.setUserInfoEndpoint("{baseUrl}/login/oauth2/{registrationId}");
        clientRecord.setAuthorizationGrantType("Bearer");
    }

    @Test
    public void shouldConvertClientRegistrationToClientRecord() {
        ClientRecord clientRecordActual =
                clientRegistrationMapper
                        .ClientRegistrationToClientRecord(clientRegistration);

        assertEquals(clientRecord, clientRecordActual);
    }

    @Test
    public void shouldConvertClientRecordToClientRegistration() {
        ClientRegistration clientRegistrationActual = clientRegistrationMapper.clientRecordToClientRegistration(clientRecord);
        assertEquals(clientRegistration.getClientId(), clientRegistrationActual.getClientId());
        assertEquals(clientRegistration.getRegistrationId(), clientRegistrationActual.getRegistrationId());
        assertEquals(clientRegistration.getClientSecret(), clientRegistrationActual.getClientSecret());
        assertEquals(clientRegistration.getClientName(), clientRegistrationActual.getClientName());
        assertEquals(clientRegistration.getAuthorizationGrantType(), clientRegistrationActual.getAuthorizationGrantType());
    }
}