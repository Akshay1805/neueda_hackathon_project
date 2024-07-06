package com.BankApp.repository;

import com.BankApp.entity.CircleRelation;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@EntityScan("com.BankApp.entity")
@Repository
public interface CircleRelationRepository extends JpaRepository<CircleRelation, Long> {


    @Modifying
    @Transactional
    @Query("DELETE FROM CircleRelation cr WHERE cr.friendCircle.circleId = :circleId AND cr.user.userId = :userId")
    int deleteByCircleIdAndUserId(@Param("circleId") long circleId, @Param("userId") long userId);
}
