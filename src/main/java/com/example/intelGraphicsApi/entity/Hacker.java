package com.example.intelGraphicsApi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Hacker {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique=true)
    @NotNull
    private String userName;
    @NotNull
    private String password;
    public boolean isAdmin;

}
