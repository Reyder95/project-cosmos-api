package com.projectcosmos.api.dto.User;

import lombok.Data;

@Data
public class UserLoginDto {
    private String username;
    private String password;
}