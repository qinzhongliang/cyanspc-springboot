package com.cyan.controller;

import com.cyan.mapper.DeptMapper;
import com.cyan.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "dept")
public class DepartmentController {

    @Autowired
    private DeptMapper deptMapper;

    @RequestMapping(value = "findOne/{id}")
    public Dept findOne(@PathVariable("id") Integer id){
        return deptMapper.findOne(id);
    }

    @RequestMapping(value = "list")
    public List<Dept> list() {
        return deptMapper.list();
    }

    @RequestMapping(value = "save")
    public int save(Dept dept) {
        deptMapper.save(dept);
        return dept.getId();
    }
}
