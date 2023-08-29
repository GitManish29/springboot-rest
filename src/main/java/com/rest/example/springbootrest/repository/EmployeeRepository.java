package com.rest.example.springbootrest.repository;

import com.rest.example.springbootrest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {
//All CRUD Databases Methods

}
