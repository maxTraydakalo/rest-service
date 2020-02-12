package epam.rd.traydakalo.configuration.security.config;


import epam.rd.traydakalo.configuration.security.jwt.JwtSecurityConfigurer;
import epam.rd.traydakalo.configuration.security.jwt.JwtTokenProvider;
import epam.rd.traydakalo.configuration.security.shit2.MyAuthProvider;
import epam.rd.traydakalo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
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
                .httpBasic().disable()
                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                .authorizeRequests()
                .antMatchers(SWAGGER).permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/save").permitAll()
//                .antMatchers(API_USER).hasAuthority(Role.SUPER_ADMIN.getAuthority())
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider));
    }


    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
