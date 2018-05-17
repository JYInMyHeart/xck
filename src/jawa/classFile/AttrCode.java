package jawa.classFile;

import java.util.List;

public class AttrCode {
    static class CodeAttribute implements AttributeInfo{
        private ConstantPool cp;
        private int maxStack;
        private int maxLocals;
        private byte[] code;
        private List<ExceptionTableEntry> exceptionTable;
        private List<AttributeInfo> attributeInfos;

        @Override
        public void readInfo(ClassReader reader) {

        }
    }

    static class ExceptionTableEntry{
        private int startPc;
        private int endPc;
        private int handlerPc;
        private int catchType;


    }
}
