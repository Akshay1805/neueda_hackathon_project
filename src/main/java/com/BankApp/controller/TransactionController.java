package com.BankApp.controller;


import com.BankApp.entity.CircleRelation;
import com.BankApp.entity.Transaction;
import com.BankApp.request.AddUserToCircleRequest;
import com.BankApp.request.CreateTransactionRequest;
import com.BankApp.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction/")
public class TransactionController {

    @Autowired
    CommonService commonService;

    @Operation(summary = "Add User to Friend Circle", description = "Adds a new user to an existing Friend Circle.Returns msg")
    /*
    {
        "description": "Paid to Halka Fulka for paratha",
        "category": "Food",
        "price": 120,
        "user_id_of_payer": 8,
        "group_id": 5,
    }
    */
    @PostMapping("/create")
    public boolean createTransaction(@RequestBody CreateTransactionRequest createTransactionRequest) {
        Transaction transaction = commonService.createTransaction(createTransactionRequest);
        return transaction != null;
    }

}