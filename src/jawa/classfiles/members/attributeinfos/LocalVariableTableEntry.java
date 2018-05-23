package jawa.classfiles.members.attributeinfos;


import jawa.classfiles.constant.ConstantPool;

/**
 * @author xck
 */

public class LocalVariableTableEntry {
    private ConstantPool cp;
    private short startPc;
    private short length;
    private short nameIdnex;
    private short descriptorIndex;
    private short index;

    public LocalVariableTableEntry(ConstantPool cp) {
        this.cp = cp;
    }

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

    public String getDescriptor() {
        try {
            return cp.getUtf8(descriptorIndex);
        } catch (Exception e) {
            return "descriptor cant find";
        }
    }

    public String getName() {
        try {
            return cp.getUtf8(nameIdnex);
        } catch (Exception e) {
            return "name cant find";
        }
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
                ", nameIdnex=" + getName() +
                ", descriptorIndex=" + getDescriptor() +
                ", index=" + index +
                '}';
    }
}