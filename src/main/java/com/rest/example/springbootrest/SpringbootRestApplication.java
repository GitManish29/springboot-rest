package com.rest.example.springbootrest;

import com.rest.example.springbootrest.model.Employee;
import com.rest.example.springbootrest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootRestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestApplication.class, args);
	}
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {

		Employee employee=new Employee();
		employee.setFirstName("John");
		employee.setLastName("Parker");
		employee.setEmailId("abc@gmail.com");
		employeeRepository.save(employee);

		Employee employee1=new Employee();
		employee1.setFirstName("Jennie");
		employee1.setLastName("Kurat");
		employee1.setEmailId("xyz@gmail.com");
		employeeRepository.save(employee);


	}
}
