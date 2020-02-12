package epam.rd.traydakalo.controllers.rest;

import epam.rd.traydakalo.entity.User;
import epam.rd.traydakalo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user){
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return userService.saveUser(user);
    }
}
