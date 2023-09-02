package com.example.intelGraphicsApi.entity.vm;

import com.example.intelGraphicsApi.entity.ResponsibleCommand;
import lombok.Data;

@Data
public class ResCommandVM {
    public long id;
    public String responsibleCode;
    public String companyName;
    public Long commandLine;

    public ResCommandVM(ResponsibleCommand command) {
        this.id=command.getId();
        this.responsibleCode=command.getResponsibleCode();
        this.companyName=command.getCompany().getName();
        this.commandLine=command.getCommandLine();
    }
}
