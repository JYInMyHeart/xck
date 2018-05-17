package jawa.classFile;

import static jawa.Utils.Sth.getShortIndex;

/**
 * @author xck
 */
public class CpString {
    static class ConstantStringInfo implements ConstantInfo{
        private ConstantPool cp;
        private short stringIndex;
        public void readInfo(ClassReader reader){
            stringIndex = getShortIndex(reader.readUint16());

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
