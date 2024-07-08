package com.BankApp.response;

import com.BankApp.entity.FriendCircle;
import com.BankApp.entity.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    @JsonProperty("transaction_id")
    private long transactionId;

    @JsonProperty("category")
    private String category;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("payment_date")
    private String paymentDate;

    public TransactionResponse(Transaction transaction) {
        this.transactionId = transaction.getTransactionId();
        this.category = transaction.getCategory();
        this.price = transaction.getPrice();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.paymentDate = dateFormat.format(transaction.getPaymentDate());
    }
}
