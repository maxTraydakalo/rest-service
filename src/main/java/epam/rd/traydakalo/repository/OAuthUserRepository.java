package epam.rd.traydakalo.repository;

import epam.rd.traydakalo.entity.security.OAuthUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OAuthUserRepository extends CrudRepository<OAuthUser, Long> {
    Optional<OAuthUser> findByClientRecord_RegistrationIdAndPrincipalName(String clientRecord_registrationId, String principalName);

    @Override
    void deleteById(Long aLong);
}
