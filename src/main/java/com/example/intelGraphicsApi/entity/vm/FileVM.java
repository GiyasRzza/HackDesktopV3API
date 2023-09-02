package com.example.intelGraphicsApi.entity.vm;

import com.example.intelGraphicsApi.entity.UpFiles;
import lombok.Data;

@Data
public class FileVM {
    public String fileName;
    public String companyName;

    public FileVM(UpFiles files) {
        this.companyName=files.getCompany().getName();
        this.fileName=files.getFileName();
    }
}
