package com.example.trail2.service;

import com.example.trail2.model.Employee;
import com.example.trail2.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByFilenumber(employee.getFilenumber())) {
            throw new IllegalArgumentException("Employee with filenumber " + employee.getFilenumber() + " already exists");
        }
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeByFilenumber(Integer filenumber) {
        return employeeRepository.findById(filenumber);
    }

    public Employee updateEmployee(Integer filenumber, Employee employee) {
        if (!employeeRepository.existsById(filenumber)) {
            throw new IllegalArgumentException("Employee with filenumber " + filenumber + " not found");
        }
        if (!filenumber.equals(employee.getFilenumber())) {
            throw new IllegalArgumentException("Filenumber in path and body must match");
        }
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer filenumber) {
        if (!employeeRepository.existsById(filenumber)) {
            throw new IllegalArgumentException("Employee with filenumber " + filenumber + " not found");
        }
        employeeRepository.deleteById(filenumber);
    }
}
