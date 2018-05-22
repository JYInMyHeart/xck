package jawa.rtda.heap;

import jawa.classfiles.constant.ConstantInterfaceMethodrefInfo;

public class InterfaceMethodRef extends MemberRef {
    XMethod xMethod;
    public static InterfaceMethodRef newInterfaceMethodRef(ConstantPool cp, ConstantInterfaceMethodrefInfo refInfo) throws Exception {
        InterfaceMethodRef ref = new InterfaceMethodRef();
        ref.cp = cp;
        ref.copyMemerRefInfo(refInfo);
        return ref;
    }
}
