package epam.rd.traydakalo.service;

import epam.rd.traydakalo.configuration.security.jwt.JwtTokenProvider;
import epam.rd.traydakalo.entity.User;
import epam.rd.traydakalo.entity.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class LoginService {
    private static final String USERNAME = "username";
    private static final String NOT_FOUND = "not found";
    private static final String TOKEN = "token";

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    @Autowired
    public LoginService(AuthenticationManager authenticationManager,
                            JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    public String authenticateAndGetToken(UserDto userDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        String token =jwtTokenProvider.createToken(userDto.getUsername(), userService.getUserByUsername(userDto.getUsername()).getAuthorities());
        HashMap <String, String> tokenResponseInJson = new HashMap<>();
        tokenResponseInJson.put(TOKEN, token);
        return token;
    }
}
