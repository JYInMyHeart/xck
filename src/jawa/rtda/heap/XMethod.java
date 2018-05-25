package jawa.rtda.heap;

import jawa.classfiles.members.MemberInfo;
import jawa.classfiles.members.attributeinfos.CodeAttribute;

import java.util.ArrayList;
import java.util.List;

import static jawa.rtda.heap.XMethodDescriptor.MethodDescriptorParser.parserMethodDescriptor;

public class XMethod extends XClassMember {
    private int maxStack;
    private int maxLocals;
    private byte[] code;
    private int argSlotCount;

    public static List<XMethod> newMethods(XClass xClass, List<MemberInfo> memberInfos) throws Exception {
        List<XMethod> xMethodList = new ArrayList<>();
        for (int i = 0; i < memberInfos.size(); i++) {
            XMethod m = new XMethod();
            m.xClass = xClass;
            m.copy(memberInfos.get(i));
            m.copyAttributes(memberInfos.get(i));
            m.calcArgSlotCount();
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

    public void calcArgSlotCount(){
        XMethodDescriptor methodDescriptor = parserMethodDescriptor(descroptor);
        if(methodDescriptor.getParameterTypes() == null || methodDescriptor.getParameterTypes().size() == 0)
            return;
        for (String param:methodDescriptor.getParameterTypes()){
            argSlotCount++;
            if(param.equals("'J'") || param.equals("'D'"))
                argSlotCount++;
        }
        if(!isStatic())
            argSlotCount++;
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

    public int getArgSlotCount() {
        return argSlotCount;
    }

    public void setArgSlotCount(int argSlotCount) {
        this.argSlotCount = argSlotCount;
    }
}
