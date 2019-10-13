package com.cyan.controller;

import com.cyan.pojo.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @RequestMapping(value = "testQksJsp")
    public String testQksJsp(){
        return "hello jsp";
    }

    @RequestMapping(value = "user/addUser")
    public User addUser(HttpServletRequest request, Model model, User user){
        System.out.println(user);
        return user;
    }

}
