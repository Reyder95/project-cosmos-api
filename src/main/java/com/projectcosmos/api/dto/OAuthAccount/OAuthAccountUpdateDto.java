package com.projectcosmos.api.dto.OAuthAccount;

import lombok.Data;

@Data
public class OAuthAccountUpdateDto {
    private String provider;
    private String providerUserId;
}
