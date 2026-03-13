package com.projectcosmos.api.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

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
@Table(name = "items")
@Data
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ship_id")
    private Integer shipId;

    @Column(name = "module_id")
    private Integer moduleId;

    @Column(name = "item_name", nullable = false, unique = true, length = 100)
    private String itemName;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "base_price", columnDefinition = "numeric(15,2) default 0.00")
    private Double basePrice;

    @Column(name = "item_type_id")
    private Integer itemTypeId;

    @Column(name = "item_sub_type_id")
    private Integer itemSubTypeId;

    @Column(name = "item_identifier", nullable = false, unique = true, length = 50)
    private String itemIdentifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ship_id", insertable = false, updatable = false)
    private Ship ship;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", insertable = false, updatable = false)
    private Module module;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_type_id", insertable = false, updatable = false)
    private ItemType itemType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_sub_type_id", insertable = false, updatable = false)
    private ItemSubType itemSubType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
