package com.example.intelGraphicsApi.repository;

import com.example.intelGraphicsApi.entity.CommandLine;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableJdbcRepositories
public interface CommandRepository extends JpaRepository<CommandLine,Long> {

    List<CommandLine> findByCompany_IdOrderByLevel(long id);
}
