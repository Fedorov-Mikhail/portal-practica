package com.example.portal.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.catalina.Role;

import javax.xml.bind.annotation.XmlAccessorOrder;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user_")
@Accessors(chain = true)
public class User extends AbstractEntity{

    @NotBlank
    private String name;
    @Email
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
