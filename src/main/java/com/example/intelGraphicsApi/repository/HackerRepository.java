package com.example.intelGraphicsApi.repository;

import com.example.intelGraphicsApi.entity.Hacker;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableJdbcRepositories
public interface HackerRepository extends JpaRepository<Hacker,Long> {

    Hacker findByUserName(String s);
}
