package epam.rd.traydakalo.controllers.mvc;

import org.apache.tomcat.util.http.parser.Cookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    private AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(/*@ModelAttribute(value = "username") String username,
                            @ModelAttribute(value = "password") String password*/) {
//        Authentication authentication =
//                new UsernamePasswordAuthenticationToken(username, password);
//        authenticationManager.authenticate(authentication);

        return String.valueOf("2");
    }
}
