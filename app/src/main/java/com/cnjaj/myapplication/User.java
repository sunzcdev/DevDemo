package com.cnjaj.myapplication;

/**
 * Created by Administrator on 2016/11/1.
 */
public class User {
    private String name;
    private int age;
    private String token;
User(){}
    public User(String name, int age, String token) {
        this.name = name;
        this.age = age;
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", token='" + token + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
