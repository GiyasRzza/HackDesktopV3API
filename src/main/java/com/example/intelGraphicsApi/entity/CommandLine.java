package com.example.intelGraphicsApi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class CommandLine {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String command;
    private boolean isResponsible;
    @Size(max = 10)
    private long level;
    private boolean copyFile;
    @ManyToOne
    private Company company;

}
