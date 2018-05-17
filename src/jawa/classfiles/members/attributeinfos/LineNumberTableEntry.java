package jawa.classfiles.members.attributeinfos;


import lombok.Data;

/**
 * @author xck
 */
@Data
public class LineNumberTableEntry {
    private short startPc;
    private short lineNumber;

}