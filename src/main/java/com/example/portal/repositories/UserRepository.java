package com.example.portal.repositories;


import com.example.portal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
    Optional<User> findByLoginEqualsIgnoreCase(String login);

}