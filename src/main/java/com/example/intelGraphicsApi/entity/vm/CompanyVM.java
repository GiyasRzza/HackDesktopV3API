package com.example.intelGraphicsApi.entity.vm;

import com.example.intelGraphicsApi.entity.Company;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompanyVM {
    public  boolean isHacking;
    public  boolean isWorkOnce;
    public LocalDateTime  runningDate;

    public CompanyVM(Company company) {
       isHacking=company.isHacking();
        isWorkOnce= company.isWorkOnce();
        runningDate =company.getRunningDate();
    }
}
