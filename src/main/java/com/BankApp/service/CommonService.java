package com.BankApp.service;

import com.BankApp.entity.*;
import com.BankApp.repository.*;
import com.BankApp.request.*;
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
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ContributionRepository contributionRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

// creating the objects
    public User createUserProfile(CreateUserRequest createUserRequest) {
        User user = new User(createUserRequest);
        user.setUserId(getNextUserId());
//        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setPassword(createUserRequest.getPassword());
        user = userRepository.save(user);
        return user;
    }

    public boolean verifyUser(VerifyUserRequest verifyUserRequest){
        User user = userRepository.findUserByEmail(verifyUserRequest.getEmail());
        if(user!=null){
            return verifyUserRequest.getPassword().equals(user.getPassword());
        }
        return false;
    }

    public boolean removeUserFromCircle(long userID,long circleID){

        int count = circleRelationRepository.deleteByCircleIdAndUserId(circleID,userID);
        if(count>0){
            return true;
        }
        return false;
    }

    public boolean deleteFriendCircle(long circleID){

        int count = circleRelationRepository.deleteByCircleId(circleID);
        if(count>0){
            return true;
        }
        return false;
    }



    public User FetchUserByEmail(String email){
        User user = userRepository.findUserByEmail(email);
        if(user!=null){
            return user;
        }
        return null ;
    }

    public CircleRelation createFriendCircle(CreateFriendCircleRequest createFriendCircleRequest) {
        // date set
        Date date = new Date();
        createFriendCircleRequest.setCreatedOn(date);
        // friend circle saving
        FriendCircle friendCircle = new FriendCircle(createFriendCircleRequest);
        friendCircle.setCircleId(getNextFriendCircleId());
        friendCircle = friendCircleRepository.save(friendCircle);

        CircleRelation circleRelation = new CircleRelation();
        circleRelation.setUserCircleCombinedId(getNextCircleRelationId());
        circleRelation.setCircleId(friendCircle.getCircleId());
        circleRelation.setUserId(createFriendCircleRequest.getAdminUserId());
        circleRelation.setFriendCircle(friendCircle);
        User user = getUserById(createFriendCircleRequest.getAdminUserId()).get();
        circleRelation.setUser(user);
        circleRelation = circleRelationRepository.save(circleRelation);
        return circleRelation;
    }

    public Transaction createTransaction(CreateTransactionRequest createTransactionRequest) {
        // get all the settlement where paid user is involved
        long payerUserId = createTransactionRequest.getUserIdOfPayer();
        HashMap<Long, Double> distributionMap = createTransactionRequest.getDistributionList();
        FriendCircle friendCircle = getFriendCircleById(createTransactionRequest.getGroupId()).get();

        // making the changes in the settlement table
        List<Settlement> settlements = settlementRepository.findAllByUserIdOfXOrUserIdOfY(payerUserId, payerUserId);
        settlements.forEach(settlement -> {
            long ux = settlement.getUserIdOfX();
            long uy = settlement.getUserIdOfY();
            long groupId = settlement.getGroupId();
            if(groupId == createTransactionRequest.getGroupId()) {
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
            }
        });

        // making the changes in the transaction table
        long n = getNextTransactionId();
        Transaction transaction = new Transaction(createTransactionRequest);
        transaction.setTransactionId(n);
        transaction.setFriendCircle(friendCircle);
        Date date = new Date();
        transaction.setPaymentDate(date);
        transaction = transactionRepository.save(transaction);

        // making the changes in the contribution table
        double payment = createTransactionRequest.getPrice();
        Transaction finalTransaction = new Transaction(transaction);
        distributionMap.entrySet().forEach(entry -> {
            long userId = entry.getKey();
            double amount = entry.getValue();
            Contribution contribution = new Contribution();
            contribution.setId(getNextContributionId());
            contribution.setUserId(userId);
            contribution.setContribution(amount);
            if(userId == payerUserId) contribution.setPayment(payment);
            else contribution.setPayment(0.0);
            contribution.setTransaction(finalTransaction);
            contributionRepository.save(contribution);
        });
        return transaction;
    }

    public boolean deleteTransaction(Long transactionId) {
        // Get the transaction with transactionId
        Optional<Transaction> optionalTransaction = getTransactionById(transactionId);
        if(!optionalTransaction.isPresent()) return false;
        Transaction transaction = optionalTransaction.get();

        // Get the contribution list
        HashMap<Long, Double> map = new HashMap<>();
        List<Contribution> contributions = contributionRepository.findByTransactionId(transactionId);
        contributions.stream().forEach(contribution -> {
            map.put(contribution.getUserId(), contribution.getPayment());
        });
        // and even delete those contributions
        contributionRepository.deleteContributionByTransactionId(transactionId);

        // Get the settlement using groupId in transaction
        long groupId = transaction.getGroupId();
        List<Settlement> settlements = getSettlementsByFriendCircleId(groupId);
        // make the changes
        settlements.forEach(settlement -> {
            long ux = settlement.getUserIdOfX();
            long uy = settlement.getUserIdOfY();
            if(ux == transaction.getUserIdOfPayer()) {
                Double amount = map.get(uy);
                settlement.setXBalance(settlement.getXBalance()-amount);
                settlement.setYBalance(settlement.getYBalance()+amount);
                addSettlements(settlement);
            } else {
                Double amount = map.get(ux);
                settlement.setXBalance(settlement.getXBalance()+amount);
                settlement.setYBalance(settlement.getYBalance()-amount);
                addSettlements(settlement);
            }
        });
        // delete transaction by transactionId
        transactionRepository.deleteById(transactionId);
        return false;
    }



// getting all the objects
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<FriendCircle> getAllFriendCircles() {
        return friendCircleRepository.findAll();
    }

    public List<CircleRelation> getAllCircleRelations() {
        return circleRelationRepository.findAll();
    }

    public List<Contribution> getAllContributions() {
        return contributionRepository.findAll();
    }

    public List<Settlement> getAllSettlements() {
        return settlementRepository.findAll();
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

// getting the objects by id
    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public Optional<FriendCircle> getFriendCircleById(long id) {
        return friendCircleRepository.findById(id);
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public List<Settlement> getSettlementsByFriendCircleId(Long id) {
        List<Settlement> settlementList = settlementRepository.findByGroupId(id);
        return settlementList;
    }

// additional Features
    public CircleRelation addUserToFriendCircle(AddUserToCircleRequest addUserToCircleRequest) {

    // creating the settlement of new user with previous users
    addSettlementsToCircle(addUserToCircleRequest.getFriendCircleId(), addUserToCircleRequest.getNewUserId());
    // creating the circleRelation of new user
    long n = getNextCircleRelationId();
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

    public List<User> getAllUsersWhoArePartOfThisCircle(long circle_id) {
        return userRepository.findUserByCircleId(circle_id);
    }

    public List<FriendCircle> getAllFriendCircleOfWhichYouArePartOf(long user_id) {
        return friendCircleRepository.findCirclesByUserId(user_id);
    }

// get Next User Id
    public long getNextUserId() {
        List<User> userList = getAllUsers();
        AtomicLong index = new AtomicLong();
        userList.forEach(user -> {
            long userId = user.getUserId();
            if(userId> index.get()) index.set(userId);
        });
        return index.get()+1;
    }

    public long getNextFriendCircleId() {
        List<FriendCircle> friendCircleList = getAllFriendCircles();
        AtomicLong index = new AtomicLong();
        friendCircleList.forEach(friendCircle -> {
            long friendCircleId = friendCircle.getCircleId();
            if(friendCircleId> index.get()) index.set(friendCircleId);
        });
        return index.get()+1;
    }

    public long getNextCircleRelationId() {
        List<CircleRelation> circleRelationList = getAllCircleRelations();
        AtomicLong index = new AtomicLong();
        circleRelationList.forEach(circleRelation -> {
            long friendCircleId = circleRelation.getUserCircleCombinedId();
            if(friendCircleId> index.get()) index.set(friendCircleId);
        });
        return index.get()+1;
    }

    public long getNextTransactionId() {
        List<Transaction> transactionList = getAllTransactions();
        AtomicLong index = new AtomicLong();
        transactionList.forEach(transaction -> {
            long transactionId = transaction.getTransactionId();
            if(transactionId> index.get()) index.set(transactionId);
        });
        return index.get()+1;
    }

    public long getNextSettlementId() {
        List<Settlement> settlementList = getAllSettlements();
        AtomicLong index = new AtomicLong();
        settlementList.forEach(settlement -> {
            long settlementId = settlement.getSettlementId();
            if(settlementId> index.get()) index.set(settlementId);
        });
        return index.get()+1;
    }

    public long getNextContributionId() {
        List<Contribution> contributionList = getAllContributions();
        AtomicLong index = new AtomicLong();
        contributionList.forEach(contribution -> {
            long contributionId = contribution.getId();
            if(contributionId> index.get()) index.set(contributionId);
        });
        return index.get()+1;
    }

//  internal functions
    public void addSettlementsToCircle(Long groupId, Long userId) {
        List<User> previousUsersList = getAllUsersWhoArePartOfThisCircle(groupId);
        AtomicBoolean isAllUserAdded = new AtomicBoolean(true);
        previousUsersList.forEach(user -> {
            Settlement settlement = new Settlement();
            settlement.setSettlementId(getNextSettlementId());
            settlement.setGroupId(groupId);
            settlement.setXBalance(0);
            settlement.setYBalance(0);
            settlement.setUserIdOfX(user.getUserId());
            settlement.setUserIdOfY(userId);
            if(user.getUserId()!=userId)  settlement = addSettlements(settlement);
            if(user.getUserId()!= userId && settlement.getSettlementId()!=0) isAllUserAdded.set(false);
        });
        isAllUserAdded.get();
    }

    public Settlement addSettlements(Settlement settlement) {;
        return settlementRepository.save(settlement);
    }

}
