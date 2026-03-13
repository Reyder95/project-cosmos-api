package com.projectcosmos.api.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "star_systems")
@Data
public class StarSystem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "system_name", nullable = false, unique = true, length = 50)
    private String systemName;

    @Column(name = "security_level", columnDefinition = "numeric(5,2) default 1.0")
    private Double securityLevel;

    @Column(name = "heat", columnDefinition = "numeric(5,2) default 0.0")
    private Double heat;

    @Column(name = "star_type_id")
    private Integer starTypeId;

    @Column(name = "region_id")
    private Integer regionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "star_type_id", insertable = false, updatable = false)
    private Star starType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    private StarRegion starRegion;

    @OneToMany(mappedBy = "starSystem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StarPlanet> planets = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
