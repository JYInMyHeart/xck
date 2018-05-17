package jawa.classfiles.constant;

import jawa.classfiles.ClassReader;
import lombok.Getter;

import static jawa.Utils.Sth.getLongIndex;

/**
 * @author xck
 */
@Getter
public class ConstantLongInfo implements ConstantInfo{
    private long value;
    public void readInfo(ClassReader reader){
        value = getLongIndex(reader.readUint64());
    }
}