package com.BankApp.repository;

import com.BankApp.entity.FriendCircle;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EntityScan("com.BankApp.entity")
@Repository
public interface FriendCircleRepository extends JpaRepository<FriendCircle, Long> {

    @Query("SELECT fc FROM FriendCircle fc JOIN CircleRelation cr ON fc.circleId = cr.friendCircle.circleId WHERE cr.user.userId = :userId")
    List<FriendCircle> findCirclesByUserId(@Param("userId") long userId);
}
