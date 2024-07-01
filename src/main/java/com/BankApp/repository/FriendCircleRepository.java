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

    @Query("SELECT cr.userId FROM CircleRelation cr WHERE cr.circleId = :circleId")
    List<Long> findUserIdsByCircleId(@Param("circleId") long circleId);

    List<FriendCircle> getFriendCircleByCircleIdIsIn(List<Long> friendCircleIds);
}
