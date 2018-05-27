package jawa.classfiles.members.attributeinfos;

import jawa.classfiles.ClassReader;

import java.util.ArrayList;
import java.util.List;

import static jawa.utils.Sth.getShortIndex;

/**
 * @author xck
 */
public class ExceptionTableEntry {
    private short startPc;
    private short endPc;
    private short handlerPc;
    private short catchType;

    public List<ExceptionTableEntry> readExceptionTable(ClassReader reader) {
        short exceptionTableLength = getShortIndex(reader.readUint16());
        List<ExceptionTableEntry> exceptionTable = new ArrayList<ExceptionTableEntry>();
        for (int i = 0; i < exceptionTableLength; i++) {
            ExceptionTableEntry e = new ExceptionTableEntry();
            e.startPc = getShortIndex(reader.readUint16());
            e.endPc = getShortIndex(reader.readUint16());
            e.handlerPc = getShortIndex(reader.readUint16());
            e.catchType = getShortIndex(reader.readUint16());
            exceptionTable.add(e);
        }
        return exceptionTable;
    }

    public short getStartPc() {
        return startPc;
    }

    public void setStartPc(short startPc) {
        this.startPc = startPc;
    }

    public short getEndPc() {
        return endPc;
    }

    public void setEndPc(short endPc) {
        this.endPc = endPc;
    }

    public short getHandlerPc() {
        return handlerPc;
    }

    public void setHandlerPc(short handlerPc) {
        this.handlerPc = handlerPc;
    }

    public short getCatchType() {
        return catchType;
    }

    public void setCatchType(short catchType) {
        this.catchType = catchType;
    }

    public String toString() {
        return "startPc=" + startPc +
                ", endPc=" + endPc +
                ", handlerPc=" + handlerPc +
                ", catchType=" + catchType;
    }
}
