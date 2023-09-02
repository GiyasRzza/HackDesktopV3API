package com.example.intelGraphicsApi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class ResponsibleCommand {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "TEXT")
    private String responsibleCode;
    private final LocalDateTime createdDate =LocalDateTime.now();
    @ManyToOne
    private Company company;
    @NotNull
    private Long commandLine;
}
