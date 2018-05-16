package jawa.classFile;

/**
 * @author xck
 */
public class CpNameAndTypeInfo {
    static class ConstantNameAndTypeInfo{
        private short nameIndex;
        private short descriptorIndex;
        public void readInfo(ClassReader reader){
            nameIndex = Short.parseShort(ClassFile.bytesToHexString(reader.readUint16()));
            descriptorIndex = Short.parseShort(ClassFile.bytesToHexString(reader.readUint16()));

        }
    }
}
