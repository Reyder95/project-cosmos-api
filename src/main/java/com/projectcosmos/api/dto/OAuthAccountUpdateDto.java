package com.projectcosmos.api.dto;

import lombok.Data;

@Data
public class OAuthAccountUpdateDto {
    private String provider;
    private String providerUserId;
}
