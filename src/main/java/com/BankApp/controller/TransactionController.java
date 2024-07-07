package com.BankApp.controller;


import com.BankApp.entity.CircleRelation;
import com.BankApp.entity.Transaction;
import com.BankApp.request.AddUserToCircleRequest;
import com.BankApp.request.CreateTransactionRequest;
import com.BankApp.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction/")
public class TransactionController {

    @Autowired
    CommonService commonService;

    @Operation(summary = "Add User to Friend Circle", description = "Adds a new user to an existing Friend Circle.Returns msg")
    /*
    {
        "description": "Paid to Kitchen Bites for paratha",
        "category": "Food",
        "price": 10000,
        "user_id_of_payer": 2,
        "group_id": 3,
        "contributions": {
            "1": 1000,
            "2": 2000,
            "3": 4000,
            "4": 2000,
            "5": 1000
        }
    }
    */
    @PostMapping("/create")
    public boolean createTransaction(@RequestBody CreateTransactionRequest createTransactionRequest) {
        Transaction transaction = commonService.createTransaction(createTransactionRequest);
        return transaction != null;
    }

    @DeleteMapping("/remove/{id}")
    public boolean removeTransaction(@PathVariable Long id) {
        return  commonService.deleteTransaction(id);
    }

    @GetMapping("/list/{id}")
    public List<Transaction> transactionList(@PathVariable Long id) {
        return commonService.transactionList(id);
    }
}