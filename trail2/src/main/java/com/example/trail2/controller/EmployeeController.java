package com.example.trail2.controller;

import com.example.trail2.model.Employee;
import com.example.trail2.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Create
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        try {
            Employee savedEmployee = employeeService.createEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // Read Single
    @GetMapping("/{filenumber}")
    public ResponseEntity<Employee> getEmployeeByFilenumber(@PathVariable Integer filenumber) {
        return employeeService.getEmployeeByFilenumber(filenumber)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Update
    @PutMapping("/{filenumber}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer filenumber, @Valid @RequestBody Employee employee) {
        try {
            Employee updatedEmployee = employeeService.updateEmployee(filenumber, employee);
            return ResponseEntity.ok(updatedEmployee);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Delete
    @DeleteMapping("/{filenumber}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer filenumber) {
        try {
            employeeService.deleteEmployee(filenumber);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
