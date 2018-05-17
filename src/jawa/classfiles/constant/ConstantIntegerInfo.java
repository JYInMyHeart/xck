package jawa.classfiles.constant;

import jawa.classfiles.ClassReader;
import lombok.Getter;

import static jawa.Utils.Sth.getIntIndex;

/**
 * @author xck
 */
@Getter
public class ConstantIntegerInfo implements ConstantInfo{
    private int value;
    public void readInfo(ClassReader reader){
        value = getIntIndex(reader.readUint32());
    }
}