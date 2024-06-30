package com.BankApp.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
public class UserController
{
    @PostMapping("/create")
    boolean createUser(String userID,String FirstName,String LastName,String Email,String UpiID){

        return true;
    }

    @PostMapping("/verify")
    boolean verifyUser(String userID,String Password){
        return true;
    }
}
