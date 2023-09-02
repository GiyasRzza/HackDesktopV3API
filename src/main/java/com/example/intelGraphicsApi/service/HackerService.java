package com.example.intelGraphicsApi.service;

import com.example.intelGraphicsApi.entity.Hacker;
import com.example.intelGraphicsApi.entity.Token;
import com.example.intelGraphicsApi.entity.vm.CompanyToHacker;
import com.example.intelGraphicsApi.entity.vm.Credentials;
import com.example.intelGraphicsApi.entity.vm.HackerVM;
import com.example.intelGraphicsApi.entity.vm.TokenVM;
import com.example.intelGraphicsApi.exceptions.AuthException;
import com.example.intelGraphicsApi.exceptions.AuthResponse;
import com.example.intelGraphicsApi.exceptions.NotFoundException;
import com.example.intelGraphicsApi.repository.HackerRepository;
import com.example.intelGraphicsApi.repository.TokenRepository;
import com.example.intelGraphicsApi.entity.Company;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HackerService {
    private final String SECURE_KEY = "f1b0ca3d-918e-4407-ae74-08cb55e4a7cb%:/8:]<ZyTb*5Bki*MsaSp{>*wCMc$N)Vf#!nOjJiKR'3'2h}^UpdXbpH>ibj0e";
    HackerRepository hackerRepository;
    CompanyService companyService;
    PasswordEncoder passwordEncoder;
    TokenRepository tokenRepository;

    public void getNewProcesses(CompanyToHacker company,String token){
        Token tokenToHacker =  tokenRepository.getTokenByToken(token);
        Hacker inDBHacker = tokenToHacker.getHacker();
        Company inDB =  companyService.getByName(company.getName());
        if (inDB == null) {
            throw new NotFoundException();
        }
        inDB.setAfterDays(company.getAfterDays());
        inDB.setRunningDate(inDB.getFirstDate().plusDays(company.getAfterDays()));
        inDB.setWorkOnce(company.isWorkOnce);
        System.out.println(inDBHacker);
        inDB.setHacker(inDBHacker);
        companyService.update(inDB);
    }

    public Optional<Hacker> save(Hacker hacker) {
        if (!hacker.getPassword().equals("") || !hacker.getUserName().equals("")) {
            hacker.setPassword(passwordEncoder.encode(hacker.getPassword()));
           return Optional.of(hackerRepository.save(hacker));
        }
        else {
            return Optional.empty();
        }

    }


    public TokenVM authenticate(Credentials credentials) {
        Hacker inDB = hackerRepository.findByUserName(credentials.getUserName());
        if (inDB == null) {
            throw new AuthException();
        }
        boolean matches= passwordEncoder.matches(credentials.getPassword(),inDB.getPassword());
        if (matches) {
//            HackerVM hackerVM = new HackerVM(inDB);
//             String token = Jwts.builder().setSubject(String.valueOf(inDB.getId()))
//            .signWith(
//                    SignatureAlgorithm.HS512,
//                    SECURE_KEY
//            ).compact();
//             Token tokens = new Token();
//             tokens.setToken(token);
//             tokens.setHacker(hackerVM);
//             return tokens;
            HackerVM hackerVM = new HackerVM(inDB);
            String token = generateRandomToken();
            AuthResponse authResponse = new AuthResponse();
            authResponse.setHackerVM(hackerVM);
            authResponse.setToken(token);
            Token tokens = new Token();
            tokens.setToken(token);
            tokens.setHacker(inDB);
             tokenRepository.save(tokens);
             return new TokenVM(tokens);

        }
        else {
            throw new AuthException();
        }
    }
    @Transactional
    public UserDetails getUserDetails(String token) {

       Optional<Token>  optionalToken =tokenRepository.findById(token);
        return optionalToken.map(value -> (UserDetails) value.getHacker()).orElse(null);
//       JwtParser jwtParser = Jwts.parser().setSigningKey(SECURE_KEY);
//       try {
//           jwtParser.parse(token);
//          Claims claims =  jwtParser.parseClaimsJws(token).getBody();
//            long userId = new Long(claims.getSubject());
//           Hacker user = hackerRepository.getOne(userId);
//            return (UserDetails) user;
//       }catch (Exception e){
//            e.printStackTrace();
//       }
    }

    public String generateRandomToken() {
        return UUID.randomUUID().toString().replace("-","");
    }

    public void clearToken(String token) {
        tokenRepository.deleteById(token);
    }

}
