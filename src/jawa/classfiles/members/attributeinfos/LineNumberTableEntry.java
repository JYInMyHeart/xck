package jawa.classfiles.members.attributeinfos;


import lombok.Data;

/**
 * @author xck
 */

public class LineNumberTableEntry {
    private short startPc;
    private short lineNumber;

    public short getStartPc() {
        return startPc;
    }

    public short getLineNumber() {
        return lineNumber;
    }

    public void setStartPc(short startPc) {
        this.startPc = startPc;
    }

    public void setLineNumber(short lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String toString() {
        return "startPc=" + startPc +
                ", lineNumber=" + lineNumber;
    }
}