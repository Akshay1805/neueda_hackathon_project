package com.BankApp.repository;

import com.BankApp.entity.CircleRelation;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@EntityScan("com.BankApp.entity")
@Repository
public interface CircleRelationRepository extends JpaRepository<CircleRelation, Long> {
}
