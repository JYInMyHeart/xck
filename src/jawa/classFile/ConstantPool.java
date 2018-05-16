package jawa.classFile;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jawa.classFile.ConstantInfo.*;
import static jawa.classFile.CpClass.ConstantClassInfo;
import static jawa.classFile.CpMemberRef.*;
import static jawa.classFile.CpNameAndTypeInfo.ConstantNameAndTypeInfo;
import static jawa.classFile.CpNumeric.*;
import static jawa.classFile.CpString.ConstantStringInfo;


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
        byte[] cpCount = reader.readUint16();
        List<ConstantInfo> cp = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(ClassFile.bytesToHexString(cpCount), 10); i++) {
            cp.add(readConstantInfo(reader, cp));
        }

        return new ConstantPool(cp);

    }

    public ConstantInfo getConstantInfo(int index) {
        ConstantInfo cf = constantInfoList.get(index);
        if (cf != null) {
            return cf;
        }
        throw new RuntimeException("invalid pool index");
    }

    public Map<String, String> getNameAndType(int index) {
        ConstantNameAndTypeInfo ntInfo = getConstantInfo(index);
        String name = ntInfo.getUtf8(ntInfo.nameIndex);
        String type = ntInfo.getUtf8(ntInfo.descriptorIndex);
        Map<String, String> map = new HashMap<>();
        map.put(name, type);
        return map;
    }

    public String getClassName(int index) {
        ConstantInfo classInfo = getConstantInfo(index);
        return getUtf8(classInfo.nameIndex);
    }

    public String getUtf8(int index) {
        ConstantInfo utf8Info = getConstantInfo(index);
        return utf8Info.toString();
    }

    interface ConstantInfo {
        void readInfo(ClassReader reader);
    }

    public ConstantInfo readConstantInfo(ClassReader reader, ConstantPool cp) {
        byte[] tag = reader.readUint8();
        ConstantInfo c = newConstantInfo(tag, cp);
        c.readInfo(reader);
        return c;
    }

    public ConstantInfo newConstantInfo(byte[] tag, ConstantPool cp) {
        String tagStr = ClassFile.bytesToHexString(tag);
        switch (tagStr) {
            case CONSTANT_Integer:
                return new CpNumeric.ConstantIntegerInfo {
            }
            break;
            ;
            case CONSTANT_Float:
                return new ConstantFloatInfo {
            }
            ;
            break;
            case CONSTANT_Long:
                return new ConstantLongInfo {
            }
            ;
            break;
            case CONSTANT_Double:
                return new ConstantDoubleInfo {
            }
            ;
            break;
            case CONSTANT_Utf8:
                return new CpUtf8Info.ConstantUtf8Info {
            }
            ;
            break;
            case CONSTANT_String:
                return new ConstantStringInfo {
                cp:
                cp
            } ;
            break;
            case CONSTANT_Class:
                return new ConstantClassInfo {
                cp:
                cp
            } ;
            break;
            case CONSTANT_Fieldref:
                return new ConstantFieldrefInfo {
                ConstantMemberrefInfo {
                    cp:
                    cp
                }
            } ;
            break;
            case CONSTANT_Methodref:
                return new ConstantMethodrefInfo {
                ConstantMemberrefInfo {
                    cp:
                    cp
                }
            } ;
            break;
            case CONSTANT_InterfaceMethodref:
                return new ConstantInterfaceMethodrefInfo {
                ConstantMemberrefInfo {
                    cp:
                    cp
                }
            } ;
            break;
            case CONSTANT_NameAndType:
                return new ConstantNameAndTypeInfo {
            }
            ;
            break;
            case CONSTANT_MethodType:
                return new ConstantMethodTypeInfo {
            }
            ;
            break;
            case CONSTANT_MethodHandle:
                return new ConstantMethodHandleInfo {
            }
            ;
            break;
            case CONSTANT_InvokeDynamic:
                return new ConstantInvokeDynamicInfo {
            }
            ;
            break;
            default:
                throw new RuntimeException("java.lang.ClassFormatError: constant pool tag!");
                break;
        }
    }
}
