package com.example.intelGraphicsApi.service;

import com.example.intelGraphicsApi.entity.Company;
import com.example.intelGraphicsApi.entity.vm.Entry;
import com.example.intelGraphicsApi.exceptions.NotFoundException;
import com.example.intelGraphicsApi.scheduler.HackingControl;
import com.example.intelGraphicsApi.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


@Service
@AllArgsConstructor
public class CompanyService {
    CompanyRepository companyRepository;
    HackingControl schedulerCompany;
    ResCommandService resCommandService;
    FileService fileService;
    @ResponseStatus
    public Company authenticate(Entry sign) {
       // String identityNumber = sign.getBaseBoardNumber()+sign.getName()+sign.getPassword();
        //Company inDB = companyRepository.findByIdentityCompany(identityNumber.replace(" ",""));
        Company inDB = companyRepository.findByBaseBoardNumber(sign.getBaseBoardNumber());
        if (inDB == null) {
            throw  new NotFoundException();
        }
        schedulerCompany.isHacking(inDB);

        return inDB;
    }

    public void save(Company sign) {
        String identityNumber=(sign.getBaseBoardNumber()+sign.getName()+sign.getPassword());
         sign.setIdentityCompany(identityNumber.replace(" ",""));
         sign.setFirstDate(sign.getCreatedDate());
        Company inDB = companyRepository.findByIdentityCompany(sign.getIdentityCompany());
        if (inDB == null) {
            fileService.createCompanyDirectory(sign.getName());
            companyRepository.save(sign);
        }
    }
    public Company getByName(String companyName){
        return companyRepository.findByName(companyName);
    }

    public void update(Company inDB) {
        companyRepository.save(inDB);
    }
    public Company findCompany(String name){
     return companyRepository.findByName(name);
    }
    public List<Company> getByCompany(String name){
        return companyRepository.getByName(name);
    }
}
