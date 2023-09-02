package com.example.intelGraphicsApi.entity.vm;

import com.example.intelGraphicsApi.entity.Hacker;
import lombok.Data;

@Data
public class HackerVM {
    public boolean isAdmin;
    private String userName;

    public HackerVM(Hacker hacker) {
        this.isAdmin = hacker.isAdmin();
        this.userName = hacker.getUserName();
    }
}
