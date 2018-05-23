package jawa.classfiles.constant;

import jawa.classfiles.ClassReader;

import static jawa.Utils.Sth.getDoubleIndex;

/**
 * @author xck
 */

public class ConstantDoubleInfo implements ConstantInfo {
    private double value;

    public double getValue() {
        return value;
    }

    public void readInfo(ClassReader reader) {
        value = getDoubleIndex(reader.readUint64());
    }

    public String toString() {
        return "Double.value=" + value;
    }
}