package jawa.rtda.heap;

import jawa.classfiles.constant.ConstantFieldrefInfo;
import jawa.classfiles.members.MemberInfo;

public class FieldRef extends MemberRef {
    XFields fields;
    public static FieldRef newFiledRef(ConstantPool cp, ConstantFieldrefInfo refInfo) throws Exception {
        FieldRef fieldRef = new FieldRef();
        fieldRef.cp = cp;
        fieldRef.copyMemerRefInfo(refInfo);
        return fieldRef;
    }
}
