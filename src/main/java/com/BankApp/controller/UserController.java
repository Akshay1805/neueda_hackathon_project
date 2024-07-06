package com.BankApp.controller;



import com.BankApp.request.VerifyUserRequest;
import com.BankApp.response.UserResponse;
import com.BankApp.entity.User;
import com.BankApp.request.CreateUserRequest;
import com.BankApp.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        User user = commonService.createUserProfile(createUserRequest);
        // if user == null => user couldn't be created
        // if user != null  => user is created successfully
        return user!=null;
    }

    @Operation(summary = "verify a  User")
    /*
    {
        "first_name": "Krishna",
        "last_name": "Kumar",
        "email": "kris@gmail.com",
        "upi_id": "kris@icici"
    }
    */
    @PostMapping("/verify")
    public boolean verifyUser(@RequestBody VerifyUserRequest verifyUserRequest){

        return commonService.verifyUser(verifyUserRequest);
    }

    @Operation(summary = "Get list of users")
    /*
    Data received will in the below format
    [
        {
            "user_id": 7,
            "first_name": "Akshay",
            "last_name": "Perison",
            "email": "akshay@gmail.com",
            "upi_id": "akshay@icici"
        },
        {
            "user_id": 8,
            "first_name": "Krishna",
            "last_name": "Kumar",
            "email": "krishna@gmail.com",
            "upi_id": "krishna@icici"
        },
        {
            "user_id": 9,
            "first_name": "Simran",
            "last_name": "Desai",
            "email": "simran@gmail.com",
            "upi_id": "simran@icici"
        }
    ]
    */
    @GetMapping("/list")
    public List<UserResponse> getAllUserList() {
        List<User> userList = commonService.getAllUsers();
        List<UserResponse> userResponseList = new ArrayList<>();
        userList.forEach(user -> {
            userResponseList.add(new UserResponse(user));
        });
        return userResponseList;
    }

    @Operation(summary = "Get user by id")
    /*
    Data received will in the below format
    {
        "user_id": 9,
        "first_name": "Simran",
        "last_name": "Desai",
        "email": "simran@gmail.com",
        "upi_id": "simran@icici"
    }

    */
    @GetMapping("/id/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        Optional<User> optionalUser = commonService.getUserById(id);
        return new UserResponse(optionalUser.get());
    }

    @Operation(summary = "List Members of Friend Circle", description = "Lists all members of a specified Friend Circle.Returns List of UserIDs")
    /*
    [
        {
            "user_id": 7,
            "first_name": "Akshay",
            "last_name": "Perison",
            "email": "akshay@gmail.com",
            "upi_id": "akshay@icici"
        },
        {
            "user_id": 8,
            "first_name": "Krishna",
            "last_name": "Kumar",
            "email": "krishna@gmail.com",
            "upi_id": "krishna@icici"
        },
        {
            "user_id": 9,
            "first_name": "Simran",
            "last_name": "Desai",
            "email": "simran@gmail.com",
            "upi_id": "simran@icici"
        }
    ]
     */
    @GetMapping("/list-members-of-circle/{id}")
    public List<UserResponse> listMembersOfFriendsCircle(@PathVariable long id) {
        List<User> userList = commonService.getAllUsersWhoArePartOfThisCircle(id);
        List<UserResponse> userResponseList = new ArrayList<>();
        userList.forEach(user -> {
            userResponseList.add(new UserResponse(user));
        });
        return userResponseList;
    }
}
