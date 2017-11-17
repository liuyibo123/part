package com.liuyibo.part.controller;

import com.liuyibo.part.dao.UserRepository;
import com.liuyibo.part.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    User user;
    @Autowired
    UserRepository repository;
    @RequestMapping("/validate")
    public @ResponseBody String validate(String username, String password){
        user = repository.findOne(username);
        if(user.getPassword().equals(password)){
            System.out.println(1);
            return "1";
        }
        else{
            System.out.println(2 );
            return "2";
        }
    }
}
