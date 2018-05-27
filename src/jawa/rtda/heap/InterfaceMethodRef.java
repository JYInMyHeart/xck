package jawa.rtda.heap;

import jawa.classfiles.constant.ConstantInterfaceMethodrefInfo;

public class InterfaceMethodRef extends MethodRef {
    private XMethod xMethod;

    public static InterfaceMethodRef newInterfaceMethodRef(ConstantPool cp, ConstantInterfaceMethodrefInfo refInfo) throws Exception {
        InterfaceMethodRef ref = new InterfaceMethodRef();
        ref.cp = cp;
        ref.copyMemerRefInfo(refInfo);
        return ref;
    }


    public XMethod getxMethod() {
        return xMethod;
    }

    public void setxMethod(XMethod xMethod) {
        this.xMethod = xMethod;
    }
}
