package jawa.cmd;

import lombok.Data;

import java.util.Arrays;
@Data
public class Cmd {
    private Boolean helpFlag;
    private Boolean versionFlag;
    private String cpOption;
    private String classsName;
    private String[] args;
    private String xJreOption;

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
