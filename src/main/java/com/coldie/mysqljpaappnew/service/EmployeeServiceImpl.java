package com.coldie.mysqljpaappnew.service;

import com.coldie.mysqljpaappnew.model.Employee;
import com.coldie.mysqljpaappnew.repository.EmployeeRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Page<Employee> getEmployees(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());
        return  employeeRepository.findAll(pageable);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee with id " + id + " not found.");
        } else {
            return optionalEmployee.get();
        }
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return employeeRepository.findByNameContaining(name, sort);
    }

    @Override
    public List<Employee> getEmployeesByNameAndLocation(String name, String location) {
        return employeeRepository.findByNameContainingAndLocationContaining(name, location);
    }

    @Override
    public List<Employee> getEmployeesByNameOrLocation(String name, String location) {
        return employeeRepository.findEmployeesByNameOrLocation(name, location);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if (employee.getId() == null) {
            throw new IllegalArgumentException("Employee ID cannot be null");
        }

        Long id = employee.getId();
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee with ID " + id + " not found"));

        if (Objects.nonNull(employee.getName())) {
            existingEmployee.setName(employee.getName());
        }
        if (Objects.nonNull(employee.getAge())) {
            existingEmployee.setAge(employee.getAge());
        }
        if (StringUtils.isNotBlank(employee.getLocation())) {
            existingEmployee.setLocation(employee.getLocation());
        }
        if (StringUtils.isNotBlank(employee.getEmail())) {
            existingEmployee.setEmail(employee.getEmail());
        }
        if (StringUtils.isNotBlank(employee.getDepartment())) {
            existingEmployee.setDepartment(employee.getDepartment());
        }

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public String deleteEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            employeeRepository.deleteById(id);
            return "Deleted Employee with id - " + id;
        } else {
            return "Employee with id - " + id + " does not exist";
        }
    }

    @Override
    public Integer deleteEmployeeByName(String name) {
        return employeeRepository.deleteEmployeeByName(name);
    }
}
