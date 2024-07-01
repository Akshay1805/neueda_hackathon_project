package com.BankApp.controller;

import com.BankApp.entity.CircleRelation;
import com.BankApp.entity.FriendCircle;
import com.BankApp.request.AddUserToCircleRequest;
import com.BankApp.request.CreateFriendCircleRequest;
import com.BankApp.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/friend_circle/")
public class FriendCircleController {

    @Autowired
    CommonService commonService;

    @Operation(summary = "Add User to Friend Circle", description = "Adds a new user to an existing Friend Circle.Returns msg")
    /*
    {
        "admin_user_id": 6,
        "friend_circle_id": 4,
        "new_user_id": 5
    }
    */
    @PostMapping("/add")
    public boolean addToFriendsCircle(@RequestBody AddUserToCircleRequest addUserToCircleRequest) {
        CircleRelation circleRelation = commonService.addUserToFriendCircle(addUserToCircleRequest);
        return circleRelation != null;
    }

    @Operation(summary = "Create New Friend Circle", description = "Creates a new Friend Circle with the specified owner and members.Returns groupID")
    /*
    {
        "admin_user_id": "5",
        "circle_name": "Lonawala Trip",
        "circle_category": "Trip",
    }
    */
    @PostMapping("/create")
    public boolean createFriendsCircle(@RequestBody CreateFriendCircleRequest createFriendCircleRequest) {
        CircleRelation circleRelation = commonService.createFriendCircle(createFriendCircleRequest);
        return circleRelation != null;
    }

    @Operation(summary = "List Friend Circles", description = "Lists all Friend Circles that the specified user is a member of.Return List of groupIDs ")
    @GetMapping("/list")
    public List<FriendCircle> listFriendsCircle(String userID) {
        return commonService.getAllFriendCircles();
    }

    @Operation(summary = "List Members of Friend Circle", description = "Lists all members of a specified Friend Circle.Returns List of UserIDs")
    @GetMapping("/list-member")
    public void listMemFriendsCircle(String userID, String groupID) {
    }

    @Operation(summary = "Remove User from Friend Circle", description = "Removes a user from a specified Friend Circle.Returns msg")
    @PostMapping("/remove")
    public void removeFriendsCircle(String userID, String groupID, String removeUserID) {
    }

    @Operation(summary = "Delete Friend Circle", description = "Deletes a specified Friend Circle.Returns msg")
    @PostMapping("/delete")
    public void deleteFriendsCircle(String userID, String groupID) {
    }

    @Operation(summary = "Leave Friend Circle", description = "Allows a user to leave a specified Friend Circle.Returns msg")
    @PostMapping("/leave")
    public void leaveFriendsCircle(String userID, String groupID) {
    }
}
