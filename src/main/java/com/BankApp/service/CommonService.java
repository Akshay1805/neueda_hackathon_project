package com.BankApp.service;

import com.BankApp.entity.CircleRelation;
import com.BankApp.entity.FriendCircle;
import com.BankApp.entity.User;
import com.BankApp.repository.CircleRelationRepository;
import com.BankApp.repository.FriendCircleRepository;
import com.BankApp.repository.UserRepository;
import com.BankApp.request.AddUserToCircleRequest;
import com.BankApp.request.CreateFriendCircleRequest;
import com.BankApp.request.CreateUserRequest;
import com.BankApp.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class CommonService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FriendCircleRepository friendCircleRepository;
    @Autowired
    CircleRelationRepository circleRelationRepository;

    public User createUserProfile(CreateUserRequest createUserRequest) {
        User user = new User(createUserRequest);
        user = userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public List<FriendCircle> getAllUsersWhoArePartOfThisCircle(List<Long> userIdsList) {
//        List<Long> firendCircleIdsList = userIdsList.findFriendCircleIdsByUserId(userIdsList);
        return new ArrayList<>();
    }

    public CircleRelation createFriendCircle(CreateFriendCircleRequest createFriendCircleRequest) {
        // date set
        Date date = new Date();
        createFriendCircleRequest.setCreatedOn(date);
        // friend circle saving
        FriendCircle friendCircle = new FriendCircle(createFriendCircleRequest);
        friendCircle = friendCircleRepository.save(friendCircle);

        CircleRelation circleRelation = new CircleRelation();
        circleRelation.setCircleId(friendCircle.getCircleId());
        circleRelation.setUserId(createFriendCircleRequest.getAdminUserId());
        circleRelation.setFriendCircle(friendCircle);
        User user = getUserById(createFriendCircleRequest.getAdminUserId()).get();
        circleRelation.setUser(user);
        // combinedId = circleId + adminUserId
        circleRelation.setUserCircleCombinedId(friendCircle.getCircleId()+createFriendCircleRequest.getAdminUserId());
        circleRelation = circleRelationRepository.save(circleRelation);
        return circleRelation;
    }

    public CircleRelation addUserToFriendCircle(AddUserToCircleRequest addUserToCircleRequest) {
        CircleRelation circleRelation = new CircleRelation();
        circleRelation.setCircleId(addUserToCircleRequest.getFriendCircleId());
        circleRelation.setUserId(addUserToCircleRequest.getNewUserId());
        circleRelation.setUserCircleCombinedId(addUserToCircleRequest.getFriendCircleId()+addUserToCircleRequest.getNewUserId());
        FriendCircle friendCircle = getFriendCircleById(circleRelation.getCircleId()).get();
        User user = getUserById(circleRelation.getUserId()).get();
        circleRelation.setUser(user);
        circleRelation.setFriendCircle(friendCircle);
        circleRelation = circleRelationRepository.save(circleRelation);
        return circleRelation;
    }

    public List<FriendCircle> getAllFriendCircles() {
        return friendCircleRepository.findAll();
    }

    public Optional<FriendCircle> getFriendCircleById(long id) {
        return friendCircleRepository.findById(id);
    }


    public List<User> getAllUsersWhoArePartOfThisCircle(long circle_id) {
        return userRepository.findUserByCircleId(circle_id);
    }

    public List<FriendCircle> getAllFriendCircleOfWhichYouArePartOf(long user_id) {
        return friendCircleRepository.findCirclesByUserId(user_id);
    }
}
