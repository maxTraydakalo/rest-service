package epam.rd.traydakalo.configuration.security.config;


import epam.rd.traydakalo.configuration.security.jwt.JwtSecurityConfigurer;
import epam.rd.traydakalo.configuration.security.jwt.JwtTokenProvider;
import epam.rd.traydakalo.configuration.security.shit2.MyAuthProvider;
import epam.rd.traydakalo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private static final String LOGIN = "/login";
    private static final String API_USER = "/api/user/**";
    private static final String[] SWAGGER = {"/v2/api-docs", "/configuration/ui", "/swagger-resources"
            , "/configuration/security", "/swagger-ui.html", "/webjars/**"
            , "/swagger-resources/configuration/ui", "/swagger-ui.html"};

    private final UserService userService;


    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    MyAuthProvider myAuthProvider;
    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    OAuth2AuthorizedClientService authorizedClientService;
    @Autowired
    AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository;
//    @Bean
//    public DaoAuthenticationProvider authProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(encoder());
//        return authProvider;
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                    .authorizationEndpoint()
                        .baseUri("/login/oauth2/authorization")
                        .authorizationRequestRepository(authorizationRequestRepository)
                    .and()
                    .redirectionEndpoint()
                        .baseUri("/login/oauth2/*")
                    .and()
                    .defaultSuccessUrl("/loginSuccess")
//                .and()
//                .tokenEndpoint()
//                .accessTokenResponseClient()
                    .loginPage("/login")
                        .clientRegistrationRepository(clientRegistrationRepository)
                        .authorizedClientService(authorizedClientService)

                .and()

        ;
    }
/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests()
                .antMatchers(SWAGGER).permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/save").permitAll()
                .antMatchers(API_USER).hasAuthority(Role.SUPER_ADMIN.getAuthority())
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider));
    }*/

//    @Override
//    @Autowired
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(myAuthProvider);
//    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
}
