package com.example.intelGraphicsApi.controller;
import com.example.intelGraphicsApi.entity.Company;
import com.example.intelGraphicsApi.entity.Token;
import com.example.intelGraphicsApi.entity.vm.CompanyVM;
import com.example.intelGraphicsApi.entity.vm.Entry;
import com.example.intelGraphicsApi.service.CompanyService;
import com.example.intelGraphicsApi.service.TokenService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(path = "/brsydrdylcyufbfurrmw/company/flhnnhstslpmxiplpdwl")
public class CompanyController {
    CompanyService companyService;
    TokenService tokenService;

    @PostMapping(path = "/signing/flhnnhstslpmxiplpdwl")
    public CompanyVM singIn(@RequestBody Entry sign) {
       return new CompanyVM(companyService.authenticate(sign));
    }

    @PostMapping(path = "/create/flhnnhstslpmxiplpdwl")
    public void createCompany(@RequestBody Company sign) {
        companyService.save(sign);
    }
    @GetMapping(path = "/get-current-company")
    public List<CompanyVM> getCompany(@RequestHeader String token, @RequestHeader String companyName ){
       Optional<Token> tokenOptional =  tokenService.isToken(token);
        if (tokenOptional.isEmpty()) {
            return null;
        }
        return companyService.getByCompany(companyName).stream().map(CompanyVM::new).toList();
    }


}
