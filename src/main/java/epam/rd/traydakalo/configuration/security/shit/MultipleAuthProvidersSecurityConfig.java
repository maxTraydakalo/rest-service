//package epam.rd.traydakalo.configuration.security.config;
//
//import epam.rd.traydakalo.configuration.security.filter.JwtTokenAuthenticationFilter;
//import epam.rd.traydakalo.configuration.security.shit.CustomAuthenticationProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@EnableWebSecurity
//@Configuration
//public class MultipleAuthProvidersSecurityConfig
//        extends WebSecurityConfigurerAdapter {
//    @Autowired
//    CustomAuthenticationProvider customAuthProvider;
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//
//        auth.authenticationProvider(customAuthProvider);
////        auth.inMemoryAuthentication()
////                .withUser("memuser")
////                .password(passwordEncoder().encode("pass"))
////                .roles("USER");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().disable()
//                .csrf().disable()
//                .formLogin().loginPage("/login").permitAll()
//                .loginProcessingUrl("/login")
//                .usernameParameter("email")
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Bean("authenticationManager")
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//}