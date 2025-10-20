package com.example.helloworld.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.helloworld.entity.User;

@RestController
public class HelloController {
    /*常用Get请求示例*/
    
    // 请求路径，GET方法，无参数
    // http://192.168.67.128:8089/hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello World!";
    }

    // 请求路径，GET方法，查询字符串参数（多参数）
    // http://192.168.67.128:8089/getUserName?name=Jack&age=25
    @RequestMapping(value = "/getUserName", method = RequestMethod.GET)
    public String getUserName(String name, Integer age) {
        // 方法参数就是前端通过查询字符串传递的参数
        return "My name is " + name + " and I am " + age + " years old.";
    }

    // 参数映射，当前端传递参数和方法参数不一致，
    // 设置参数映射，required=false表示参数可选，否则必须传递,不然会报错
    // nickname映射为name
    // http://192.168.67.128:8089/getUserName2?nickname=Jack
    @RequestMapping(value = "/getUserName2", method = RequestMethod.GET)
    public String getUserName2(@RequestParam(value = "nickname", required = false) String name) {
        // 使用注解的方式，参数映射到方法参数
        return "My name is " + name;
    }


     /*常用Post请求示例*/

     // POST方法，无参数
     @RequestMapping(value = "/postTest", method=RequestMethod.POST)
     public String postTest() {
         return "Post请求响应，无参数";
     }

     // POST方法，请求体参数数据格式为x-www-form-urlencoded
     // Post方法参数放在请求体中，postman中body类型选择x-www-form-urlencoded,对应参数会被编码,表单类型
     @RequestMapping(value = "/postTest2", method=RequestMethod.POST)
     public String postTest2(String username, String password) {
         return "Post请求响应，请求体参数：" + username + " " + password;
     }

     // (不常用)POST方法，请求体参数数据格式为表单类型，post参数的形式为x-www-form-urlencoded
     @RequestMapping(value = "/postTest3", method=RequestMethod.POST)
     public User postTest3(User user) {
        System.out.println(user);
         return user;
     }

     //(常用的post请求)前端请求参数过多：注册功能，用类来封装接受参数,post参数的形式为json格式
     /*
     {"username": "jack","password": "123"}
    */
     // 函数为自定义类型 User，参数为@RequestBody注解，接收请求体中的json数据
     // 参数传递过来，参数名和参数类型要和User类中的属性名和类型一致
     @RequestMapping(value = "/postTest4", method=RequestMethod.POST)
     public User postTest4(@RequestBody User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getAge());
         return user;
     }
     
     
}
