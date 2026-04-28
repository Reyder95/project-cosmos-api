package com.projectcosmos.api.dto.StarSystem;

import java.util.List;
import java.util.Set;

import com.projectcosmos.api.entity.Star;
import com.projectcosmos.api.entity.StarPlanet;
import com.projectcosmos.api.entity.StarRegion;

import lombok.Data;

@Data
public class StarSystemDto {
    private Integer id;
    private String galaxy;
    private Double heat;
    private List<StarPlanet> planets;
    private StarRegion starRegion;
    private Star starType;
    private String systemIdentifier;
    private String systemName;
    private Double securityLevel;
    private Double xPos;
    private Double yPos;
    private Set<StarSystemSummaryDto> connectedSystems;
}
