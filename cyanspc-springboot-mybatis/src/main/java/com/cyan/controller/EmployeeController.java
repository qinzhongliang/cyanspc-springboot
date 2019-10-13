package com.cyan.controller;

import com.cyan.mapper.EmployeeMapper;
import com.cyan.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "employee")
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;

    @RequestMapping(value = "findOne/{id}")
    public Employee findOne(@PathVariable("id")Integer id) {
        return employeeMapper.findOne(id);
    }

    @RequestMapping(value = "list")
    public List<Employee> list() {
        return employeeMapper.list();
    }

    @RequestMapping(value = "save")
    public int save(Employee employee){
        employeeMapper.save(employee);
        return employee.getId();
    }
}
