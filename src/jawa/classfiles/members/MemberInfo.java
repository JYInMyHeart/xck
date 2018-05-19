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

    public static List<MemberInfo> readMembers(ClassReader reader, ConstantPool cp) throws Exception {
        byte[] memberCount = reader.readUint16();
        List<MemberInfo> members = new ArrayList<>();
        for (int i = 0; i < getIntIndex(memberCount); i++) {
            MemberInfo m = readMember(reader, cp);
            members.add(m);
        }
        return members;
    }

    public static MemberInfo readMember(ClassReader reader, ConstantPool cp) throws Exception {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.cp = cp;
        memberInfo.accessFlags = bytesToIntHexString(reader.readUint16());
        memberInfo.nameIndex = getShortIndex(reader.readUint16());
        memberInfo.descriptorIndex = getShortIndex(reader.readUint16());
        memberInfo.attributeInfos = readAttributes(reader, cp);
        return memberInfo;
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

    public String toString() {
        try {
            return "MemberInfo{" +
                    "accessFlags='" + getAccessFlags() + '\'' +
                    ", nameIndex=" + getName() +
                    ", descriptorIndex=" + getDescriptor() +
                    ", attributeInfos=" + attributeInfos +
                    '}';
        } catch (Exception e) {
            e.printStackTrace();
            return "fatal error!";
        }
    }
}
