package jawa.rtda.heap;

import jawa.classfiles.constant.ConstantMethodrefInfo;

public class MethodRef extends MemberRef {
    private XMethod method;

    public static MethodRef newMethodRef(ConstantPool cp, ConstantMethodrefInfo refInfo) throws Exception {
        MethodRef methodRef = new MethodRef();
        methodRef.cp = cp;
        methodRef.copyMemerRefInfo(refInfo);
        return methodRef;
    }



    public XMethod getMethod() {
        return method;
    }

    public void setMethod(XMethod method) {
        this.method = method;
    }
}
