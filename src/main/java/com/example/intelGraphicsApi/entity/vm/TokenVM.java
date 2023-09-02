package com.example.intelGraphicsApi.entity.vm;

import com.example.intelGraphicsApi.entity.Token;
import lombok.Data;

@Data
public class TokenVM {
    public String token;
    private String userName;

    public TokenVM(Token realToken) {
        this.token = realToken.getToken();
        this.userName = realToken.getHacker().getUserName();
    }
}
