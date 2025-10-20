package com.example.helloworld.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.helloworld.entity.UserForMybatisPlus;

@Mapper
public interface UserMapper extends BaseMapper<UserForMybatisPlus> {
    // Mybatisplus会自动根据UserForMybatisPlus类找到数据库中的表，类的名字和表的名字保持一致。
    // 不保持一致的话,需要在UserMapper接口上添加@TableName("user")注解,指定表名。
}
