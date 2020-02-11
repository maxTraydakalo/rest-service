//package epam.rd.traydakalo.configuration.security.shit;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//
//import static epam.rd.traydakalo.configuration.security.shit.SecurityConstants.*;
//
//
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//    private AuthenticationManager authenticationManager;
//
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest req,
//                                                HttpServletResponse res) throws AuthenticationException {
////        try {
////            User creds = new ObjectMapper()
////                    .readValue(req.getInputStream(), User.class);
//
//        System.out.println(req.getHeader("l")+" "+req.getHeader("p"));
//            return authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            req.getHeader("l"),
//                            req.getHeader("p"),
//                            new ArrayList<>())
//            );
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest req,
//                                            HttpServletResponse res,
//                                            FilterChain chain,
//                                            Authentication auth) throws IOException, ServletException {
//        System.out.println(auth.getPrincipal());
//        String token = jwtTokenProvider
//                .createToken(
//                        auth.getPrincipal().toString(),
//                        auth.getAuthorities());
//
////                JWT.create()
////                .withSubject(((User) auth.getPrincipal()).getUsername())
////                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
////                .sign(HMAC512(SECRET.getBytes()));
//        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
//    }
//}