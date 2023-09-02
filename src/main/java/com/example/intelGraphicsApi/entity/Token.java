package com.example.intelGraphicsApi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Token {
    @Id
    private String token;
    @ManyToOne
    private Hacker hacker;
    LocalDateTime loginDate = LocalDateTime.now();
}
