package jawa.classFile;

import static jawa.Utils.Sth.getIntIndex;

public class AttrConstantValue {
    static class ConstantValueAttribute implements AttributeInfo{
        private int constantValueIndex;

        @Override
        public void readInfo(ClassReader reader) {
            constantValueIndex = getIntIndex(reader.readUint16());
        }
        public int getConstantValueIndex(){
            return constantValueIndex;
        }
    }
}
