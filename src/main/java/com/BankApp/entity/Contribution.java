//package com.BankApp.entity;
//
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.Date;
//
//@Entity
//@Table(name = "contribution")
//@NoArgsConstructor
//@Data
//public class Contribution {
//    @Id
//    @GeneratedValue(strategy =  GenerationType.IDENTITY)
//    @Column(name = "contribution_id")
//    private long id;
//
//    @Column(name = "transaction_id")
//    private Long transactionId;
//
//    @Column(name = "user_id")
//    private Long userId;
//
//    @Column(name = "payment")
//    private double Payment;
//
//    @Column(name = "group_id", insertable = false, updatable = false)
//    private Long groupId;
//
//    // One-to-many relationship with CircleRelation
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "group_id")
//    private FriendCircle friendCircle;
//
//}
