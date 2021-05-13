package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.example.demo.mymapper.MyMapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends MyMapper<User> {

  User findById(@Param("id") int id);
}