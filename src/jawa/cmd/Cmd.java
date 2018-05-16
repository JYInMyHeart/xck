package jawa.cmd;

import java.util.Arrays;

public class Cmd {
    private Boolean helpFlag;
    private Boolean versionFlag;
    private String cpOption;
    private String classsName;
    private String[] args;
    private String xJreOption;

    public String getxJreOption() {
        return xJreOption;
    }

    public void setxJreOption(String xJreOption) {
        this.xJreOption = xJreOption;
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

    public String getClasssName() {
        return classsName;
    }

    public void setClasssName(String classsName) {
        this.classsName = classsName;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "Cmd{" +
                "helpFlag=" + helpFlag +
                ", versionFlag=" + versionFlag +
                ", cpOption='" + cpOption + '\'' +
                ", classsName='" + classsName + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
