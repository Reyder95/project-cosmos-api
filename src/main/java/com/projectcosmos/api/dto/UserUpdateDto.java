package com.projectcosmos.api.dto;

import lombok.Data;

@Data
public class UserUpdateDto {
    private String username;
    private String email;
    private String profilePicIdentifier;
    private String password;
}
