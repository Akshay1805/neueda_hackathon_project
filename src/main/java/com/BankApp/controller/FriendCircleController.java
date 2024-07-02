package com.BankApp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend_circle")
public class FriendCircleController {

    @Operation(summary = "Add User to Friend Circle", description = "Adds a new user to an existing Friend Circle.Returns msg")
    @PostMapping("/add")
    public String addToFriendsCircle(String userID, String groupID, String newUserID) {
        return "User " + newUserID + " added to Friend Circle " + groupID + " of user " + userID;
    }

    @Operation(summary = "Create New Friend Circle", description = "Creates a new Friend Circle with the specified owner and members.Returns groupID")
    @PostMapping("/create")
    public void createFriendsCircle(String ownerUserID, @ArraySchema(schema = @Schema(type = "string")) String[] memUserIDs) {
    }

    @Operation(summary = "List Friend Circles", description = "Lists all Friend Circles that the specified user is a member of.Return List of groupIDs ")
    @GetMapping("/list")
    public void listFriendsCircle(String userID) {
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
