package com.example.portal.authentication;

import com.google.common.base.Preconditions;
import com.example.portal.entities.User;
import com.example.portal.entities.UserRole;
import com.example.portal.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import org.springframework.security.authentication.BadCredentialsException;

import static com.example.portal.utils.TextConstant.*;

@Service
@RequiredArgsConstructor
public class UserDetailsService extends AbstractUserDetailsAuthenticationProvider implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        System.err.println(ADDITIONAL_AUTHENTICATION_CHECKS);
    }

    @Override
    @Transactional
    public UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UserDetails userDetails = loadUserByUsername(username);
        String rawPassword = String.valueOf(authentication.getCredentials());
        String encodedPassword = userDetails.getPassword();

        System.out.println("Введённый пароль: " + rawPassword);
        System.out.println("Зашифрованный пароль из базы: " + encodedPassword);

        boolean matched = passwordEncoder.matches(rawPassword, userDetails.getPassword());

        if (!matched) {
            System.out.println("Пароль не совпадает");
            throw new BadCredentialsException(INCORRECT_PASSWORD);
        }
        return userDetails;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = getActiveUser(login);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), Collections.emptyList());
    }

    public User getActiveUser(String login) {
        User user = findByLogin(login);
        Preconditions.checkState(BooleanUtils.toBoolean(user.getIsActive()), ACCOUNT_IS_NOT_ACTIVE);
        return user;
    }

    public UserRole getRoleNow() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_DEFINED));
        return user.getRole();
    }

    public User findByLogin(String login) {
        return userRepository.findByLoginEqualsIgnoreCase(login)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_DEFINED));
    }

    public Long getIdNow() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByLoginEqualsIgnoreCase(login)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_DEFINED));
        return user.getId();
    }

    public String getNameNow() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByLoginEqualsIgnoreCase(login)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_DEFINED));
        return user.getName();
    }

    public String getEncryptedPassword(String password){
        return passwordEncoder.encode(password);
    }
}
