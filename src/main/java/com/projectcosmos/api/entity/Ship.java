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
@Table(name = "ships")
@Data
public class Ship {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ship_name", nullable = false, unique = true, length = 50)
    private String shipName;

    @Column(name = "ship_pic_identifier", length = 100)
    private String shipPicIdentifier;

    @Column(name = "ship_type_id")
    private Integer shipTypeId;

    @Column(name = "high_slots", columnDefinition = "integer default 0")
    private Integer highSlots;

    @Column(name = "medium_slots", columnDefinition = "integer default 0")
    private Integer mediumSlots;

    @Column(name = "low_slots", columnDefinition = "integer default 0")
    private Integer lowSlots;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ship_type_id", insertable = false, updatable = false)
    private ShipType shipType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
