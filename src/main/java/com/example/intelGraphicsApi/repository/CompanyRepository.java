package com.example.intelGraphicsApi.repository;

import com.example.intelGraphicsApi.entity.Company;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJdbcRepositories
public interface CompanyRepository extends JpaRepository<Company,Long> {
        Company findByIdentityCompany(String s);
        Company findByName(String s);
        Company findByBaseBoardNumber(String s);
        List<Company> getByName(String s);
}
