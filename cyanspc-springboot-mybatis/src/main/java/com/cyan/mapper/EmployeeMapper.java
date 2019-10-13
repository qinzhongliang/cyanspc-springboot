package com.cyan.mapper;

import com.cyan.pojo.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper
public interface EmployeeMapper {

    @Select("select * from employee")
    List<Employee> list();

    @Select("select * from employee where id = #{id}")
    Employee findOne(Integer id);

    @Insert("insert into employee(last_name,email,gender,dept_id)values(#{lastName},#{email},#{gender},#{deptId})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer save(Employee employee);

}
