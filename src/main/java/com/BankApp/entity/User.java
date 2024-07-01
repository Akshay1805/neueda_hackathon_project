package com.BankApp.entity;


import com.BankApp.request.CreateUserRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "upi_id")
    private String upiId;

    // One-to-many relationship with CircleRelation
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CircleRelation> circleRelations;

    // constructor for User from createUserResponse
    public User(CreateUserRequest createUserRequest) {
        this.firstName = createUserRequest.getFirstName();
        this.lastName = createUserRequest.getLastName();
        this.email = createUserRequest.getEmail();
        this.upiId = createUserRequest.getUpiId();
    }
}
