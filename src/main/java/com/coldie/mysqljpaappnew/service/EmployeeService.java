package com.coldie.mysqljpaappnew.service;

import com.coldie.mysqljpaappnew.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    Page<Employee> getEmployees(int pageNumber, int pageSize);

    Employee getEmployeeById(Long id);

    List<Employee> getEmployeesByName(String name);

    List<Employee> getEmployeesByNameAndLocation(String name, String location);

    List<Employee> getEmployeesByNameOrLocation(String name, String Location);

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    String deleteEmployee(Long id);

    Integer deleteEmployeeByName(String name);
}
