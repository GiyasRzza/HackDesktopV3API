package com.example.intelGraphicsApi.scheduler;

import com.example.intelGraphicsApi.entity.Company;
import com.example.intelGraphicsApi.repository.CompanyRepository;
import com.example.intelGraphicsApi.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@EnableScheduling
@AllArgsConstructor
public class HackingControl {

    CompanyRepository companyService;
    public void isHacking(Company company){
        LocalDateTime controlDate  =  LocalDateTime.now();
        if (company.getRunningDate().isBefore(controlDate)) {
            company.setHacking(true);
        }
        companyService.save(company);

    }

}
