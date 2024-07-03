package com.BankApp.entity;

import com.BankApp.request.CreateFriendCircleRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Table(name = "friendcircle")
@Entity
@Data
public class FriendCircle {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "circle_id")
    private long circleId;
    @Column(name = "circle_name")
    private String circleName;
    @Column(name = "circle_category")
    private String circleCategory;
    @Column(name = "created_on")
    private Date createdOn;

    // One-to-many relationship with CircleRelation
    @OneToMany(mappedBy = "friendCircle")
    private List<CircleRelation> circleRelations;

    @OneToMany(mappedBy = "friendCircle")
    private List<Transaction> transactionList;

    // constructor for FriendCircle from createFriendCircleRequest
    public FriendCircle(CreateFriendCircleRequest createFriendCircleRequest) {
        this.circleName = createFriendCircleRequest.getCircleName();
        this.circleCategory = createFriendCircleRequest.getCircleCategory();
        this.createdOn = createFriendCircleRequest.getCreatedOn();
    }
}
