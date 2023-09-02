package com.example.intelGraphicsApi.service;

import com.example.intelGraphicsApi.entity.Token;
import com.example.intelGraphicsApi.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenService {
        TokenRepository tokenRepository;
        CompanyService companyService;
    public Optional<Token> getCurrentToken(String token) {
        return tokenRepository.findById(token);
    }
    public Optional<Token> isToken(String token){
       Optional<Token> tokens =  tokenRepository.findById(token);
        if (tokens.isEmpty()) {
            throw new NullPointerException();
        }
        else {
            return tokens;
        }
    }

}
