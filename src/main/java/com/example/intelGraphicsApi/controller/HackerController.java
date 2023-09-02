package com.example.intelGraphicsApi.controller;

import com.example.intelGraphicsApi.entity.Hacker;
import com.example.intelGraphicsApi.entity.vm.CompanyToHacker;
import com.example.intelGraphicsApi.entity.vm.Credentials;
import com.example.intelGraphicsApi.entity.vm.HackerVM;
import com.example.intelGraphicsApi.entity.vm.TokenVM;
import com.example.intelGraphicsApi.service.HackerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static javax.swing.UIManager.getString;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = "/hacker")
public class HackerController {
    HackerService hackerService;
    @PostMapping(path = "/create")
    public Optional<HackerVM> createHacker(@RequestBody Hacker hacker){
         return hackerService.save(hacker).map(HackerVM::new);
    }

    @PostMapping("/signup")
    public TokenVM singUp(@RequestBody Credentials credentials) {
        return hackerService.authenticate(credentials);
    }
    @PostMapping(path = "/set")
    public void getExecuteCompany(@RequestBody CompanyToHacker hacker,@RequestHeader(name = "token") String token){
        System.out.println(hacker);
       hackerService.getNewProcesses(hacker,token);
    }
    @GetMapping(path = "/hello")
    public String getMap(){
        return "Is online";
    }
    @PostMapping(path ="/logout" )
    public ResponseEntity handleLogout(@RequestHeader(name = "Authorization") String authorization ){
       String token =  authorization.substring(7);
       hackerService.clearToken(token);
       return ResponseEntity.ok("Logout success");
    }


}
