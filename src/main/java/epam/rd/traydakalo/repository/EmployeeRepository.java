package epam.rd.traydakalo.repository;

import epam.rd.traydakalo.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("select e from Employee e join e.claims c where c.id= :id")
//    @Query("select e from Claim c join c.Employee e where c.id= :id")
    public Optional<Employee> findByClaimsId(@Param("id") Long id);
}
