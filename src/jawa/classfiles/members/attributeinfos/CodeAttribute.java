package jawa.classfiles.members.attributeinfos;

import jawa.classfiles.ClassReader;
import jawa.classfiles.constant.ConstantPool;
import jawa.classfiles.members.AttributeInfo;
import jawa.classfiles.members.Attributes;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

import static jawa.Utils.Sth.getIntIndex;
import static jawa.Utils.Sth.getShortIndex;

/**
 * @author xck
 */

public class CodeAttribute implements AttributeInfo {
    private ConstantPool cp;
    private int maxStack;
    private int maxLocals;
    private byte[] code;
    private List<ExceptionTableEntry> exceptionTable;
    private List<AttributeInfo> attributeInfos;

    public CodeAttribute(ConstantPool cp) {
        this.cp = cp;
    }

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

    public ConstantPool getCp() {
        return cp;
    }

    public void setCp(ConstantPool cp) {
        this.cp = cp;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public List<ExceptionTableEntry> getExceptionTable() {
        return exceptionTable;
    }

    public void setExceptionTable(List<ExceptionTableEntry> exceptionTable) {
        this.exceptionTable = exceptionTable;
    }

    public List<AttributeInfo> getAttributeInfos() {
        return attributeInfos;
    }

    public void setAttributeInfos(List<AttributeInfo> attributeInfos) {
        this.attributeInfos = attributeInfos;
    }

    public String toString() {
        return "CodeAttribute{" +
                "maxStack=" + maxStack +
                ", maxLocals=" + maxLocals +
                ", code=" + Arrays.toString(code) +
                ", exceptionTable=" + exceptionTable +
                ", attributeInfos=" + attributeInfos +
                '}';
    }
}