package epam.rd.traydakalo.service;

import epam.rd.traydakalo.entity.User;
import epam.rd.traydakalo.exceptions.NoSuchUserException;
import epam.rd.traydakalo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(NoSuchUserException::new);
    }
}
