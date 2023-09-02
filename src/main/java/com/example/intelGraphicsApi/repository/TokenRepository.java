package com.example.intelGraphicsApi.repository;

import com.example.intelGraphicsApi.entity.Hacker;
import com.example.intelGraphicsApi.entity.Token;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@EnableJdbcRepositories
public interface TokenRepository extends JpaRepository<Token,String> {
    void deleteByLoginDateBefore(LocalDateTime localDateTime);
    Token getTokenByToken(String token);



}
