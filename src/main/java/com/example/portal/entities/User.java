package com.example.portal.entities;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Data
@Entity
@Table(name = "user_")
public class User extends AbstractEntity{

    private String name;
    private String email;
    private String password;
    private String post;
    private String telegram;
    private String number;


    public User() {
        // Можно оставить пустым
        // Если в классе User отсутствует публичный или защищённый конструктор без аргументов, возникнет эта ошибка.
    }


    public User(String name, String email, String password, String post, String telegram, String number) {
//        this.setId(id); // если есть поле id в AbstractEntity или в этом классе
        this.name = name;
        this.email = email;
        this.password = password;
        this.post = post;
        this.telegram = telegram;
        this.number = number;
    }
}
