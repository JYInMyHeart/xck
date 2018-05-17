package jawa.classfiles.constant;

import jawa.classfiles.ClassReader;

import java.util.Map;

import static jawa.Utils.Sth.getShortIndex;

/**
 * @author xck
 */

public abstract class ConstantMemberrefInfo implements ConstantInfo {
    private ConstantPool cp;
    private short classIndex;
    private short nameAndTypeIndex;

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

}
