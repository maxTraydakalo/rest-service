package epam.rd.traydakalo.configuration.security.config;

import epam.rd.traydakalo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.endpoint.NimbusAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Configuration
@PropertySource("classpath:application.properties")
public class SecurityBeans {
    private static String CLIENT_PROPERTY_KEY
            = "spring.security.oauth2.client.registration.";
    private static List<String> clients = Arrays.asList("google", "github");

    @Autowired
    private Environment env;

    @Bean
    BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//
//    @Bean
//    public OAuth2AuthorizedClientService authorizedClientService() {
//
//        return new InMemoryOAuth2AuthorizedClientService(
//                clientRegistrationRepository());
//    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = clients.stream()
                .map(this::getRegistration)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new InMemoryClientRegistrationRepository(registrations);
    }

    private ClientRegistration getRegistration(String client) {
        String clientProp = CLIENT_PROPERTY_KEY + client + ".client-id";
        String clientId = env.getProperty(clientProp);

        if (clientId == null) {
            return null;
        }

        String clientSecret = env.getProperty(
                CLIENT_PROPERTY_KEY + client + ".client-secret");

        if (client.equals("google")) {
            return CommonOAuth2Provider.GOOGLE
                    .getBuilder(client)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .redirectUriTemplate("{baseUrl}/login/oauth2/{registrationId}")
                    .build();
        }
        if (client.equals("github")) {
            return CommonOAuth2Provider.GITHUB
                    .getBuilder(client)
                    .clientId(clientId).clientSecret(clientSecret)
                    .redirectUriTemplate("{baseUrl}/login/oauth2/{registrationId}")
                    .build();
        }
        return null;
    }

    @Bean
    public AuthorizationRequestRepository<OAuth2AuthorizationRequest>
    authorizationRequestRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public WebClient rest(ClientRegistrationRepository clients, OAuth2AuthorizedClientRepository authz) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clients, authz);
        return WebClient.builder()
                .filter(oauth2).build();
    }
    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest>
    accessTokenResponseClient() {
        return new NimbusAuthorizationCodeTokenResponseClient();
    }

//    @Bean
//    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService(WebClient rest) {
//        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
//        return request -> {
//            OAuth2User user = delegate.loadUser(request);
//            if (!"github".equals(request.getClientRegistration().getRegistrationId())) {
//                return user;
//            }
//
//            OAuth2AuthorizedClient client = new OAuth2AuthorizedClient
//                    (request.getClientRegistration(), user.getName(), request.getAccessToken());
//            String url = user.getAttribute("organizations_url");
//            List<Map<String, Object>> orgs = rest
//                    .get().uri(url)
//                    .attributes(oauth2AuthorizedClient(client))
//                    .retrieve()
//                    .bodyToMono(List.class)
//                    .block();
//
//            if (orgs.stream().anyMatch(org -> "spring-projects".equals(org.get("login")))) {
//                return user;
//            }
//
//            throw new OAuth2AuthenticationException(new OAuth2Error("invalid_token", "Not in Spring Team", ""));
//        };
//    }


}
