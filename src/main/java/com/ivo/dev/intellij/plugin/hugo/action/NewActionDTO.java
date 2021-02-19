package com.ivo.dev.intellij.plugin.hugo.action;

public class NewActionDTO {

    private String fileName;
    private String arguments;

    private boolean createBundle;

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isCreateBundle() {
        return createBundle;
    }

    public void setCreateBundle(boolean createBundle) {
        this.createBundle = createBundle;
    }
}
