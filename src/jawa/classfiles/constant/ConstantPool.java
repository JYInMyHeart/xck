package jawa.classfiles.constant;


import jawa.Utils.MyInt;
import jawa.classfiles.ClassReader;

import java.util.HashMap;
import java.util.Map;

import static jawa.Utils.Sth.getIntIndex;
import static jawa.Utils.Sth.getShortIndex;
import static jawa.classfiles.constant.ConstantInfo.*;


/**
 * @author xck
 */
public class ConstantPool {
    private ConstantInfo[] constantInfoList;

    public ConstantPool(ConstantInfo[] constantInfoList) {
        this.constantInfoList = constantInfoList;
    }

    public ConstantPool() {
    }

    public ConstantPool readConstantPool(ClassReader reader) throws Exception {
        int cpCount = getShortIndex(reader.readUint16());
        ConstantInfo[] cp = new ConstantInfo[cpCount];
        MyInt index = new MyInt(1);
        for (; index.getValue() < cpCount; index.increase()) {
//            System.out.print("i=" + index.getValue() + ",");
            cp[index.getValue()] = readConstantInfo(reader, new ConstantPool(cp), index);
        }
        return new ConstantPool(cp);

    }

    public ConstantInfo getConstantInfo(int index) throws Exception {
        ConstantInfo cf = constantInfoList[index];
        if (cf != null) {
            return cf;
        }
        throw new Exception("invalid pool index");
    }

    public Map<String, String> getNameAndType(int index) throws Exception {
        ConstantNameAndTypeInfo ntInfo = (ConstantNameAndTypeInfo) getConstantInfo(index);
        String name = getUtf8(ntInfo.getNameIndex());
        String type = getUtf8(ntInfo.getDescriptorIndex());
        Map<String, String> map = new HashMap<String, String>();
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


    public ConstantInfo readConstantInfo(ClassReader reader, ConstantPool cp, MyInt index) throws Exception {
//        int last = reader.getOffset();
        byte[] tag = reader.readUint8();
        ConstantInfo c = newConstantInfo(tag, cp, index);
        c.readInfo(reader);
//        System.out.println(Arrays.toString(tag) + "--->" + (reader.getOffset() - last));
        return c;
    }

    public ConstantInfo newConstantInfo(byte[] tag, ConstantPool cp, MyInt index) throws Exception {
        int tagStr = getIntIndex(tag);
        switch (tagStr) {
            case CONSTANT_Integer:
                return new ConstantIntegerInfo();
            case CONSTANT_Float:
                return new ConstantFloatInfo();
            case CONSTANT_Long:
                index.increase();
                return new ConstantLongInfo();
            case CONSTANT_Double:
                index.increase();
                return new ConstantDoubleInfo();
            case CONSTANT_Utf8:
                return new ConstantUtf8Info();
            case CONSTANT_String:
                return new ConstantStringInfo();
            case CONSTANT_Class:
                return new ConstantClassInfo(cp);
            case CONSTANT_Fieldref:
                return new ConstantFieldrefInfo(cp);
            case CONSTANT_Methodref:
                return new ConstantMethodrefInfo(cp);
            case CONSTANT_InterfaceMethodref:
                return new ConstantInterfaceMethodrefInfo(cp);
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

    public ConstantInfo[] getConstantInfoList() {
        return constantInfoList;
    }
}
