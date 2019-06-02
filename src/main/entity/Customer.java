package main.entity;

import java.util.List;

public class Customer {
    private Integer age;
    private String name;
    private String surname;
    private List<String> contact;


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<String> getContact() {
        return contact;
    }

    public void setContact(List<String> contact) {
        this.contact = contact;
    }


    public void addContact(String item) {
        contact.add(item);
    }
}
