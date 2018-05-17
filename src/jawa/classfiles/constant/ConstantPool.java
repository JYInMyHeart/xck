package jawa.classfiles.constant;


import jawa.classfiles.ClassReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jawa.Utils.Sth.bytesToHexString;
import static jawa.Utils.Sth.getIntIndex;
import static jawa.classfiles.constant.ConstantInfo.*;


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

    public ConstantPool readConstantPool(ClassReader reader) throws Exception {
        int cpCount = getIntIndex(reader.readUint16());
        List<ConstantInfo> cp = new ArrayList<ConstantInfo>();
        for (int i = 0; i < cpCount; i++) {
            cp.add(readConstantInfo(reader, this));
        }

        return new ConstantPool(cp);

    }

    public ConstantInfo getConstantInfo(int index) throws Exception {
        ConstantInfo cf = constantInfoList.get(index);
        if (cf != null) {
            return cf;
        }
        throw new Exception("invalid pool index");
    }

    public Map<String, String> getNameAndType(int index) throws Exception {
        ConstantNameAndTypeInfo ntInfo = (ConstantNameAndTypeInfo) getConstantInfo(index);
        String name = getUtf8(ntInfo.getNameIndex());
        String type = getUtf8(ntInfo.getDescriptorIndex());
        Map<String,String> map = new HashMap<String, String>();
        map.put(name, type);
        return map;
    }

    public String getClassName(int index) throws Exception {
        ConstantClassInfo classInfo = (ConstantClassInfo) getConstantInfo(index);
        return classInfo.getName();
    }

    public String getUtf8(int index) throws Exception {
        ConstantInfo utf8Info = getConstantInfo(index);
        return utf8Info.toString();
    }


    public ConstantInfo readConstantInfo(ClassReader reader, ConstantPool cp) throws Exception {
        byte[] tag = reader.readUint8();
        ConstantInfo c = newConstantInfo(tag, cp);
        c.readInfo(reader);
        return c;
    }

    public ConstantInfo newConstantInfo(byte[] tag, ConstantPool cp) throws Exception {
        String tagStr = bytesToHexString(tag);
        switch (Integer.parseInt(tagStr)) {
            case CONSTANT_Integer:
                return new ConstantIntegerInfo();
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
                throw new Exception("java.lang.ClassFormatError: constant pool tag!");
        }
    }
}
