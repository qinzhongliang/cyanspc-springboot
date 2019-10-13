package com.cyan.controller;

import com.cyan.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties(Person.class)
public class PersonController {

    @Autowired
    private Person person;

    @RequestMapping(value = "getPersonInfo")
    public Person getPersonInfo() {
        return person;
    }
}
