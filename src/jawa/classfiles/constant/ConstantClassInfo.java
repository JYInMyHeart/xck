package jawa.classfiles.constant;

import jawa.classfiles.ClassReader;

import static jawa.Utils.Sth.getShortIndex;

/**
 * @author xck
 */

public class ConstantClassInfo implements ConstantInfo {
    private ConstantPool cp;
    private short nameIndex;

    public ConstantClassInfo(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        nameIndex = getShortIndex(reader.readUint16());
    }

    public String toString() {
        return "ConstantClassInfo{" +
                "nameIndex=" + nameIndex +
                '}';
    }

    public String getName() throws Exception {
        return cp.getUtf8(nameIndex);
    }
}

