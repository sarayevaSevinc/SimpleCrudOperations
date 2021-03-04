package com.ibar.demo.controllers.dto;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class ResponseDTO<T> {

    T data;
    String message;

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("status")
    public void setMessage(String message) {
        this.message = message;
    }
}