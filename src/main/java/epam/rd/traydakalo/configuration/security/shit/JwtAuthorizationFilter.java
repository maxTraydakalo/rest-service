//package epam.rd.traydakalo.configuration.security.shit;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import static epam.rd.traydakalo.configuration.security.shit.SecurityConstants.HEADER_STRING;
//import static epam.rd.traydakalo.configuration.security.shit.SecurityConstants.TOKEN_PREFIX;
//
//
//public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest req,
//                                    HttpServletResponse res,
//                                    FilterChain chain) throws IOException, ServletException {
//        String header = req.getHeader(HEADER_STRING);
//        String headerA = req.getHeader("a");
//        System.out.println(headerA);
//        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
//            chain.doFilter(req, res);
//            return;
//        }
//        String token = jwtTokenProvider.resolveToken(req);
//        Authentication authentication = jwtTokenProvider.getAuthentication(token);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        chain.doFilter(req, res);
//    }
//
////    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
////        String token = request.getHeader(HEADER_STRING);
////        if (token != null) {
////            // parse the token.
////            String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
////                    .build()
////                    .verify(token.replace(TOKEN_PREFIX, ""))
////                    .getSubject();
////
////            if (user != null) {
////                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
////            }
////            return null;
////        }
////        return null;
////    }
//}