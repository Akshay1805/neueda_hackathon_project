package com.BankApp.repository;

import com.BankApp.entity.Contribution;
import com.BankApp.entity.FriendCircle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {

    List<Contribution> findByTransactionId(Long id);

    @Modifying
    void deleteContributionByTransactionId(long transactionId);
}
