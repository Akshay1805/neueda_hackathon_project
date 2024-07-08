package com.BankApp.repository;

import com.BankApp.entity.FriendCircle;
import com.BankApp.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettlementRepository extends JpaRepository<Settlement, Long> {

    List<Settlement> findAllByUserIdOfXOrUserIdOfY(Long userIdOfX, Long userIdOfY);

    Long findByGroupIdAndUserIdOfXAndUserIdOfY(Long groupId, Long UserIdX, Long UserIdY);

    List<Settlement> findByGroupId(Long groupId);


}
