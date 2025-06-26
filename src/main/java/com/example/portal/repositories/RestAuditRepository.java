package com.example.portal.repositories;

import com.example.portal.entities.RestAudit;
import com.example.portal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestAuditRepository extends JpaRepository<RestAudit, Long> {

    default Class<User> getClazz() {
        return User.class;
    }

}