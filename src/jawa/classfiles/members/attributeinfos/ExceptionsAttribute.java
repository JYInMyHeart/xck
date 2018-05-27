package jawa.classfiles.members.attributeinfos;

import jawa.utils.Sth;
import jawa.classfiles.ClassReader;
import jawa.classfiles.constant.ConstantPool;
import jawa.classfiles.members.AttributeInfo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xck
 */

public class ExceptionsAttribute implements AttributeInfo {
    private ConstantPool cp;
    private List<Short> exceptionIndexTable;

    public ExceptionsAttribute(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        exceptionIndexTable = reader.readUint16s().stream().map(Sth::getShortIndex).collect(Collectors.toList());
    }

    public List<Short> getExceptionIndexTable() {
        return exceptionIndexTable;
    }

    public List<String> getExceptionTable() {
        return exceptionIndexTable.stream().map(e -> {
            try {
                return cp.getUtf8(e);
            } catch (Exception e1) {
                return "exception cant find!";
            }
        }).collect(Collectors.toList());
    }

    public String toString() {
        return "\r\n            exceptionIndexTable=" + getExceptionTable();
    }
}
