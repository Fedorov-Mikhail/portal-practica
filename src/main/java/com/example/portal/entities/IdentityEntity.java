package com.example.portal.entities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.FieldNameConstants;


@Data
@MappedSuperclass
@FieldNameConstants
public abstract class IdentityEntity<V> {

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "global_sequence", allocationSize = 1)
    private V id;
}
