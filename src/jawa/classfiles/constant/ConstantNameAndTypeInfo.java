package jawa.classfiles.constant;

import jawa.classfiles.ClassReader;
import lombok.Getter;

import static jawa.Utils.Sth.getShortIndex;

/**
 * @author xck
 */
@Getter
public class ConstantNameAndTypeInfo implements ConstantInfo {
    private short nameIndex;
    private short descriptorIndex;

    @Override
    public void readInfo(ClassReader reader) {
        nameIndex = getShortIndex(reader.readUint16());
        descriptorIndex = getShortIndex(reader.readUint16());
    }


}

