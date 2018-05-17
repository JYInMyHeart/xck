package jawa.classfiles.members.attributeinfos;

import jawa.Utils.Sth;
import jawa.classfiles.ClassReader;
import jawa.classfiles.members.AttributeInfo;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xck
 */
@Getter
public class ExceptionsAttribute implements AttributeInfo {

    private List<Short> exceptionIndexTable;

    @Override
    public void readInfo(ClassReader reader) {
        exceptionIndexTable = reader.readUint16s().stream().map(Sth::getShortIndex).collect(Collectors.toList());
    }


}
