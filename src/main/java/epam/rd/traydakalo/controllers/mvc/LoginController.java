package epam.rd.traydakalo.controllers.mvc;

import epam.rd.traydakalo.entity.dto.UserDto;
import epam.rd.traydakalo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class LoginController {

    private static final String INVALID_EMAIL_OR_PASSWORD = "email or password invalid";
    private static final String USER_NOT_FOUND = "user not found";

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
//    private AuthenticationManager authenticationManager;
//
//    public LoginController(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

//    @PostMapping("/login")
//    @ResponseBody
//    public ResponseEntity login(/*@ModelAttribute(value = "username") String username,
//                            @ModelAttribute(value = "password") String password*/) {
////        Authentication authentication =
////                new UsernamePasswordAuthenticationToken(username, password);
////        authenticationManager.authenticate(authentication);
//
//        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication());
//    }
//
//    @ModelAttribute(value = "username") String username,
//    @ModelAttribute(value = "password") String password
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserDto userDto) {
        System.out.println(userDto.getPassword());
        System.out.println(userDto.getUsername());
        return Collections.singletonMap("token", loginService.authenticateAndGetToken(userDto));
    }
}




