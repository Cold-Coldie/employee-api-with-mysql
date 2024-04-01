package com.coldie.mysqljpaappnew.controller;

import com.coldie.mysqljpaappnew.model.Employee;
import com.coldie.mysqljpaappnew.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Operation(
            description = "Get all employees.",
            summary = "This endpoint returns all the Employees available and can be paginated. By default sorted by the \"id\" in the ascending order."

    )
    @GetMapping("/employee")
    public ResponseEntity<Page<Employee>> getEmployees(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return new ResponseEntity<Page<Employee>>(employeeService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
    }

    @Operation(
            description = "Get a single employee by \"id\".",
            summary = "This endpoint returns an Employee available having the \"id\" provided."

    )
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @Operation(
            description = "Get a list employees by having the \"name\" provided",
            summary = "This endpoint returns a list of Employees available having the \"name\" provided."

    )
    @GetMapping("/employee/filterByName")
    public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name) {
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByName(name), HttpStatus.OK);
    }

    @Operation(
            description = "Get a list employees by having the \"name\" and \"location\" provided",
            summary = "This endpoint returns a list of Employees available having the \"name\" and \"location\" provided. This uses JPA derived queries."

    )
    @GetMapping("/employee/filterByNameAndLocation")
    public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@RequestParam String name, @RequestParam String location) {
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByNameAndLocation(name, location), HttpStatus.OK);
    }

    @Operation(
            description = "Get a list employees by having the \"name\" or \"location\" provided",
            summary = "This endpoint returns a list of Employees available having the \"name\" or \"location\" provided. This uses JPQL queries."

    )
    @GetMapping("/employee/{name}/{location}")
    public ResponseEntity<List<Employee>> getEmployeesByNameOrLocation(@PathVariable String name, @PathVariable String location) {
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByNameOrLocation(name, location), HttpStatus.OK);
    }

    @Operation(
            description = "Create a new Employee",
            summary = "This endpoint is used to create a new Employee."

    )
    @PostMapping("/employee")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @Operation(
            description = "Update an Employee",
            summary = "This endpoint is used to update the details of an Employee. The \"id\" of the Employee must be provided"

    )
    @PutMapping("/employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
    }

    @Operation(
            description = "Delete an Employee",
            summary = "This endpoint is used to delete an Employee from the database. The \"id\" of the Employee must be provided"

    )
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return new ResponseEntity<String>(employeeService.deleteEmployee(id), HttpStatus.ACCEPTED);
    }

    @Operation(
            description = "Delete an Employee by their \"name\"",
            summary = "This endpoint is used to delete an Employee from the database. The \"name\" of the Employee must be provided"

    )
    @DeleteMapping("/employee")
    public ResponseEntity<String> deleteEmployeeByName(@RequestParam String name) {
        return new ResponseEntity<String>(employeeService.deleteEmployeeByName(name) + " No. of records deleted", HttpStatus.ACCEPTED);
    }
}
