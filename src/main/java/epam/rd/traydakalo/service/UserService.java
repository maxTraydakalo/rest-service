package epam.rd.traydakalo.service;

import epam.rd.traydakalo.entity.User;
import epam.rd.traydakalo.exceptions.NoSuchUserException;
import epam.rd.traydakalo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByEmail(username);
    }

    public User findUserByEmail(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(NoSuchUserException::new);
    }

    public User saveUser(User user){
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }
}
