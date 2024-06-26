package com.BankApp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("settlement")
public class settlementController {

    @PostMapping("/make")
    void makeSettlement(){

    }
}
