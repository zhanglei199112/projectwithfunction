package com.example.demo.controller;

import com.example.demo.baseentity.RestResult;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  @RequestMapping("getUser/{id}")
  public String GetUser(@PathVariable int id){
    return userService.sel(id).toString();
  }

  @GetMapping("add")
  public RestResult addUser(){
    User user = new User();
    user.setAge(18);
    user.setName("关雅琪");
    userService.add(user);
    return RestResult.ok();
  }

}
