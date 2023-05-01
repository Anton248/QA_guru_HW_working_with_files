package ru.nikolski;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect
public class Human {

    private int age;
    private String name;
    private String surname;
    private List<Human> children;

    public Human() {}

    public Human(int age, String name, String surname) {
        this.age = age;
        this.name = name;
        this.surname = surname;
    }

    public void addChild(Human child) {
        if (children == null) {children = new ArrayList<>();}
        children.add(child);
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Human> getChildren() {
        return children;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setChildren(List<Human> children) {
        this.children = children;
    }
}
