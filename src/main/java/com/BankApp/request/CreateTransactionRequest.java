package com.BankApp.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionRequest {

    @JsonProperty("description")
    private String description;

    @JsonProperty("category")
    private String category;

    @JsonProperty("price")
    private double price;

    @JsonProperty("user_id_of_payer")
    private Long UserIdOfPayer;

    @JsonProperty("group_id")
    private Long groupId;

    @JsonProperty("contributions")
    private HashMap<Long, Double> distributionList;

}

