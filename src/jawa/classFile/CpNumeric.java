package jawa.classFile;

/**
 * @author xck
 */
public class CpNumeric {
    static class ConstantIntegerInfo{
        private int value;
        public void readInfo(ClassReader reader){
            byte[] bytes = reader.readUint32();
            value = Integer.parseInt(ClassFile.bytesToHexString(bytes),10);
        }
    }
    static class ConstantFloatInfo{
        private float value;
        public void readInfo(ClassReader reader){
            byte[] bytes = reader.readUint32();
            value = Float.parseFloat(ClassFile.bytesToHexString(bytes));
        }
    }
    static class ConstantLongInfo{
        private long value;
        public void readInfo(ClassReader reader){
            byte[] bytes = reader.readUint64();
            value = Long.parseLong(ClassFile.bytesToHexString(bytes),10);
        }
    }
    static class ConstantDoubleInfo{
        private double value;
        public void readInfo(ClassReader reader){
            byte[] bytes = reader.readUint64();
            value = Double.parseDouble(ClassFile.bytesToHexString(bytes));
        }
    }

}
