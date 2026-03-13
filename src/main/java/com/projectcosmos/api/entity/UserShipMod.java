package com.projectcosmos.api.entity;

import java.time.LocalDateTime;

import com.projectcosmos.api.config.Helpers.SlotType;

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
@Table(name = "user_ship_mods")
@Data
public class UserShipMod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_ships_id", nullable = false)
    private Integer userShipsId;

    @Column(name = "module_id", nullable = false)
    private Integer moduleId;

    @Column(name = "slot_type", nullable = false)
    private SlotType slotType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_ships_id", insertable = false, updatable = false)
    private UserShip userShip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", insertable = false, updatable = false)
    private Module module;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
