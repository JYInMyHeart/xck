package jawa.classFile;

import static jawa.Utils.Sth.getShortIndex;

/**
 * @author xck
 */
public class CpClass {
    static class ConstantClassInfo implements ConstantInfo{
        private ConstantPool cp;
        private short nameIndex;
        @Override
        public void readInfo(ClassReader reader){
            nameIndex = getShortIndex(reader.readUint16());
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
