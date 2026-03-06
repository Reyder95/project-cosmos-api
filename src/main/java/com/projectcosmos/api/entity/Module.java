package com.projectcosmos.api.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.projectcosmos.api.config.Helpers;
import com.projectcosmos.api.dto.Module.ModuleAttributesDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "modules")
@Data
public class Module {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "module_name", nullable = false, unique = true, length = 50)
    private String moduleName;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default false")
    private Boolean isActive;

    @Column(name = "uses_ammo", nullable = false, columnDefinition = "boolean default false")
    private Boolean usesAmmo;

    @Column(name = "cpu", columnDefinition = "numeric(10,2) default 0.0")
    private Double cpu;

    @Column(name = "power_grid", columnDefinition = "numeric(10,2) default 0.0")
    private Double powerGrid;

    @Column(name = "ammo_subtype_id")
    private Integer ammoSubtypeId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "attributes", columnDefinition = "jsonb", nullable = false)
    private Map<String, Object> attributes = new HashMap<>();

    @Column(name = "slot_type", nullable = false)
    private Helpers.SlotType slotType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
