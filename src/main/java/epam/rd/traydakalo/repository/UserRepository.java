package epam.rd.traydakalo.repository;

import epam.rd.traydakalo.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
