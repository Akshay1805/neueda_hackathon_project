package com.BankApp.response;


import com.BankApp.entity.FriendCircle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendCircleResponse {

    @JsonProperty("friend_circle_id")
    private long circleId;

    @JsonProperty("circle_name")
    private String circleName;

    @JsonProperty("circle_category")
    private String circleCategory;

    @JsonIgnore
    @JsonProperty("created_on")
    private Date createdOn;

    public FriendCircleResponse(FriendCircle friendCircle) {
        this.circleId = friendCircle.getCircleId();
        this.circleName = friendCircle.getCircleName();
        this.circleCategory = friendCircle.getCircleCategory();
        this.createdOn = friendCircle.getCreatedOn();
    }
}
