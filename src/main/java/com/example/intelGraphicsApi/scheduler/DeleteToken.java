package com.example.intelGraphicsApi.scheduler;

import com.example.intelGraphicsApi.repository.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
@EnableScheduling
public class DeleteToken {
    TokenRepository tokenRepository;
    @Scheduled(fixedRate = 10*60*1000)
    @Transactional
    public void clearTokens(){
        LocalDateTime localDateTime = LocalDateTime.now();
        tokenRepository.deleteByLoginDateBefore(localDateTime);
     }
}
