package com.BankApp.repository;

import com.BankApp.entity.Transaction;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EntityScan("com.BankApp.entity")
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByGroupId(Long groupId);
}
