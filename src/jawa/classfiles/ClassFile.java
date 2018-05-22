package jawa.classfiles;

import jawa.Utils.Sth;
import jawa.classfiles.members.AttributeInfo;
import jawa.classfiles.constant.ConstantPool;
import jawa.classfiles.members.MemberInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static jawa.Utils.Sth.bytesToIntHexString;
import static jawa.Utils.Sth.getIntIndex;
import static jawa.Utils.Sth.getShortIndex;
import static jawa.classfiles.members.Attributes.readAttributes;

public class ClassFile {
    private static final String MAGIC = "cafebabe";
    private String magic;
    private int minorVersion;
    private int majorVersion;
    private ConstantPool constantPool;
    private int accessFlags;
    private short thisClass;
    private short superClass;
    private List<byte[]> interfaces;
    private List<MemberInfo> classFileds;
    private List<MemberInfo> classMethods;
    private List<AttributeInfo> attributeInfos;

    public ClassFile parse(byte[] classData) throws Exception {
        ClassReader cr = new ClassReader(classData);
        ClassFile cf = new ClassFile();
        cf.read(cr);
        return cf;
    }
    public ClassFile read(ClassReader reader) throws Exception {
        readAndCheckMagic(reader);
        readAndCheckVersion(reader);
        ConstantPool c = new ConstantPool();
        constantPool = c.readConstantPool(reader);
        accessFlags = getIntIndex(reader.readUint16());
        thisClass = getShortIndex(reader.readUint16());
        superClass = getShortIndex(reader.readUint16());
        interfaces = reader.readUint16s();
        classFileds = MemberInfo.readMembers(reader,constantPool);
        classMethods = MemberInfo.readMembers(reader,constantPool);
        attributeInfos = readAttributes(reader,constantPool);
        return this;
    }

    private void readAndCheckVersion(ClassReader reader) throws Exception {
        byte[] minorVersion = reader.readUint16();
        byte[] majorVersion = reader.readUint16();
        this.minorVersion = getIntIndex(minorVersion);
        this.majorVersion = getIntIndex(majorVersion);
        if(this.majorVersion <= 53 && this.majorVersion > 45 && this.minorVersion == 0) return;
        if(this.majorVersion == 45) return;
        throw new Exception("java.lang.UnsupportedClassVersionError!");
    }

    private void readAndCheckMagic(ClassReader reader) throws Exception {
        byte[] magic = reader.readUint32();
        if(!MAGIC.equals(bytesToIntHexString(magic))){
            throw new Exception("java.lang.ClassFormatError:magic!");
        }
    }

    public String getClassName() throws Exception {
        return constantPool.getClassName(thisClass);
    }

    public String getSuperClassName() throws Exception {
        return constantPool.getClassName(superClass);
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public List<MemberInfo> getClassFileds() {
        return classFileds;
    }

    public List<MemberInfo> getClassMethods() {
        return classMethods;
    }

    public List<String> getInterfaces() {
        List<String> list = interfaces.stream().map(e -> {
            try {
                return constantPool.getClassName(getShortIndex(e));
            } catch (Exception e1) {
                e1.printStackTrace();
                return "error";
            }
        }).collect(Collectors.toList());
        return list;
    }
}
