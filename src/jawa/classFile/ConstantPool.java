package jawa.classFile;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jawa.Utils.Sth.*;
import static jawa.classFile.ConstantInfo.*;
import static jawa.classFile.CpClass.ConstantClassInfo;
import static jawa.classFile.CpMemberRef.*;
import static jawa.classFile.CpNameAndTypeInfo.ConstantNameAndTypeInfo;
import static jawa.classFile.CpNumeric.*;
import static jawa.classFile.CpString.ConstantStringInfo;
import static jawa.classFile.CpUtf8Info.ConstantUtf8Info;


/**
 * @author xck
 */
public class ConstantPool {
    private List<ConstantInfo> constantInfoList;

    public ConstantPool(List<ConstantInfo> constantInfoList) {
        this.constantInfoList = constantInfoList;
    }

    public ConstantPool() {
    }

    public ConstantPool readConstantPool(ClassReader reader) {
        var cpCount = reader.readUint16();
        var cp = new ArrayList<ConstantInfo>();
        for (int i = 0; i < getIntIndex(cpCount); i++) {
            cp.add(readConstantInfo(reader, this));
        }

        return new ConstantPool(cp);

    }

    public ConstantInfo getConstantInfo(int index) {
        var cf = constantInfoList.get(index);
        if (cf != null) {
            return cf;
        }
        throw new RuntimeException("invalid pool index");
    }

    public Map<String, String> getNameAndType(int index) {
        var ntInfo = (ConstantNameAndTypeInfo) getConstantInfo(index);
        var name = getUtf8(ntInfo.getNameIndex());
        var type = getUtf8(ntInfo.getDescriptorIndex());
        var map = new HashMap<String, String>();
        map.put(name, type);
        return map;
    }

    public String getClassName(int index) {
        var classInfo = (ConstantClassInfo) getConstantInfo(index);
        return classInfo.getName();
    }

    public String getUtf8(int index) {
        var utf8Info = getConstantInfo(index);
        return utf8Info.toString();
    }


    public ConstantInfo readConstantInfo(ClassReader reader, ConstantPool cp) {
        var tag = reader.readUint8();
        var c = newConstantInfo(tag, cp);
        c.readInfo(reader);
        return c;
    }

    public ConstantInfo newConstantInfo(byte[] tag, ConstantPool cp) {
        var tagStr = bytesToHexString(tag);
        switch (Integer.parseInt(tagStr)) {
            case CONSTANT_Integer:
                return new CpNumeric.ConstantIntegerInfo();
            case CONSTANT_Float:
                return new ConstantFloatInfo();
            case CONSTANT_Long:
                return new ConstantLongInfo();
            case CONSTANT_Double:
                return new ConstantDoubleInfo();
            case CONSTANT_Utf8:
                return new ConstantUtf8Info();
            case CONSTANT_String:
                return new ConstantStringInfo();
            case CONSTANT_Class:
                return new ConstantClassInfo();
            case CONSTANT_Fieldref:
                return new ConstantFieldrefInfo();
            case CONSTANT_Methodref:
                return new ConstantMethodrefInfo();
            case CONSTANT_InterfaceMethodref:
                return new ConstantInterfaceMethodrefInfo();
            case CONSTANT_NameAndType:
                return new ConstantNameAndTypeInfo();
            case CONSTANT_MethodType:
                return new ConstantMethodTypeInfo();
            case CONSTANT_MethodHandle:
                return new ConstantMethodHandleInfo();
            case CONSTANT_InvokeDynamic:
                return new ConstantInvokeDynamicInfo();
            default:
                throw new RuntimeException("java.lang.ClassFormatError: constant pool tag!");
        }
    }
    class ConstantMethodTypeInfo implements ConstantInfo{
        @Override
        public void readInfo(ClassReader reader) {

        }
    }
    class ConstantMethodHandleInfo implements ConstantInfo{
        @Override
        public void readInfo(ClassReader reader) {

        }
    }
    class ConstantInvokeDynamicInfo implements ConstantInfo{
        @Override
        public void readInfo(ClassReader reader) {

        }
    }
}
