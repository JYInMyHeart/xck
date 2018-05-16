package jawa.classFile;

/**
 * @author xck
 */
public class CpString {
    static class ConstantStringInfo{
        private ConstantPool cp;
        private short stringIndex;
        public void readInfo(ClassReader reader){
            stringIndex = Short.parseShort(ClassFile.bytesToHexString(reader.readUint16()));

        }

        public String toString() {
            return "ConstantStringInfo{" +
                    "stringIndex=" + stringIndex +
                    '}';
        }
        public String getString(){
            return cp.getUtf8(stringIndex);
        }
    }
}
