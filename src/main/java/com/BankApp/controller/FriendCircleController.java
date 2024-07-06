package com.BankApp.controller;

import com.BankApp.entity.CircleRelation;
import com.BankApp.entity.FriendCircle;
import com.BankApp.request.AddUserToCircleRequest;
import com.BankApp.request.CreateFriendCircleRequest;
import com.BankApp.response.FriendCircleResponse;
import com.BankApp.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        "admin_user_id": "7",
        "circle_name": "Goa Trip",
        "circle_category": "Trip"
    }
    */
    @PostMapping("/create")
    public boolean createFriendsCircle(@RequestBody CreateFriendCircleRequest createFriendCircleRequest) {
        CircleRelation circleRelation = commonService.createFriendCircle(createFriendCircleRequest);
        return circleRelation != null;
    }

    @Operation(summary = "List Friend Circles", description = "Lists all Friend Circles that the specified user is a member of.Return List of groupIDs ")
    /*
    [
        {
            "friend_circle_id": 5,
            "circle_name": "Hukka Party",
            "circle_category": "Party"
        },
        {
            "friend_circle_id": 6,
            "circle_name": "Birthday Party",
            "circle_category": "Party"
        },
        {
            "friend_circle_id": 7,
            "circle_name": "Gokarna Trip",
            "circle_category": "Trip"
        },
        {
            "friend_circle_id": 8,
            "circle_name": "Goa Trip",
            "circle_category": "Trip"
        }
    ]
    */
    @GetMapping("/list")
    public List<FriendCircleResponse> listFriendsCircle() {
        List<FriendCircle> friendCircleList = commonService.getAllFriendCircles();
        List<FriendCircleResponse> friendCircleResponses = new ArrayList<>();
        friendCircleList.forEach(friendCircle -> {
            friendCircleResponses.add(new FriendCircleResponse(friendCircle));
        });
        return friendCircleResponses;
    }

    @Operation(summary = "Get Friend Circle Details", description = "Lists all Details of the specific friend circle. ")
    /*
    {
        "friend_circle_id": 5,
        "circle_name": "Hukka Party",
        "circle_category": "Party"
    }
    */
    @GetMapping("/id/{id}")
    public FriendCircleResponse listFriendCircleDetails(@PathVariable Long id) {
        FriendCircle friendCircle = commonService.getFriendCircleById(id).get();
        return new FriendCircleResponse(friendCircle);
    }

    @Operation(summary = "list all  Friends Circle the User is part of")
    /*
    [
        {
            "friend_circle_id": 5,
            "circle_name": "Hukka Party",
            "circle_category": "Party"
        },
        {
            "friend_circle_id": 8,
            "circle_name": "Goa Trip",
            "circle_category": "Trip"
        }
    ]
     */
    @GetMapping("/list-friend-circle-of-user/{id}")
    public List<FriendCircleResponse> ListFriendCircleOfWhichCertainUserIsPartOf(@PathVariable long id) {
        List<FriendCircle> friendCircleList = commonService.getAllFriendCircleOfWhichYouArePartOf(id);
        List<FriendCircleResponse> friendCircleResponses = new ArrayList<>();
        friendCircleList.forEach(friendCircle -> {
            friendCircleResponses.add(new FriendCircleResponse(friendCircle));
        });
        return friendCircleResponses;
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
