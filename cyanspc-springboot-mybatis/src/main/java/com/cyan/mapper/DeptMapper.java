package com.cyan.mapper;

import com.cyan.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
public interface DeptMapper {

    Dept findOne(Integer id);

    List<Dept> list();

    Integer save(Dept dept);
}
