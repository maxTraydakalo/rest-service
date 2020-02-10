package epam.rd.traydakalo.configuration.security.shit;

import epam.rd.traydakalo.entity.User;
import epam.rd.traydakalo.exceptions.BadCredentialsException;
import epam.rd.traydakalo.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private UserService userService;

    public CustomAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication auth)
            throws AuthenticationException {
        User user = userService.findUserByEmail(auth.getPrincipal().toString());

        if (user.getPassword().equals(auth.getCredentials())) {
            return new UsernamePasswordAuthenticationToken(user.getEmail(),
                    user.getPassword(), Collections.emptyList());
        } else {
            throw new BadCredentialsException();
        }


//        if ("externaluser".equals(username) && "pass".equals(password)) {
//            System.out.println(username + " " + password);
////            SecurityContext sc = SecurityContextHolder.getContext();
////            sc.setAuthentication(auth);
//            return new UsernamePasswordAuthenticationToken
//                    (username, password, Collections.emptyList());
//        } else {
//            throw new
//                    BadCredentialsException("External system authentication failed");
//        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}

