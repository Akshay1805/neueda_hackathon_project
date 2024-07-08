package com.BankApp.response;


import com.BankApp.entity.Settlement;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SettlementResponse {
    @Column(name = "settlement_id")
    private Long settlementId;

    @Column(name = "balance")
    private double balance;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "status")
    private String status;

    @Column(name = "note")
    private String note;


    public SettlementResponse(Settlement settlement, Long userId) {
        long userIdx = settlement.getUserIdOfX(); // 2
        long userIdy = settlement.getUserIdOfY(); // 5
        double balancex = settlement.getXBalance(); // -2000
        double balancey = settlement.getYBalance(); // +2000

        if(userId==userIdx){
            this.userId = userIdy;
            this.balance = Math.abs(balancey);
            if(balancex>balancey) {
                this.note = "You will get Rs."+this.balance + " from user with id "+this.userId;
                status = "WILL GET "; // from userId
            } else {
                this.note = "You will have to pay Rs."+this.balance + " to user with id "+this.userId;
                status = "YOU PAY"; // have to Pay
            }
        } else {
            this.userId = userIdx;
            this.balance = Math.abs(balancex);
            if(balancex<=balancey) {
                this.note = "You will get Rs."+this.balance + " from user with id "+this.userId;
                status = "WILL GET "; // from userId
            } else {
                this.note = "You will have to pay Rs."+this.balance + " to user with id "+this.userId;
                status = "YOU PAY"; // have to Pay
            }
        }
        this.settlementId = settlement.getSettlementId();
    }
}
// will get
// will pay

