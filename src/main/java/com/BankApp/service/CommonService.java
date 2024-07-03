package com.BankApp.service;

import com.BankApp.entity.*;
import com.BankApp.repository.CircleRelationRepository;
import com.BankApp.repository.FriendCircleRepository;
import com.BankApp.repository.SettlementRepository;
import com.BankApp.repository.UserRepository;
import com.BankApp.request.AddUserToCircleRequest;
import com.BankApp.request.CreateFriendCircleRequest;
import com.BankApp.request.CreateTransactionRequest;
import com.BankApp.request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class CommonService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FriendCircleRepository friendCircleRepository;
    @Autowired
    CircleRelationRepository circleRelationRepository;
    @Autowired
    SettlementRepository settlementRepository;

    public User createUserProfile(CreateUserRequest createUserRequest) {
        int n = getAllUsers().size();
        User user = new User(createUserRequest);
        user.setUserId(n+1);
        user = userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public int getSizeOfCircleRelation() {
        return circleRelationRepository.findAll().size();
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
        int m = getAllFriendCircles().size();
        FriendCircle friendCircle = new FriendCircle(createFriendCircleRequest);
        friendCircle.setCircleId(m+1);
        friendCircle = friendCircleRepository.save(friendCircle);

        int n = getSizeOfCircleRelation();
        CircleRelation circleRelation = new CircleRelation();
        circleRelation.setUserCircleCombinedId(n+1);
        circleRelation.setCircleId(friendCircle.getCircleId());
        circleRelation.setUserId(createFriendCircleRequest.getAdminUserId());
        circleRelation.setFriendCircle(friendCircle);
        User user = getUserById(createFriendCircleRequest.getAdminUserId()).get();
        circleRelation.setUser(user);
        circleRelation = circleRelationRepository.save(circleRelation);
        return circleRelation;
    }

    public CircleRelation addUserToFriendCircle(AddUserToCircleRequest addUserToCircleRequest) {

        // creating the settlement of new user with previous users
        addSettlementsToCircle(addUserToCircleRequest.getFriendCircleId(), addUserToCircleRequest.getNewUserId());
        // creating the circleRelation of new user
        int n = getSizeOfCircleRelation();
        CircleRelation circleRelation = new CircleRelation();

        circleRelation.setCircleId(addUserToCircleRequest.getFriendCircleId());
        circleRelation.setUserId(addUserToCircleRequest.getNewUserId());
        circleRelation.setUserCircleCombinedId(n+1);
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

    public boolean addSettlementsToCircle(Long groupId, Long userId) {
        List<User> previousUsersList = getAllUsersWhoArePartOfThisCircle(groupId);
        AtomicBoolean isAllUserAdded = new AtomicBoolean(true);
        int n = getSettlementTableSize();
        AtomicLong index = new AtomicLong(1);
        previousUsersList.forEach(user -> {
            Settlement settlement = new Settlement();
            settlement.setSettlementId(n+ index.get());
            index.getAndIncrement();
            settlement.setGroupId(groupId);
            settlement.setXBalance(0);
            settlement.setYBalance(0);
            settlement.setUserIdOfX(user.getUserId());
            settlement.setUserIdOfY(userId);
            if(user.getUserId()!=userId)  settlement = addSettlements(settlement);
            if(user.getUserId()!= userId && settlement.getSettlementId()!=0) isAllUserAdded.set(false);
        });
        return isAllUserAdded.get();
    }

    public Transaction createTransaction(CreateTransactionRequest createTransactionRequest) {
        // get all the settlement where paid user is involved
        long payerUserId = createTransactionRequest.getUserIdOfPayer();
        HashMap<Long, Double> distributionMap = createTransactionRequest.getDistributionList();
        List<Settlement> settlements = settlementRepository.findAllByUserIdOfXOrUserIdOfY(payerUserId, payerUserId);
        settlements.forEach(settlement -> {
            long ux = settlement.getUserIdOfX();
            long uy = settlement.getUserIdOfY();
            if(ux == payerUserId) {
                Double amount = distributionMap.get(uy);
                settlement.setXBalance(settlement.getXBalance()+amount);
                settlement.setYBalance(settlement.getYBalance()-amount);
                addSettlements(settlement);
            } else {
                Double amount = distributionMap.get(ux);
                settlement.setXBalance(settlement.getXBalance()-amount);
                settlement.setYBalance(settlement.getYBalance()+amount);
                addSettlements(settlement);
            }
        });
        return new Transaction();
    }
    public Settlement addSettlements(Settlement settlement) {;
        return settlementRepository.save(settlement);
    }

    public int getSettlementTableSize() {
        return settlementRepository.findAll().size();
    }
}
