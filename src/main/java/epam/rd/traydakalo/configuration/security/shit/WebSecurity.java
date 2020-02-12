//package epam.rd.traydakalo.configuration.security.shit;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.context.annotation.Bean;
//
//import javax.annotation.PostConstruct;
//
//@EnableWebSecurity
//public class WebSecurity extends WebSecurityConfigurerAdapter {
//
//
//    @Autowired
//    private AuthProvider authProvider;
//
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//    private JwtAuthorizationFilter jwtAuthorizationFilter;
//
//    @PostConstruct
//    public void addconfig() throws Exception {
//        jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
//        jwtAuthorizationFilter = new JwtAuthorizationFilter(authenticationManager());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
////                .formLogin().usernameParameter("email").loginPage("/login")
////                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilter(jwtAuthenticationFilter)
//                .addFilter(jwtAuthorizationFilter)
//                // this disables session creation on Spring Security
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }
//
//    @Override
//    @Autowired
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authProvider);
//    }
//
//    @Bean
//    public PasswordEncoder bCryptPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//
////    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
////    @Primary
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////    }
//
//
//    @Bean
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//}