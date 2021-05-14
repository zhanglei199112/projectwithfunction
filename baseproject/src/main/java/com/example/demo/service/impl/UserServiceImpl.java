package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public User Sel(int id) {
    return userMapper.selectByPrimaryKey(id);
  }

  @Override
  public void add(User user) {
    userMapper.insert(user);
  }

  @Override
  public void batchUpdate(List<User> users) {
    userMapper.batchUpdateMapper(users);
  }

  @Override
  public void update(User user) {
    userMapper.updateByPrimaryKeySelective(user);
  }
}
