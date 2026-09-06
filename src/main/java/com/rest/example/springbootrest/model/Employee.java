package com.rest.example.springbootrest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name",nullable=false)
    @NotBlank(message = "firstName is mandatory")
    private String firstName;
    @Column(name="last_name", nullable = false)
    @NotBlank(message = "lastName is mandatory")
    private String lastName;
    @Column(name="email_id", nullable = false)
    @NotBlank(message = "emailId is mandatory")
    @Email(message = "emailId should be a valid email")
    private String emailId;
}
