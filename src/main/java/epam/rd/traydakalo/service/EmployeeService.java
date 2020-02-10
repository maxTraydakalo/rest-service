package epam.rd.traydakalo.service;

import epam.rd.traydakalo.entity.Employee;
import epam.rd.traydakalo.exceptions.NoSuchEmployeeException;
import epam.rd.traydakalo.repository.EmployeeRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(NoSuchEmployeeException::new);
    }

    @Query
    public Employee findByClaimsId(Long id) {
        return employeeRepository.findByClaimsId(id).orElseThrow(NoSuchEmployeeException::new);
    }
}
