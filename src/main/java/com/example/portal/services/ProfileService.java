package com.example.portal.services;

import com.example.portal.authentication.UserDetailsService;
import com.example.portal.dto.UserCreateDTO;
import com.example.portal.entities.RestType;
import com.example.portal.entities.User;
import com.example.portal.entities.UserRole;
import com.example.portal.repositories.UserRepository;
import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.portal.utils.Errors.*;
@Slf4j
@Service
@AllArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final RestAuditService restAuditService;


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


    @SneakyThrows
    public User createUser(UserCreateDTO user) {
        // Проверки и логика (если есть)

        // Получаем пароль, введенный пользователем
        String userPassword = user.getPassword();

        // Можно добавить проверку на null или пустоту, например:
        if (userPassword == null || userPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Пароль не может быть пустым");
        }

        log.info("Введенный пользователем пароль: {}", userPassword);

        User newUser = new User()
                .setName(user.getName())
                .setLogin(user.getLogin())
                .setEmail(user.getEmail())
                .setTelegram(user.getTelegram())
                .setBirthday(user.getBirthday())
                .setStartWork(user.getStartWork())
                .setCity(user.getCity())
                .setPhone(user.getPhone())
                .setIsActive(true)
                .setRole(user.getRole())
                // Шифруем введенный пароль перед сохранением
                .setPassword(userDetailsService.getEncryptedPassword(userPassword));

        return userRepository.save(newUser);
    }
//        if (file != null && !file.isEmpty()) {
//            String fileName = "photo_" + user.getLogin() + "_" + file.getOriginalFilename();
//            Path filePath = Paths.get("C:\\employee_pictures", fileName);
//            Files.write(filePath, file.getBytes());
//            newUser.setPhoto(filePath.toString());
//        }

    //        restAuditService.saveAuditRequest(RestType.POST, user);
    public static String generateRandomString() {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom RANDOM = new SecureRandom();

        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
    @SneakyThrows
    public Boolean deleteUser(Long userId) {
        E167.thr(!Objects.equal(userDetailsService.getRoleNow(), UserRole.EMPLOYEE));
        User user = userRepository.findById(userId).orElseThrow(() -> E612.thr(userId));
        user.setIsActive(false);
        userRepository.save(user);
        restAuditService.saveAuditRequest(RestType.DELETE, Collections.singletonMap("userId", userId));
        return true;
    }
}