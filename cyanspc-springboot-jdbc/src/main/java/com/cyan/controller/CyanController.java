package com.cyan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CyanController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "cyanHello")
    public List<Map<String,Object>> cyanHello(){
        List<Map<String,Object>> employeeList = jdbcTemplate.queryForList("select * from employee");
        return employeeList;
    }














}
