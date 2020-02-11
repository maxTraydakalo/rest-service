//package epam.rd.traydakalo.configuration.security.filter;
//
//import epam.rd.traydakalo.configuration.security.jwt.JwtTokenProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtTokenAuthenticationFilter extends GenericFilterBean {
//
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    public JwtTokenAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
//            throws IOException, ServletException {
//        ((HttpServletResponse)res).addHeader("a", "a");
//        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
//        if (!token.isEmpty() && jwtTokenProvider.validateToken(token)) {
//            Authentication auth = jwtTokenProvider.getAuthentication(token);
//            if (auth != null) {
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }
//        }
//        filterChain.doFilter(req, res);
//    }
//
//}
