package com.projectcosmos.api.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "star_planets")
@Data
public class StarPlanet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "system_id")
    private Integer systemId;

    @Column(name = "planet_type_id")
    private Integer planetTypeId;

    @Column(name = "radius", nullable = false)
    private Double radius;

    @Column(name = "angle", nullable = false)
    private Double angle;

    @Column(name = "size", nullable = false)
    private Double size;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id", insertable = false, updatable = false)
    private StarSystem starSystem;

    @OneToMany(mappedBy = "parentPlanet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StarMoon> moons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planet_type_id", insertable = false, updatable = false)
    private Planet planet;
}
