package com.BankApp.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserToCircleRequest {

    @JsonProperty("admin_user_id")
    private long adminUserId;

    @JsonProperty("friend_circle_id")
    private long friendCircleId;

    @JsonProperty("new_user_id")
    private long newUserId;
}
