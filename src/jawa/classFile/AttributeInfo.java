package jawa.classFile;

import java.util.ArrayList;
import java.util.List;

import static jawa.Utils.Sth.getIntIndex;
import static jawa.classFile.AttrMarkers.DeprecatedAttribute;
import static jawa.classFile.AttrMarkers.SyntheticAttribute;
import static jawa.classFile.AttrConstantValue.ConstantValueAttribute;
import static jawa.classFile.AttrCode.CodeAttribute;

public interface AttributeInfo {
    void readInfo(ClassReader reader);
    static List<AttributeInfo> readAttributes(ClassReader reader,ConstantPool cp){
        var attributesCount = reader.readUint16();
        var attributeInfos = new ArrayList<AttributeInfo>();
        var attributesCountVal = getIntIndex(attributesCount);
        for (int i = 0; i < attributesCountVal; i++) {
            attributeInfos.add(readAttribute(reader,cp));
        }
        return attributeInfos;
    }
    static AttributeInfo readAttribute(ClassReader reader,ConstantPool cp){
        var attrNameIndex = reader.readUint16();
        var attrName = cp.getUtf8(getIntIndex(attrNameIndex));
        var attrLen = reader.readUint32();
        var attrInfo = newAttributeInfo(attrName,attrLen,cp);
        attrInfo.readInfo(reader);
        return attrInfo;
    }
    static AttributeInfo newAttributeInfo(String attrName,int attrLen,ConstantPool cp){
        switch (attrName){
            case "Code":return new CodeAttribute();
            case "ConstantValue": return new ConstantValueAttribute();
            case "Deprecated": return new DeprecatedAttribute();
            case "Exceptions": return new ExceptionsAttribute();
            case "LineNumberTable": return new LineNumberTableAttribute();
            case "LocalVariableTable": return new LocalVariableTableAttribute();
            case "SourceFile": return new SourceFileAttribute();
            case "Synthetic": return new SyntheticAttribute();
            default: return new UnparsedAttribute(attrName, attrLen);
        }
    }


}
