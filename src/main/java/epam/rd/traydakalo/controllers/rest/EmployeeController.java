package epam.rd.traydakalo.controllers.rest;

import epam.rd.traydakalo.entity.Employee;
import epam.rd.traydakalo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @GetMapping("/employeesByClaimsId/{id}")
    public Employee getEmployeeByClaimsId(@PathVariable Long id){
        return employeeService.findByClaimsId(id);
    }
}
