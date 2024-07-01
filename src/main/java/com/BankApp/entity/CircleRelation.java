package com.BankApp.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "circlerelation")
@NoArgsConstructor
@Data
public class CircleRelation {

    @Id
    @Column(name = "id")
    private long userCircleCombinedId;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "circle_id")
    private long circleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circle_id")
    private FriendCircle friendCircle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
