package jawa.classfiles.members.attributeinfos;

import jawa.classfiles.ClassReader;
import jawa.classfiles.constant.ConstantPool;
import jawa.classfiles.members.AttributeInfo;

import static jawa.Utils.Sth.getIntIndex;

/**
 * @author xck
 */
public class ConstantValueAttribute implements AttributeInfo {
    private ConstantPool cp;
    private int constantValueIndex;

    public ConstantValueAttribute(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        constantValueIndex = getIntIndex(reader.readUint16());
    }

    public String getConstantValue() {
        try {
            return cp.getUtf8(constantValueIndex);
        } catch (Exception e) {
            return "cant find the value";
        }
    }

    public int getConstantValueAttr() {
        return constantValueIndex;
    }

    public String toString() {
        return getConstantValue();
    }
}