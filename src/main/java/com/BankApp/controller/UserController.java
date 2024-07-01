package com.BankApp.controller;



import com.BankApp.entity.User;
import com.BankApp.request.CreateUserRequest;
import com.BankApp.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController
{
    @Autowired
    CommonService commonService;

    @Operation(summary = "Create a new User")
    /*
    {
        "first_name": "Krishna",
        "last_name": "Kumar",
        "email": "kris@gmail.com",
        "upi_id": "kris@icici"
    }
    */
    @PostMapping("/create")
    public boolean createUser(@RequestBody CreateUserRequest createUserRequest){
        System.out.println(new Date());
        User user = commonService.createUserProfile(createUserRequest);
        // if user == null => user couldn't be created
        // if user != null  => user is created successfully
        return user!=null;
    }

    @Operation(summary = "Create a new User")
    /*
    {
        "first_name": "Krishna",
        "last_name": "Kumar",
        "email": "kris@gmail.com",
        "upi_id": "kris@icici"
    }
    */
    @GetMapping("/list")
    public List<User> createUser(){
//        System.out.println(new Date());
//        List<User> userList = commonService.createUserProfile(createUserRequest);
        // if user == null => user couldn't be created
        // if user != null  => user is created successfully
//        return userList;
        return new ArrayList<>();
    }

//    @Operation(summary = "Verify a User")
//    @PostMapping("/verify")
//    boolean verifyUser(String userID,String Password){
//        return true;
//    }
}
