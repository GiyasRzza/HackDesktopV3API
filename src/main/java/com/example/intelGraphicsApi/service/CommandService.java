package com.example.intelGraphicsApi.service;

import com.example.intelGraphicsApi.entity.*;
import com.example.intelGraphicsApi.entity.vm.Entry;
import com.example.intelGraphicsApi.entity.vm.StringCommand;
import com.example.intelGraphicsApi.repository.CommandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CommandService {
    CommandRepository commandRepository;
    CompanyService companyService;
    ResCommandService resCommandService;
    TokenService tokenService;
    public void save(StringCommand commandLine, String token) {
       Optional<Token> tokens = tokenService.isToken(token);
        if (tokens.isPresent()) {
            Company company =  companyService.findCompany(commandLine.getCompany());
            if (company == null) {
                throw new NullPointerException();
            }
            else {
                CommandLine inDB = new CommandLine();
                if (commandLine.getIsResponse().equals("true")) {
                    inDB.setResponsible(true);
                }
                inDB.setCommand(commandLine.getCommand());
                inDB.setCompany(company);
                inDB.setLevel(commandLine.getLevel());
                inDB.setCopyFile(commandLine.isCopyFile());
                commandRepository.save(inDB);

            }
        }


    }

    public List<CommandLine> getCommand(Entry entry) {
        Company inDB = companyService.authenticate(entry);
        if (entry.getResponsibleCode()!=null){
            ResponsibleCommand inDBCommand = resCommandService.findCommand(entry.getResponsibleCodeId());
            if (inDBCommand == null) {
                ResponsibleCommand responsibleCode  = new ResponsibleCommand();
                responsibleCode.setResponsibleCode(entry.getResponsibleCode());
                responsibleCode.setCompany(inDB);
                responsibleCode.setCommandLine(entry.getResponsibleCodeId());
                resCommandService.saveCommand(responsibleCode);
                commandRepository.deleteById(entry.getResponsibleCodeId());

            }

        }
        return commandRepository.findByCompany_IdOrderByLevel(inDB.getId());
    }
    public List<CommandLine> getCommandById(String company,String token){
        Optional<Token> tokens =  tokenService.isToken(token);
        if (tokens.isPresent()) {
            Company inDB = companyService.getByName(company);
            return commandRepository.findByCompany_IdOrderByLevel(inDB.getId());
        }
        else {
            throw new NullPointerException();
        }


    }
    public void deleteForFile(long id){
        commandRepository.deleteById(id);
    }
    public Optional<CommandLine> findCommand(long id){
        return commandRepository.findById(id);
    }
}
