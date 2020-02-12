package epam.rd.traydakalo.configuration.security.jwt;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final String JWT_AUTHENTICATION_FAILED = "Jwt authentication failed";


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        //log.debug(JWT_AUTHENTICATION_FAILED + authException);
        ((HttpServletResponse) response).addHeader("entry", "filter");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, JWT_AUTHENTICATION_FAILED);

    }

}
