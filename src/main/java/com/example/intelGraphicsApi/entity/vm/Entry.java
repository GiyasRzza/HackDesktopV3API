package com.example.intelGraphicsApi.entity.vm;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class Entry {

    private String name;

    private String baseBoardNumber;

    private String password;

    private LocalDateTime firstDate;
    @Getter
    @Setter
    public static String identityNumber;

    private String responsibleCode;

    private long responsibleCodeId;

}
