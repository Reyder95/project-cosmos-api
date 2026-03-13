package com.projectcosmos.api.dto.Module;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projectcosmos.api.config.Helpers;

import lombok.Data;

@Data
public class OuterModuleCreateDTO {
    private String moduleName;

    @JsonProperty(required = false)
    private Boolean isActive;
    @JsonProperty(required = false)
    private Boolean usesAmmo;
    private Double cpu;
    private Double powerGrid;
    private Integer ammoSubTypeId;
    private Helpers.SlotType slotType;
    private ModuleAttributesDTO attributes;
}
