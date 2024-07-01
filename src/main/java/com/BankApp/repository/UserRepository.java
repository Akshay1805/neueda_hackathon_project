package com.BankApp.repository;

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

    @Query("SELECT cr.circleId FROM CircleRelation cr WHERE cr.userId = :userId")
    List<Long> findFriendCircleIdsByUserId(@Param("userId") long userId);
}
