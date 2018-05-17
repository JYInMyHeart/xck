package jawa.classfiles.members.attributeinfos;

import jawa.classfiles.ClassReader;
import jawa.classfiles.members.AttributeInfo;

import static jawa.Utils.Sth.getIntIndex;

/**
 * @author xck
 */
public class ConstantValueAttribute implements AttributeInfo {
    private int constantValueIndex;

    @Override
    public void readInfo(ClassReader reader) {
        constantValueIndex = getIntIndex(reader.readUint16());
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }
}