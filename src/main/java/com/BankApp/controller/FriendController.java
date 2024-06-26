package com.BankApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/friend")
public class FriendController {

    @PostMapping("/add")
    void addFriends(){

    }

    @GetMapping("/get")
    void getFriends(){  // optional arguments . If argument present na fetch particular else return all

    }

    @PostMapping("/remove")
    void removeFriends(){

    }
}
