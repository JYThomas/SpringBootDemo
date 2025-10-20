package com.example.helloworld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.helloworld.entity.UserForMybatisPlus;
import com.example.helloworld.mapper.UserMapper;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class UserController {
    // 注入UserMapper
    @Autowired
    private UserMapper userMapper;

    // 新增用户信息
    @RequestMapping(value = "/user/insertUser", method = RequestMethod.POST)
    public String addUser(@RequestBody UserForMybatisPlus user) {
        // 找Mapper中的方法，新增用户信息
        System.out.println(user.getUsername());
        System.out.println(user.getBirthday());

        int insertCode = userMapper.insert(user);
        if (insertCode > 0) {
            return "新增用户成功！";
        } else {
            return "新增用户失败！";
        }
    }

    // 修改用户信息
    @RequestMapping(value = "/user/updateUserById", method = RequestMethod.POST)
    public String updateUser(@RequestBody UserForMybatisPlus user) {
        // 找Mapper中的方法，修改用户信息
        int updateCode = userMapper.updateById(user);
        if (updateCode > 0) {
            return "修改用户成功！";
        } else {
            return "修改用户失败！";
        }
    }

    // 查询用户信息列表
    @RequestMapping(value = "/user/queryUserList", method = RequestMethod.GET)
    public List<UserForMybatisPlus> queryUser() {
        // 找Mapper中的方法，查询用户信息列表
        List<UserForMybatisPlus> userList = userMapper.selectList(null);
        return userList;
    }

    // 删除用户信息
    @RequestMapping(value = "/user/deleteUserById", method = RequestMethod.POST)
    public String deleteUser(@RequestBody UserForMybatisPlus user) {
        // 找Mapper中的方法，删除用户信息
        int deleteCode = userMapper.deleteById(user.getId());
        if (deleteCode > 0) {
            return "删除用户成功！";
        } else {
            return "删除用户失败！";
        }
    }   
    
    // 条件查询
    @RequestMapping(value = "/user/queryUser", method = RequestMethod.POST)
    public List<UserForMybatisPlus> queryUser(@RequestBody UserForMybatisPlus queryCondition) {
        // 1. 创建 LambdaQueryWrapper（针对 UserForMybatisPlus 实体）
        LambdaQueryWrapper<UserForMybatisPlus> queryWrapper = new LambdaQueryWrapper<>();

        // 2. 构造查询条件：前端传入的非空字段作为查询条件（动态拼接）
        // 示例1：若 username 不为空，添加模糊查询（LIKE %username%）
        if (queryCondition.getUsername() != null && !queryCondition.getUsername().isEmpty()) {
            queryWrapper.like(UserForMybatisPlus::getUsername, queryCondition.getUsername());
        }

        // 示例2：若 birthday 不为空，添加精确查询（= birthday）
        if (queryCondition.getBirthday() != null && !queryCondition.getBirthday().isEmpty()) {
            queryWrapper.eq(UserForMybatisPlus::getBirthday, queryCondition.getBirthday());
        }

        // 按 id 降序排序
        queryWrapper.orderByDesc(UserForMybatisPlus::getId); 

        // 将构造的查询条件传入 Mapper 的 selectList 方法中，查询用户信息列表
        List<UserForMybatisPlus> userList = userMapper.selectList(queryWrapper);

        if (userList.isEmpty()) {
            return null;
        }

        return userList;
    }

    // 分页查询用户列表(问题)
    @RequestMapping(value = "/user/queryUserByPage", method = RequestMethod.POST)
    public List<UserForMybatisPlus> queryUserByPage(@RequestBody UserForMybatisPlus queryCondition) {
        // 1. 创建 LambdaQueryWrapper（针对 UserForMybatisPlus 实体）
        LambdaQueryWrapper<UserForMybatisPlus> queryWrapper = new LambdaQueryWrapper<>();

        // 2. 构造查询条件：前端传入的非空字段作为查询条件（动态拼接）
        // 示例1：若 username 不为空，添加模糊查询（LIKE %username%）
        if (queryCondition.getUsername() != null && !queryCondition.getUsername().isEmpty()) {
            queryWrapper.like(UserForMybatisPlus::getUsername, queryCondition.getUsername());
        }

        // 示例2：若 birthday 不为空，添加精确查询（= birthday）
        if (queryCondition.getBirthday() != null && !queryCondition.getBirthday().isEmpty()) {
            queryWrapper.eq(UserForMybatisPlus::getBirthday, queryCondition.getBirthday());
        }

        // 按 id 降序排序
        queryWrapper.orderByDesc(UserForMybatisPlus::getId); 

        // 3. 构造分页查询条件
        // 示例1：每页显示 10 条数据
        queryWrapper.last("LIMIT 3");

        // 4. 将构造的查询条件和分页查询条件传入 Mapper 的 selectPage 方法中，查询用户信息列表
        // 注意：selectPage 方法返回的结果是 Page 对象，需要调用 getRecords 方法获取数据列表
        List<UserForMybatisPlus> userList = userMapper.selectPage(null, queryWrapper).getRecords();

        if (userList.isEmpty()) {
            return null;
        }

        return userList;        
    }

    // 多表查询(问题)

}
