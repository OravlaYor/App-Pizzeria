package com.platzi.pizza.Persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserEntity {

    @Id
    @Column(nullable = false,length = 20)
    private String username;

    @Column(nullable = false,length = 200)
    //nullable = indica que no puede tener valores null en la base de datos.
    //nullable = in a BD indicate the password shouldn't null
    private String password;

    @Column(nullable = false,length = 50)
    private String email;

    @Column(nullable = false,columnDefinition = "TINYINT")
    private Boolean locked;

    @Column(nullable = false,columnDefinition = "TINYINT")
    private Boolean disable;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<UserRoleEntity> role;
}
