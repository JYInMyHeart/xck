package jawa.cmd;

import lombok.Data;

import java.util.Arrays;

public class Cmd {
    private Boolean helpFlag;
    private Boolean versionFlag;
    private String cpOption;
    private String className;
    private String[] args;
    private String xJreOption;

    @Override
    public String toString() {
        return "Cmd{" +
                "helpFlag=" + helpFlag +
                ", versionFlag=" + versionFlag +
                ", cpOption='" + cpOption + '\'' +
                ", className='" + className + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }

    public Boolean getHelpFlag() {
        return helpFlag;
    }

    public void setHelpFlag(Boolean helpFlag) {
        this.helpFlag = helpFlag;
    }

    public Boolean getVersionFlag() {
        return versionFlag;
    }

    public void setVersionFlag(Boolean versionFlag) {
        this.versionFlag = versionFlag;
    }

    public String getCpOption() {
        return cpOption;
    }

    public void setCpOption(String cpOption) {
        this.cpOption = cpOption;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public String getxJreOption() {
        return xJreOption;
    }

    public void setxJreOption(String xJreOption) {
        this.xJreOption = xJreOption;
    }


}
