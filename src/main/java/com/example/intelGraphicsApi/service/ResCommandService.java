package com.example.intelGraphicsApi.service;

import com.example.intelGraphicsApi.entity.Company;
import com.example.intelGraphicsApi.entity.ResponsibleCommand;
import com.example.intelGraphicsApi.entity.Token;
import com.example.intelGraphicsApi.repository.CompanyRepository;
import com.example.intelGraphicsApi.repository.ResCommandRepository;
import com.example.intelGraphicsApi.repository.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResCommandService {
ResCommandRepository commandRepository;
CompanyRepository companyService;
TokenRepository tokenService;
  public void saveCommand(ResponsibleCommand responsibleCode){
      commandRepository.save(responsibleCode);
  }
  public ResponsibleCommand findCommand(long id){
       return commandRepository.findByCommandLine(id);
  }

        public List<ResponsibleCommand> getResCommands(String companyName,String token){
           Optional<Token> optionalToken = tokenService.findById(token);
           if (optionalToken.isEmpty()){
               return null;
           }
            Company company= companyService.findByName(companyName);
            return commandRepository.findByCompany_Id(company.getId());
        }
        @Transactional
        public  Object  deleteCommand (String companyName, String token){
            Optional<Token> optionalToken = tokenService.findById(token);
            if (optionalToken.isEmpty()){
                return null;
            }
            Company company= companyService.findByName(companyName);
           return  commandRepository.deleteByCompany(company);

        }
}
