package jawa.rtda.heap;

import jawa.classfiles.members.MemberInfo;

public class XClassMember {
    protected int accessFlags;
    protected String name;
    protected String descroptor;
    protected XClass xClass;

    public void copy(MemberInfo memberInfo) throws Exception {
        accessFlags = memberInfo.getAccessFlags();
        name = memberInfo.getName();
        descroptor = memberInfo.getDescriptor();
    }
}
