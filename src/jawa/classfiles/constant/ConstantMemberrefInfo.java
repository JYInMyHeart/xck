package jawa.classfiles.constant;

import jawa.classfiles.ClassReader;

import java.util.Map;

import static jawa.Utils.Sth.getShortIndex;

/**
 * @author xck
 */

public abstract class ConstantMemberrefInfo implements ConstantInfo {
    protected ConstantPool cp;
    protected short classIndex;
    protected short nameAndTypeIndex;

    public ConstantMemberrefInfo(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        classIndex = getShortIndex(reader.readUint16());
        nameAndTypeIndex = getShortIndex(reader.readUint16());
    }

    public String getClassName() throws Exception {
        return cp.getClassName(classIndex);
    }

    public Map<String, String> getNameAndType() throws Exception {
        return cp.getNameAndType(nameAndTypeIndex);
    }

    public String toString() {
        return "ConstantMemberrefInfo{" +
                "classIndex=" + classIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex +
                '}';
    }
}
