package epam.rd.traydakalo.configuration.security.shit2;

import epam.rd.traydakalo.entity.User;
import epam.rd.traydakalo.exceptions.NoSuchUserException;
import epam.rd.traydakalo.service.UserService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyAuthProvider implements AuthenticationProvider {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public MyAuthProvider(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userService.getUserByUsername(authentication.getPrincipal().toString());
          if(bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())){
            return new UsernamePasswordAuthenticationToken(user.getId(), null, user.getAuthorities());
        }
        throw new NoSuchUserException();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}

