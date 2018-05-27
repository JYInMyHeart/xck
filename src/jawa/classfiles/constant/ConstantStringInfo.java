package jawa.classfiles.constant;

import jawa.classfiles.ClassReader;

import static jawa.utils.Sth.getShortIndex;

/**
 * @author xck
 */

public class ConstantStringInfo implements ConstantInfo {
    private ConstantPool cp;
    private short stringIndex;

    public void readInfo(ClassReader reader) {
        stringIndex = getShortIndex(reader.readUint16());

    }

    public String toString() {
        return "ConstantStringInfo{" +
                "stringIndex=" + stringIndex +
                '}';
    }

    public String getString() throws Exception {
        return cp.getUtf8(stringIndex);
    }
}

