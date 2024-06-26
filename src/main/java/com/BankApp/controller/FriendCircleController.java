package com.BankApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/friend_circle")
public class FriendCircleController {

    @PostMapping("/add")
    void addFriendsCircle(){ //add to a group

    }

    @PostMapping("/create")
    void createFriendsCircle(){ //create new group

    }

    @GetMapping("/get")
    void getFriendsCircle(){ // get what he is member of

    }

    @PostMapping("/remove")
    void removeFriendsCircle(){ // remove from group

    }
    @PostMapping("/delete")
    void deleteFriendsCircle(){ //delete a gropu

    }
}
