package com.projectcosmos.api.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "star_moons")
@Data
public class StarMoon {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "moon_type_id")
    private Integer moonTypeId;

    @Column(name = "parent_planet_id")
    private Integer parentPlanetId;

    @Column(name = "radius", nullable = false)
    private Double radius;

    @Column(name = "angle", nullable = false)
    private Double angle;

    @Column(name = "size", nullable = false)
    private Double size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moon_type_id", insertable = false, updatable = false)
    private Planet moonType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_planet_id", insertable = false, updatable = false)
    private StarPlanet parentPlanet;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
