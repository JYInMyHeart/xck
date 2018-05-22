package jawa.rtda.heap;

import jawa.classfiles.constant.ConstantMethodrefInfo;

import java.lang.reflect.Member;

public class MethodRef extends MemberRef {
    XMethod method;
    public static MethodRef newMethodRef(ConstantPool cp, ConstantMethodrefInfo refInfo) throws Exception {
        MethodRef methodRef = new MethodRef();
        methodRef.cp = cp;
        methodRef.copyMemerRefInfo(refInfo);
        return methodRef;
    }
}
