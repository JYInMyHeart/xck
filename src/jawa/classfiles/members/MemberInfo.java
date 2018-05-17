package jawa.classfiles.members;

import jawa.classfiles.ClassReader;
import jawa.classfiles.constant.ConstantPool;


import java.util.ArrayList;
import java.util.List;

import static jawa.Utils.Sth.*;
import static jawa.classfiles.members.Attributes.readAttributes;

public class MemberInfo {
    private ConstantPool cp;
    private String accessFlags;
    private short nameIndex;
    private short descriptorIndex;
    private List<AttributeInfo> attributeInfos;

    public List<MemberInfo> readMembers(ClassReader reader, ConstantPool cp) throws Exception {
        byte[] memberCount = reader.readUint16();
        List<MemberInfo> members = new ArrayList<>();
        for (int i = 0; i < getIntIndex(memberCount); i++) {
            members.add(readMember(reader, cp));
        }
        return members;
    }

    public MemberInfo readMember(ClassReader reader, ConstantPool cp) throws Exception {
        this.cp = cp;
        accessFlags = bytesToHexString(reader.readUint16());
        nameIndex = getShortIndex(reader.readUint16());
        descriptorIndex = getShortIndex(reader.readUint16());
        attributeInfos = readAttributes(reader, cp);
        return this;
    }

    public String getAccessFlags() {
        return accessFlags;
    }

    public String getName() throws Exception {
        return cp.getUtf8(nameIndex);
    }

    public String getDescriptor() throws Exception {
        return cp.getUtf8(descriptorIndex);
    }
}
