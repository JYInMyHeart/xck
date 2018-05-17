package jawa.classfiles;

import jawa.classfiles.members.AttributeInfo;
import jawa.classfiles.constant.ConstantPool;
import jawa.classfiles.members.MemberInfo;

import java.util.List;
import java.util.Objects;

import static jawa.Utils.Sth.bytesToHexString;
import static jawa.Utils.Sth.getShortIndex;
import static jawa.classfiles.members.Attributes.readAttributes;

public class ClassFile {
    private static final String MAGIC = "cafebabe";
    private String magic;
    private String minorVersion;
    private String majorVersion;
    private ConstantPool constantPool;
    private short accessFlags;
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
        accessFlags = getShortIndex(reader.readUint16());
        thisClass = getShortIndex(reader.readUint16());
        superClass = getShortIndex(reader.readUint16());
        interfaces = reader.readUint16s();
        MemberInfo memberInfo = new MemberInfo();
        classFileds = memberInfo.readMembers(reader,constantPool);
        classMethods = memberInfo.readMembers(reader,constantPool);
        attributeInfos = readAttributes(reader,constantPool);
        return this;
    }

    private void readAndCheckVersion(ClassReader reader) throws Exception {
        byte[] minorVersion = reader.readUint16();
        byte[] majorVersion = reader.readUint16();
        int minor = Integer.parseInt(Objects.requireNonNull(bytesToHexString(minorVersion)),10);
        int major = Integer.parseInt(Objects.requireNonNull(bytesToHexString(majorVersion)),10);
        if(major <= 52 && major > 45 && minor == 0) return;
        if(major == 45) return;
        throw new Exception("java.lang.UnsupportedClassVersionError!");
    }

    private void readAndCheckMagic(ClassReader reader) throws Exception {
        byte[] magic = reader.readUint32();
        if(!MAGIC.equals(bytesToHexString(magic))){
            throw new Exception("java.lang.ClassFormatError:magic!");
        }
    }

    public String getClassName() throws Exception {
        return constantPool.getClassName(thisClass);
    }

    public String getSuperClassName() throws Exception {
        return constantPool.getClassName(superClass);
    }

    public String getMinorVersion() {
        return minorVersion;
    }

    public String getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public short getAccessFlags() {
        return accessFlags;
    }

    public List<MemberInfo> getClassFileds() {
        return classFileds;
    }

    public List<MemberInfo> getClassMethods() {
        return classMethods;
    }

}
