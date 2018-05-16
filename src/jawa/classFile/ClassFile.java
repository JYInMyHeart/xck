package jawa.classFile;

import java.util.List;

public class ClassFile {
    private static final String MAGIC = "cafebabe";
    private String magic;
    private String minorVersion;
    private String majorVersion;
    private ConstantPool constantPool;
    private String accessFlags;
    private String thisClass;
    private String superClass;
    private List<String> interfaces;
    private MemberInfo[] classFileds;
    private MemberInfo[] classMethods;
    private AttributeInfo[] attributeInfos;

    public ClassFile parse(byte[] classData){
        ClassReader cr = new ClassReader(classData);
        ClassFile cf = new ClassFile();
        cf.read(fr);
        return cf;
    }
    public ClassFile read(ClassReader reader){
        readAndCheckMagic(reader);
        readAndCheckVersion(reader);
        constantPool = readConstantPool(reader);
        accessFlags = new String(reader.readUint16());
        thisClass = new String(reader.readUint16());
        superClass = new String(reader.readUint16());
        interfaces = reader.readUint16s();
        classFileds = readMeners(reader,this,constantPool);
        classMethods = readMeners(reader,this,constantPool);
        attributeInfos = readAttributes(reader,this.constantPool);
        return this;
    }

    private void readAndCheckVersion(ClassReader reader) {
        byte[] minjorVersion = reader.readUint16();
        byte[] majorVersion = reader.readUint16();
        int minjor = Integer.parseInt(bytesToHexString(minjorVersion),10);
        int major = Integer.parseInt(bytesToHexString(majorVersion),10);
        if(major <= 52 && major > 45 && minjor == 0) return;
        if(major == 45) return;
        throw new RuntimeException("java.lang.UnsupportedClassVersionError!");
    }

    private void readAndCheckMagic(ClassReader reader) {
        byte[] magic = reader.readUint32();
        if(!MAGIC.equals(bytesToHexString(magic))){
            throw new RuntimeException("java.lang.ClassFormatError:magic!");
        }
    }

    public String getClassName(){
        return constantPool.getClassName(thisClass);
    }

    public String getSuperClassName(){
        return constantPool.getSuperClassName(superClass);
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

    public String getAccessFlags() {
        return accessFlags;
    }

    public MemberInfo[] getClassFileds() {
        return classFileds;
    }

    public MemberInfo[] getClassMethods() {
        return classMethods;
    }

    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
