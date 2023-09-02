package com.example.intelGraphicsApi.entity.vm;


import com.example.intelGraphicsApi.entity.CommandLine;
import lombok.Data;


@Data
public class CommandVM {
    private String command;
    private boolean isResponsible;
    private long level;
    private long id;
    private boolean copyFile;

    public CommandVM(CommandLine commandLine) {
        this.setCommand(commandLine.getCommand());
        this.setResponsible(commandLine.isResponsible());
        this.setLevel(commandLine.getLevel());
        this.setId(commandLine.getId());
        this.setCopyFile(commandLine.isCopyFile());
    }
}
