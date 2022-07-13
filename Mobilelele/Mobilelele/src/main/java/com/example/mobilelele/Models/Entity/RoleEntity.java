package com.example.mobilelele.Models.Entity;

import com.example.mobilelele.Models.Entity.Enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoleEnum name;

    public RoleEntity(UserRoleEnum roleEnum) {
        this.name = roleEnum;
    }

    public RoleEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserRoleEnum getName() {
        return name;
    }

    public void setName(UserRoleEnum name) {
        this.name = name;
    }
}
