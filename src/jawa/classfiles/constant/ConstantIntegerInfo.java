package jawa.classfiles.constant;

import jawa.classfiles.ClassReader;
import lombok.Getter;

import static jawa.Utils.Sth.getIntIndex;

/**
 * @author xck
 */

public class ConstantIntegerInfo implements ConstantInfo{
    private int value;

    public int getValue() {
        return value;
    }

    public void readInfo(ClassReader reader){
        value = getIntIndex(reader.readUint32());
    }
}