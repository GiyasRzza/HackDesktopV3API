package com.example.intelGraphicsApi.controller;

import com.example.intelGraphicsApi.entity.Token;
import com.example.intelGraphicsApi.entity.vm.FileVM;
import com.example.intelGraphicsApi.service.CommandService;
import com.example.intelGraphicsApi.service.FileService;
import com.example.intelGraphicsApi.service.TokenService;

import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@RestController

@AllArgsConstructor
public class FileUploadController {
    FileService fileService;
    TokenService tokenService;

    CommandService commandService;
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestBody MultipartFile file,@RequestHeader(name = "name") String companyName, @RequestHeader(name = "id") String id ) {
        String fileName =file.getOriginalFilename();
            System.out.println(companyName+" tests");
        try {
            file.transferTo(new File("path"+companyName+"\\"+file.getOriginalFilename()));
           fileService.saveFile(fileName,companyName);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        commandService.deleteForFile(Long.parseLong(id));
        return ResponseEntity.ok("File uploaded successfully.");
    }

    @GetMapping("/image-hacker")
    public ResponseEntity<InputStreamResource> getImage(@RequestHeader(name = "token") String token,
                                                        @RequestHeader(name = "companyName") String companyName,
                                                        @RequestHeader(name = "fileName") String fileName) throws IOException {
        Optional<Token> currentToken =  tokenService.isToken(token);
        if (currentToken.isEmpty()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        String filePath = "path"+companyName+"\\"+fileName;

        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);

        String mimeType = Files.probeContentType(file.toPath());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .contentType(MediaType.parseMediaType(mimeType))
                .body(new InputStreamResource(inputStream));


    }
    @GetMapping(path = "/company-files")
    public List<FileVM> findByCompanyFile(@RequestHeader(name = "token") String token, @RequestHeader(name = "company") String companyName){
       Optional<Token> currentToken=  tokenService.isToken(token);
        if (currentToken.isEmpty()) {
           ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        return fileService.getCompanyFiles(companyName).stream().map(FileVM::new).toList();
    }
    @DeleteMapping(path = "/delete-current-file")
     public Object deleteFile(@RequestHeader String token,
                             @RequestHeader String companyName,
                             @RequestHeader String fileName){
         Optional<Token> currentToken=  tokenService.isToken(token);
         if (currentToken.isEmpty()) {
             ResponseEntity.status(HttpStatus.BAD_REQUEST);
         }
         String filePath = "path"+companyName+"\\"+fileName;
         File file = new File(filePath);
         file.delete();
         return fileService.deleteFile(fileName);
     }

}
