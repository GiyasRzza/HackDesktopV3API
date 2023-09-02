package com.example.intelGraphicsApi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Entity
@NoArgsConstructor
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String baseBoardNumber;
    @NotNull
    private String identityCompany ;
    private LocalDateTime createdDate;
    private LocalDateTime firstDate;
    private LocalDateTime runningDate;
    private long afterDays;
    private boolean isHacking;
    private boolean isWorkOnce;
    @ManyToOne
    private Hacker hacker;




}
