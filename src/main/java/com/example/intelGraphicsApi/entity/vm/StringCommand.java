package com.example.intelGraphicsApi.entity.vm;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StringCommand {
    private String command;
    private String isResponse;
    @Size(max = 10)
    private long level;
    private String company;
    private boolean copyFile;
}
