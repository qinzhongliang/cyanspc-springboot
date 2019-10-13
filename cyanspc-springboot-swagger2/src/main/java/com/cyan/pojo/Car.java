package com.cyan.pojo;

import java.io.Serializable;

public class Car implements Serializable {

    private static final long serialVersionUID = -8662270293054538259L;

    private String name;

    public Car(){}

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                '}';
    }
}
