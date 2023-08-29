package com.rest.example.springbootrest.controller;

import com.rest.example.springbootrest.exception.ResourceNotFoundException;
import com.rest.example.springbootrest.model.Employee;
import com.rest.example.springbootrest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();

    }

    // Build create employee REST API
    @PostMapping()
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);

    }

    // Get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return ResponseEntity.ok(employee);
    }

    // Build update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails) {
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
      updateEmployee.setFirstName(employeeDetails.getFirstName());
      updateEmployee.setLastName(employeeDetails.getLastName());
      updateEmployee.setEmailId(employeeDetails.getEmailId());
      employeeRepository.save(updateEmployee);
      return ResponseEntity.ok(updateEmployee);
    }
    // Build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee not exist with id:"));
        employeeRepository.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

