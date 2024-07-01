package com.BankApp.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFriendCircleRequest {

    @JsonProperty("admin_user_id")
    private long adminUserId;

    @JsonProperty("circle_name")
    private String circleName;

    @JsonProperty("circle_category")
    private String circleCategory;

    @JsonIgnore
    @JsonProperty("created_on")
    private Date createdOn;
}
