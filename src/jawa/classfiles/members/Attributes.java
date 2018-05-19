package jawa.classfiles.members;


import jawa.classfiles.ClassReader;
import jawa.classfiles.constant.ConstantPool;
import jawa.classfiles.members.attributeinfos.*;

import java.util.ArrayList;
import java.util.List;


import static jawa.Utils.Sth.getIntIndex;

/**
 * @author xck
 */
public class Attributes {
    public static List<AttributeInfo> readAttributes(ClassReader reader, ConstantPool cp) throws Exception {
        byte[] attributesCount = reader.readUint16();
        List<AttributeInfo> attributeInfos = new ArrayList<AttributeInfo>();
        int attributesCountVal = getIntIndex(attributesCount);
        for (int i = 0; i < attributesCountVal; i++) {
            attributeInfos.add(readAttribute(reader, cp));
        }
        return attributeInfos;
    }

    public static AttributeInfo readAttribute(ClassReader reader, ConstantPool cp) throws Exception {
        byte[] attrNameIndex = reader.readUint16();
        String attrName = cp.getUtf8(getIntIndex(attrNameIndex));
        int attrLen = getIntIndex(reader.readUint32());
        AttributeInfo attrInfo = newAttributeInfo(attrName, attrLen, cp);
        attrInfo.readInfo(reader);
        return attrInfo;
    }

    public static AttributeInfo newAttributeInfo(String attrName, int attrLen, ConstantPool cp) {
        switch (attrName) {
            case "'Code'":
                return new CodeAttribute(cp);
            case "'ConstantValue'":
                return new ConstantValueAttribute(cp);
            case "'Deprecated'":
                return new DeprecatedAttribute();
            case "'Exceptions'":
                return new ExceptionsAttribute(cp);
            case "'LineNumberTable'":
                return new LineNumberTableAttribute();
            case "'LocalVariableTable'":
                return new LocalVariableTableAttribute(cp);
            case "'SourceFile'":
                return new SourceFileAttribute();
            case "'Synthetic'":
                return new SyntheticAttribute();
            default:
                return new UnparsedAttribute(attrName, attrLen);
        }
    }
}
