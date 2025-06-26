package com.example.portal.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "rest_audit")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class RestAudit extends IdentityEntity<Long> {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime requestTime;

    private String name;

    @Column
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column
    @Enumerated(value = EnumType.STRING)
    private RestType requestType;

    private String body;

    @PrePersist
    public void prePersist(){
        requestTime = LocalDateTime.now();
    }
}