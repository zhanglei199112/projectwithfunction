package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

public interface UserService {

  User Sel(int id);

  void add(User user);

  void batchUpdate(List<User> users);

  void update(User user);
}
