package com.example.portal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Setter;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Getter
@Setter
@Data
@Entity
@Table(name = "user_")
public class User extends AbstractEntity{

    @NotBlank
    private String name;
    private String email;

    @JsonIgnore
    private String password;

    private String post;
    private String telegram;
    @NotBlank
    private String phone;
    private String project;
    private LocalDate birthday;
    @NotBlank
    private String city;
    @NotBlank
    @Enumerated(EnumType.STRING) // или EnumType.ORDINAL
    private UserRole role;
    private LocalDate startWork;
    @NotBlank
    @Column(updatable = false)
    private String login;
    private Boolean isActive;
    private String photo;

    public User() {
        // Можно оставить пустым
        // Если в классе User отсутствует публичный или защищённый конструктор без аргументов, возникнет ошибка.
    }


    public User(String name, String email, String password, String post, String telegram, String number, String project, String birth_date, String city) {
//        this.setId(id); // если есть поле id в AbstractEntity или в этом классе
        this.name = name;
        this.email = email;
        this.password = password;
        this.post = post;
        this.telegram = telegram;
        this.phone = phone;
        this.project = project;
        this.birthday = birthday;
        this.city = city;
        this.role = role;
        this.startWork = LocalDate.now();
        this.login = login;
    }
}
