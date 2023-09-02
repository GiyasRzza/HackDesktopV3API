package com.example.intelGraphicsApi.exceptions;

import com.example.intelGraphicsApi.entity.vm.HackerVM;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private HackerVM hackerVM;
}
