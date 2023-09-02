package com.example.intelGraphicsApi.entity.vm;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommandToCompany {
    private String command;
    private boolean isResponse;
    @Size(max = 10)
    private long level;
    private String company;
}
