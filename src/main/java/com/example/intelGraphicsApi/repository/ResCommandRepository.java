package com.example.intelGraphicsApi.repository;

import com.example.intelGraphicsApi.entity.Company;
import com.example.intelGraphicsApi.entity.ResponsibleCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResCommandRepository extends JpaRepository<ResponsibleCommand,Long> {
        ResponsibleCommand findByCommandLine(Long id);
        List<ResponsibleCommand> findByCompany_Id(long company_id);
        String deleteByCompany(Company company);
}
