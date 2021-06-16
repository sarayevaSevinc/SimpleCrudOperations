package com.ibar.demo.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JwtRequestDTO implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    @JsonProperty("pin")
    private String username;
    @JsonProperty("password")
    private String password;
}