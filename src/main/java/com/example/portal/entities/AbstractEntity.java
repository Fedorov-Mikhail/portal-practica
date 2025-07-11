package com.example.portal.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Data
@MappedSuperclass
@FieldNameConstants
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity<V> extends IdentityEntity<V> {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @LastModifiedDate
    private LocalDateTime modificationDate;
    @PreUpdate
    public void preUpdate(){
        modificationDate = LocalDateTime.now();
    }
    @PrePersist
    public void prePersist(){
        creationDate = LocalDateTime.now();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AbstractEntity)) return false;
        final AbstractEntity<?> other = (AbstractEntity<?>) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$creationDate = this.getCreationDate();
        final Object other$creationDate = other.getCreationDate();
        if (this$creationDate == null ? other$creationDate != null : !this$creationDate.equals(other$creationDate))
            return false;
        final Object this$modificationDate = this.getModificationDate();
        final Object other$modificationDate = other.getModificationDate();
        if (this$modificationDate == null ? other$modificationDate != null : !this$modificationDate.equals(other$modificationDate))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AbstractEntity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $creationDate = this.getCreationDate();
        result = result * PRIME + ($creationDate == null ? 43 : $creationDate.hashCode());
        final Object $modificationDate = this.getModificationDate();
        result = result * PRIME + ($modificationDate == null ? 43 : $modificationDate.hashCode());
        return result;
    }
}