package com.example.intelGraphicsApi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class UpFiles {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String fileName;
    private LocalDateTime  uploadedDate = LocalDateTime.now();
    @ManyToOne
    private Company company;


}
