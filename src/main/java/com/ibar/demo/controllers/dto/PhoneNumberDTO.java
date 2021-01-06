package com.ibar.demo.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberDTO {

    @ApiModelProperty(notes = "phone number", example = "+9941234567")
    private String phone;

    @NoArgsConstructor
    @AllArgsConstructor
    public static class JwtRequestDTO implements Serializable {

        private static final long serialVersionUID = 5926468583005150707L;

        @JsonProperty("pin")
        private String username;
        @JsonProperty("password")
        private String password;


        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
