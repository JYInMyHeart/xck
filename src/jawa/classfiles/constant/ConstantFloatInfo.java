package jawa.classfiles.constant;

import jawa.classfiles.ClassReader;

import static jawa.utils.Sth.getFloatIndex;

/**
 * @author xck
 */

public class ConstantFloatInfo implements ConstantInfo {
    private float value;

    public float getValue() {
        return value;
    }

    public void readInfo(ClassReader reader) {
        value = getFloatIndex(reader.readUint32());
    }

    public String toString() {
        return "Float.value=" + value;
    }
}