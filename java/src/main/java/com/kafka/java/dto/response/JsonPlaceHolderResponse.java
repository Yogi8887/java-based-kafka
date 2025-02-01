package com.kafka.java.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class JsonPlaceHolderResponse {
    @JsonProperty("userId")
    @NotNull
    @Pattern(regexp = "^[0-9]*$")
    private long userId;
    @JsonProperty("id")
    @NotNull
    @Pattern(regexp = "^[0-9]*$")
    private long id;
    @JsonProperty("title")
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String title;
    @NotNull
    @JsonProperty("body")
    private String body;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
