package jawa.classfiles.constant;

import jawa.classfiles.ClassReader;

import static jawa.utils.Sth.getShortIndex;

/**
 * @author xck
 */

public class ConstantNameAndTypeInfo implements ConstantInfo {
    private short nameIndex;
    private short descriptorIndex;

    public short getNameIndex() {
        return nameIndex;
    }

    public short getDescriptorIndex() {
        return descriptorIndex;
    }

    @Override
    public void readInfo(ClassReader reader) {
        nameIndex = getShortIndex(reader.readUint16());
        descriptorIndex = getShortIndex(reader.readUint16());
    }

    public String toString() {
        return "ConstantNameAndTypeInfo{" +
                "nameIndex=" + nameIndex +
                ", descriptorIndex=" + descriptorIndex +
                '}';
    }
}

