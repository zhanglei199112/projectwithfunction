package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

  User Sel(int id);

  void add(User user);
}
