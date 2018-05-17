package jawa.classFile;

import java.util.List;
import java.util.Objects;

import static jawa.Utils.Sth.bytesToHexString;
import static jawa.Utils.Sth.getShortIndex;

public class ClassFile {
    private static final String MAGIC = "cafebabe";
    private String magic;
    private String minorVersion;
    private String majorVersion;
    private ConstantPool constantPool;
    private short accessFlags;
    private short thisClass;
    private short superClass;
    private List<String> interfaces;
    private List<MemberInfo> classFileds;
    private List<MemberInfo> classMethods;
    private List<AttributeInfo> attributeInfos;

    public ClassFile parse(byte[] classData){
        ClassReader cr = new ClassReader(classData);
        ClassFile cf = new ClassFile();
        cf.read(cr);
        return cf;
    }
    public ClassFile read(ClassReader reader){
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
        attributeInfos = AttributeInfo.readAttributes(reader,constantPool);
        return this;
    }

    private void readAndCheckVersion(ClassReader reader) {
        var minjorVersion = reader.readUint16();
        var majorVersion = reader.readUint16();
        var minjor = Integer.parseInt(Objects.requireNonNull(bytesToHexString(minjorVersion)),10);
        var major = Integer.parseInt(Objects.requireNonNull(bytesToHexString(majorVersion)),10);
        if(major <= 52 && major > 45 && minjor == 0) return;
        if(major == 45) return;
        throw new RuntimeException("java.lang.UnsupportedClassVersionError!");
    }

    private void readAndCheckMagic(ClassReader reader) {
        var magic = reader.readUint32();
        if(!MAGIC.equals(bytesToHexString(magic))){
            throw new RuntimeException("java.lang.ClassFormatError:magic!");
        }
    }

    public String getClassName(){
        return constantPool.getClassName(thisClass);
    }

    public String getSuperClassName(){
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
