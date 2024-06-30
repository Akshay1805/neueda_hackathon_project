package com.BankApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/friend_circle")
public class FriendCircleController {

    @PostMapping("/add")
    String addToFriendsCircle(String userID,String groupID,String newUserID){ //add to a friend circle

        return "sgb";
    }

    @PostMapping("/create")
    void createFriendsCircle(String ownerUserID, String[] memUserIDs){ //create new friend circle

    }

    @GetMapping("/list")
    void listFriendsCircle(String userID){ // get friends circle he is member of

    }

    @GetMapping("/list-member")
    void listMemFriendsCircle(String userID,String groupID){ // get members of friends circle

    }

    @PostMapping("/remove")
    void removeFriendsCircle(String userID,String groupID,String removeUserID){ // remove a user from group

    }
    @PostMapping("/delete")
    void deleteFriendsCircle(String userID,String groupID){ //delete a gropu

    }

    @PostMapping("/leave")
    void leaveFriendsCircle(String userID,String groupID){ //leave a circle

    }
}
