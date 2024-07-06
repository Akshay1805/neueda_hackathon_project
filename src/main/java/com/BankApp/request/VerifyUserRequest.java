package com.BankApp.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerifyUserRequest {


    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

}

