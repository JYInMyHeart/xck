package jawa.classFile;

public class MemberInfo {
    private ConstantPool cp;
    private String accessFlags;
    private String nameIndex;
    private String descriptorIndex;
    private AttributeInfo[] attributeInfos;

    public MemberInfo[] readMembers(ClassReader reader, ConstantPool cp) {

    }

    public MemberInfo readMember(ClassReader reader, ConstantPool cp) {


    }

    public String getAccessFlags() {
        return accessFlags;
    }

    public String getName() {

    }

    public String getDescriptor() {

    }
}
