package com.example.portal.entities;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
public class User extends AbstractEntity {

    private String name;
    private String email;
    private String password;

}
