package jawa.classFile;

import lombok.Getter;

import static jawa.Utils.Sth.getShortIndex;

/**
 * @author xck
 */
public class CpNameAndTypeInfo {
    static class ConstantNameAndTypeInfo implements ConstantInfo{
        private short nameIndex;
        private short descriptorIndex;
        @Override
        public void readInfo(ClassReader reader){
            nameIndex = getShortIndex(reader.readUint16());
            descriptorIndex = getShortIndex(reader.readUint16());
        }

        public short getNameIndex() {
            return nameIndex;
        }

        public short getDescriptorIndex() {
            return descriptorIndex;
        }
    }
}
