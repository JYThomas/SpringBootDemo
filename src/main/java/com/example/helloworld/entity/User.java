package com.example.helloworld.entity;


public class User {
    private String username;
    private String password;
    private Integer age;

    public String getUsername() {
        return username;
    }
    
    // set后面的方法名部分，要是对应实体属性的首字母大写的形式
    // 比如类属性为username,那么传递过来的参数也得是username，
    // 然后set方法的命名为setUsername,Username为属性username的首字母大写
    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
