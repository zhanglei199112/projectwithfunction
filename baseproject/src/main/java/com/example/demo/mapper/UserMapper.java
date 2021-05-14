package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends MyBatchUpdateMapper<User>, Mapper<User> {

  User findById(@Param("id") int id);
}