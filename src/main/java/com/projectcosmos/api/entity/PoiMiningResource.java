package com.projectcosmos.api.entity;

import java.time.LocalDateTime;

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
@Table(name = "poi_mining_resources")
@Data
public class PoiMiningResource {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "resource_id")
    private Integer resourceId;

    @Column(name = "poi_id")
    private Integer poiId;

    @Column(name = "maximum", nullable = false)
    private Integer maximum;

    @Column(name = "current", nullable = false)
    private Integer current;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id", insertable = false, updatable = false)
    private Item resource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poi_id", insertable = false, updatable = false)
    private PointsOfInterest poi;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
