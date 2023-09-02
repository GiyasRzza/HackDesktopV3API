package com.example.intelGraphicsApi.service;

import com.example.intelGraphicsApi.entity.Company;
import com.example.intelGraphicsApi.entity.UpFiles;
import com.example.intelGraphicsApi.repository.CompanyRepository;
import com.example.intelGraphicsApi.repository.FilesRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FileService {
    FilesRepository filesRepository;
    CompanyRepository companyService;
    public String generateRandomName(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    public void createCompanyDirectory(String name){
        try {
            Files.createDirectories(Paths.get("path"+name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveFile(String  files,String companyName){
        UpFiles inDB = new UpFiles();
        Company company = companyService.findByName(companyName);
        System.out.println(company);
        if (company != null) {
            inDB.setCompany(company);
            inDB.setFileName(files);
            filesRepository.save(inDB);
        }

    }

    public List<UpFiles> getCompanyFiles(String companyName ){
        return filesRepository.findByCompany_Name(companyName);
    }
    @Transactional
    public Object deleteFile(String fileName){
            return filesRepository.deleteByFileName(fileName);
    }



}
