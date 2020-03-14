package com.example.sqlitedatabase;

public class ModelClass {
    String id;


    private String name;
    private String age;
    private String phone;

    public ModelClass(){

    }

    public ModelClass(String name, String age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }
    public ModelClass(String id, String name, String age, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
