package jawa.classFile;

/**
 * @author xck
 */
public class CpClass {
    static class ConstantClassInfo{
        private ConstantPool cp;
        private short nameIndex;
        public void readInfo(ClassReader reader){
            nameIndex = Short.parseShort(ClassFile.bytesToHexString(reader.readUint16()));
        }

        public String toString() {
            return "ConstantClassInfo{" +
                    "nameIndex=" + nameIndex +
                    '}';
        }
        public String getName(){
            return cp.getUtf8(nameIndex);
        }
    }
}
