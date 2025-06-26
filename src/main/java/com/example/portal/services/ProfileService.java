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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import static com.example.portal.utils.Errors.E167;
import static com.example.portal.utils.Errors.E289;

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
    public User createUser(UserCreateDTO user, MultipartFile file) {
        E289.thr(userRepository.findByLoginEqualsIgnoreCase(user.getLogin()).isEmpty(), user.getLogin());
        E167.thr(!Objects.equal(userDetailsService.getRoleNow(), UserRole.EMPLOYEE));

        String cleanPassword = generateRandomString();
        user.setPassword(cleanPassword);

        User newUser = new User()
                .setName(user.getName())
                .setLogin(user.getLogin())
                .setEmail(user.getEmail())
                .setTelegram(user.getTelegram())
                .setBirthday(user.getBirthday())
                .setStartWork(user.getStartWork())
                .setCity(user.getCity())
                .setPhoneNumber(user.getPhoneNumber())
                .setIsActive(true)
                .setRole(user.getRole())
                .setPassword(userDetailsService.getEncryptedPassword(cleanPassword));

        if (file != null && !file.isEmpty()) {
            String fileName = "photo_" + user.getLogin() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get("C:\\employee_pictures", fileName);
            Files.write(filePath, file.getBytes());
            newUser.setPhoto(filePath.toString());
        }

        restAuditService.saveAuditRequest(RestType.POST, user);

        return userRepository.save(newUser);
    }

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
}