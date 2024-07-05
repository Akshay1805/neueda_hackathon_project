package com.BankApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "settlement")
@NoArgsConstructor
@Data
public class Settlement {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "settlement_id")
    private Long settlementId;

    @Column(name = "balance_of_x")
    private double xBalance;

    @Column(name = "balance_of_y")
    private double yBalance;

    @Column(name = "user_id_of_x")
    private Long userIdOfX;

    @Column(name = "user_id_of_y")
    private Long userIdOfY;

    @Column(name = "group_id")
    private Long groupId;

}
