package com.example.intelGraphicsApi.controller;

import com.example.intelGraphicsApi.entity.vm.ResCommandVM;
import com.example.intelGraphicsApi.service.ResCommandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ResCommandController {
    ResCommandService resCommandService;
    @GetMapping(path = "/get-responsible-commands")
    public List<ResCommandVM> getResponsibleCommand(@RequestHeader(name = "token") String token, @RequestHeader(name = "companyName") String companyName){
          return resCommandService.getResCommands(companyName,token).stream().map(ResCommandVM::new).toList();
    }
    @DeleteMapping(path = "/delete-current-responsible-command")
    public Object deleteResponsibleCode(@RequestHeader String token,
                                        @RequestHeader String companyName){

            return resCommandService.deleteCommand(companyName,token);
    }

}
