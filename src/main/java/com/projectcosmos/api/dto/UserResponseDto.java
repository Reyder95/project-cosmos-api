package com.projectcosmos.api.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserResponseDto {
    private Integer id;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
