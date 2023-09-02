package com.example.intelGraphicsApi.controller;

import com.example.intelGraphicsApi.entity.vm.TokenVM;
import com.example.intelGraphicsApi.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/tokens")
@AllArgsConstructor
public class TokenController {
        TokenService tokenService;
        @GetMapping(path = "/current-token")
          public Optional<TokenVM> currentToken(@RequestHeader(name = "token") String token){
                return tokenService.getCurrentToken(token).map(TokenVM::new);
        }

}
