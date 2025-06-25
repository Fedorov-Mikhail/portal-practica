package com.example.portal.services;

import com.example.portal.dto.UserDTO;
import com.example.portal.entities.User;
import com.example.portal.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;

    public User getUserProfileById(Long id) {
        // Попытка найти пользователя по ID
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new RuntimeException("Пользователь с id " + id + " не найден");
        }
    }

    public List<User> getAllUserProfiles() {
        return userRepository.findAll();
    }
}


//        UserDTO userDTO = new UserDTO();
//        userDTO
//                .setName("gfbgb")
//                .setPost("gfbgb");
//List<User> java = userRepository.findAllByPostContains("java");