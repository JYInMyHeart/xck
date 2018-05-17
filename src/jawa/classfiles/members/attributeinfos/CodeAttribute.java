package jawa.classfiles.members.attributeinfos;

import jawa.classfiles.ClassReader;
import jawa.classfiles.constant.ConstantPool;
import jawa.classfiles.members.AttributeInfo;
import jawa.classfiles.members.Attributes;
import lombok.Data;

import java.util.List;

import static jawa.Utils.Sth.getIntIndex;
import static jawa.Utils.Sth.getShortIndex;

/**
 * @author xck
 */
@Data
public class CodeAttribute implements AttributeInfo {
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
        int codeLength = getIntIndex(reader.readUint32());
        code = reader.readBytes(codeLength);
        exceptionTable = new ExceptionTableEntry().readExceptionTable(reader);
        try {
            attributeInfos = Attributes.readAttributes(reader,cp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}