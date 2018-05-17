package jawa.classFile;

import lombok.Getter;

import static jawa.Utils.Sth.*;

/**
 * @author xck
 */
public class CpNumeric {
    @Getter
    static class ConstantIntegerInfo implements ConstantInfo{
        private int value;
        public void readInfo(ClassReader reader){
            value = getIntIndex(reader.readUint32());
        }
    }
    @Getter
    static class ConstantFloatInfo implements ConstantInfo{
        private float value;
        public void readInfo(ClassReader reader){
            value = getFloatIndex(reader.readUint32());
        }
    }
    @Getter
    static class ConstantLongInfo implements ConstantInfo{
        private long value;
        public void readInfo(ClassReader reader){
            value = getLongIndex(reader.readUint64());
        }
    }
    @Getter
    static class ConstantDoubleInfo implements ConstantInfo{
        private double value;
        public void readInfo(ClassReader reader){
            value = getDoubleIndex(reader.readUint64());
        }
    }

}
