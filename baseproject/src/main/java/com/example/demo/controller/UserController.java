package com.example.demo.controller;

import com.example.demo.baseentity.RestResult;
import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  @RequestMapping("getUser/{id}")
  public String GetUser(@PathVariable int id){
    return userService.Sel(id).toString();
  }

  @GetMapping("add")
  public RestResult addUser(){
    User user = new User();
    user.setAge(18);
    user.setName("关雅琪");
    userService.add(user);
    return RestResult.ok();
  }

  @GetMapping("batchUpdate")
  public RestResult batchUpdate(){
    List<User> users= new ArrayList<User>();
    User user = new User();
    user.setAge(20);
    user.setName("关雅琪");
    user.setId(1l);
    User user1 = new User();
    user1.setAge(20);
    user1.setName("张磊");
    user1.setId(2l);
    users.add(user);
    users.add(user1);
    userService.batchUpdate(users);
    return RestResult.ok();
  }

  @PutMapping("update")
  public RestResult update(){
    List<User> users= new ArrayList<User>();
    User user = new User();
    user.setAge(20);
    user.setName("关雅琪");
    user.setId(1l);
    Address address = new Address();
    address.setCity("武汉");
    address.setProvince("湖北");
    address.setStreet("关东街道");
    user.setAddress(address);
    userService.update(user);
    return RestResult.ok();
  }

}
