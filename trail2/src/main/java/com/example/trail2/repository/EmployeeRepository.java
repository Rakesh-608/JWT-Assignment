package com.example.trail2.repository;


import com.example.trail2.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, Integer> {
    boolean existsByFilenumber(Integer filenumber);
}
