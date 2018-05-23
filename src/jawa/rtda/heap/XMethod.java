package jawa.rtda.heap;

import jawa.classfiles.members.MemberInfo;
import jawa.classfiles.members.attributeinfos.CodeAttribute;

import java.util.ArrayList;
import java.util.List;

public class XMethod extends XClassMember {
    private int maxStack;
    private int maxLocals;
    private byte[] code;

    public static List<XMethod> newMethods(XClass xClass, List<MemberInfo> memberInfos) throws Exception {
        List<XMethod> xMethodList = new ArrayList<>();
        for (int i = 0; i < memberInfos.size(); i++) {
            XMethod m = new XMethod();
            m.xClass = xClass;
            m.copy(memberInfos.get(i));
            m.copyAttributes(memberInfos.get(i));
            xMethodList.add(m);
        }
        return xMethodList;
    }

    public void copyAttributes(MemberInfo memberInfo) {
        CodeAttribute codeAttribute = memberInfo.getCodeAttribute();
        if (codeAttribute != null) {
            maxStack = codeAttribute.getMaxStack();
            maxLocals = codeAttribute.getMaxLocals();
            code = codeAttribute.getCode();
        }
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public byte[] getCode() {
        return code;
    }
}
