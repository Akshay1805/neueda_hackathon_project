package com.BankApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendController {

    @PostMapping("/friend")
    void addFriends(){

    }

    @GetMapping("/friend")
    void getFriends(){  // optional arguments . If argument present na fetch particular else return all

    }

    @PostMapping("/friend")
    void removeFriends(){

    }
}
