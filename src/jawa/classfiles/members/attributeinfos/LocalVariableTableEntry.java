package jawa.classfiles.members.attributeinfos;


/**
 * @author xck
 */

public class LocalVariableTableEntry {
    private short startPc;
    private short length;
    private short nameIdnex;
    private short descriptorIndex;
    private short index;

    public short getNameIdnex() {
        return nameIdnex;
    }

    public void setNameIdnex(short nameIdnex) {
        this.nameIdnex = nameIdnex;
    }

    public short getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(short descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public short getIndex() {
        return index;
    }

    public void setIndex(short index) {
        this.index = index;
    }

    public short getStartPc() {
        return startPc;
    }

    public short getLength() {
        return length;
    }

    public void setStartPc(short startPc) {
        this.startPc = startPc;
    }

    public void setLength(short length) {
        this.length = length;
    }
    

    public String toString() {
        return "LocalVariableTableEntry{" +
                "startPc=" + startPc +
                ", length=" + length +
                ", nameIdnex=" + nameIdnex +
                ", descriptorIndex=" + descriptorIndex +
                ", index=" + index +
                '}';
    }
}