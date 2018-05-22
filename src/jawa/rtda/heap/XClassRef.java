package jawa.rtda.heap;

import jawa.classfiles.constant.ConstantClassInfo;

public class XClassRef extends SymRef{
    public static XClassRef newClassRef(ConstantPool cp, ConstantClassInfo cfInfo){
        XClassRef ref = new XClassRef();
        ref.cp = cp;
        ref.className = cfInfo.getName();
        return ref;
    }
}
