package com.BankApp.repository;

import com.BankApp.entity.FriendCircle;
import com.BankApp.entity.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EntityScan("com.BankApp.entity")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> getUsersByUserIdIsIn(List<Long> userIdsList);

    @Query("SELECT u FROM User u JOIN CircleRelation cr ON u.userId = cr.user.userId WHERE cr.friendCircle.circleId = :circleId")
    List<User> findUserByCircleId(@Param("circleId") long circleId);

}
