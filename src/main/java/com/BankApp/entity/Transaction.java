package com.BankApp.entity;


import com.BankApp.request.CreateTransactionRequest;
import com.BankApp.request.CreateUserRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private long transactionId;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private double price;

    @Column(name = "user_id_of_payer")
    private Long userIdOfPayer;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "group_id", insertable = false, updatable = false)
    private Long groupId;

    // One-to-many relationship with CircleRelation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private FriendCircle friendCircle;

    // constructor for User from createUserResponse
    public Transaction(CreateTransactionRequest createTransactionRequest) {
        this.description = createTransactionRequest.getDescription();
        this.category = createTransactionRequest.getCategory();
        this.price = createTransactionRequest.getPrice();
        this.userIdOfPayer = createTransactionRequest.getUserIdOfPayer();
        this.groupId = createTransactionRequest.getGroupId();
    }
}
