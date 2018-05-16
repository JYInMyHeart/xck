package jawa.classFile;

import java.util.ArrayList;
import java.util.List;

public class MemberInfo {
    private ConstantPool cp;
    private String accessFlags;
    private String nameIndex;
    private String descriptorIndex;
    private AttributeInfo[] attributeInfos;

    public List<MemberInfo> readMembers(ClassReader reader, ConstantPool cp) {
        byte[] memberCount = reader.readUint16();
        List<MemberInfo> members = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(ClassFile.bytesToHexString(memberCount),10); i++) {
            members.add(readMember(reader,cp));
        }
        return members;
    }

    public MemberInfo readMember(ClassReader reader, ConstantPool cp) {
        this.cp = cp;
        accessFlags = ClassFile.bytesToHexString(reader.readUint16());
        nameIndex = ClassFile.bytesToHexString(reader.readUint16());
        descriptorIndex = ClassFile.bytesToHexString(reader.readUint16());
        attributeInfos = readAttributes(reader,cp);
    }

    public String getAccessFlags() {
        return accessFlags;
    }

    public String getName() {
        return cp.getUtf8(nameIndex);
    }

    public String getDescriptor() {
        return cp.getUtf8(descriptorIndex);
    }
}
