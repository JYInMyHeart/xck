package jawa.classFile;

import java.util.ArrayList;
import java.util.List;

import static jawa.Utils.Sth.getIntIndex;
import static jawa.Utils.Sth.getShortIndex;
import static jawa.classFile.AttrCode.ExceptionTableEntry.readExceptionTable;

public class AttrCode {
    static class ExceptionTableEntry{
        private short startPc;
        private short endPc;
        private short handlerPc;
        private short catchType;
        public static List<ExceptionTableEntry> readExceptionTable(ClassReader reader){
            var exceptionTableLength = getShortIndex(reader.readUint16());
            var exceptionTable = new ArrayList<ExceptionTableEntry>();
            for (int i = 0;i < exceptionTableLength;i++) {
                var e = new ExceptionTableEntry();
                e.startPc = getShortIndex(reader.readUint16());
                e.endPc = getShortIndex(reader.readUint16());
                e.handlerPc = getShortIndex(reader.readUint16());
                e.catchType = getShortIndex(reader.readUint16());
                exceptionTable.add(e);
            }
            return exceptionTable;
        }


    }
    static class CodeAttribute implements AttributeInfo{
        private ConstantPool cp;
        private int maxStack;
        private int maxLocals;
        private byte[] code;
        private List<ExceptionTableEntry> exceptionTable;
        private List<AttributeInfo> attributeInfos;

        @Override
        public void readInfo(ClassReader reader) {
            maxStack = getShortIndex(reader.readUint16());
            maxLocals = getShortIndex(reader.readUint16());
            var codeLength = getIntIndex(reader.readUint32());
            code = reader.readBytes(codeLength);
            exceptionTable = readExceptionTable(reader);
            attributeInfos = AttributeInfo.readAttributes(reader,cp);
        }
    }


}
