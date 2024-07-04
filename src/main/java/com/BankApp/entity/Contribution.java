package com.BankApp.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "contribution")
@NoArgsConstructor
@Data
public class Contribution {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "contribution_id")
    private Long id;

    @Column(name = "transaction_id", insertable = false, updatable = false)
    private Long transactionId;

    @Column(name = "User_id")
    private Long userId;

    @Column(name = "Payment")
    private Double Payment;

    @Column(name = "Contribution")
    private Double contribution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

}
