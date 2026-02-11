package com.projectcosmos.api.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserLoginDto {
    private String username;
    private String password;
}