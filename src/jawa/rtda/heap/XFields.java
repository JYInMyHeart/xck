package jawa.rtda.heap;

import jawa.classfiles.members.MemberInfo;
import jawa.classfiles.members.attributeinfos.ConstantValueAttribute;

import java.util.ArrayList;
import java.util.List;

import static jawa.rtda.heap.ACCESS_FLAG.*;

public class XFields extends XClassMember {
    private int slotId;
    private int constValueIndex;

    public static List<XFields> newFields(XClass xClass, List<MemberInfo> classFields) throws Exception {
        List<XFields> xFieldsList = new ArrayList<>();
        for (int i = 0; i < classFields.size(); i++) {
            XFields x = new XFields();
            x.xClass = xClass;
            x.copy(classFields.get(i));
            x.copyAttributes(classFields.get(i));
            xFieldsList.add(x);
        }
        return xFieldsList;
    }

    public void copyAttributes(MemberInfo memberInfo) {
        if(memberInfo.getConstantValueAttribute().isPresent()){
            ConstantValueAttribute valueAttribute = memberInfo.getConstantValueAttribute().get();
            constValueIndex = valueAttribute.getConstantValueAttr();
        }
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public void setConstValueIndex(int constValueIndex) {
        this.constValueIndex = constValueIndex;
    }

    public int getSlotId() {
        return slotId;
    }

    public int getConstValueIndex() {
        return constValueIndex;
    }

    public boolean isPublic() {
        return 0 != (accessFlags & ACC_PUBLIC.getValue());
    }

    public boolean isPrivate() {
        return 0 != (accessFlags & ACC_PRIVATE.getValue());
    }

    public boolean isProtected() {
        return 0 != (accessFlags & ACC_PROTECTED.getValue());
    }

    public boolean isStatic() {
        return 0 != (accessFlags & ACC_STATIC.getValue());
    }

    public boolean isFinal() {
        return 0 != (accessFlags & ACC_FINAL.getValue());
    }

    public boolean isSynthetic() {
        return 0 != (accessFlags & ACC_SYNTHETIC.getValue());
    }

    public boolean isVolatile() {
        return 0 != (accessFlags & ACC_VOLATILE.getValue());
    }

    public boolean isTransient() {
        return 0 != (accessFlags & ACC_TRANSIENT.getValue());
    }

    public boolean isEnum() {
        return 0 != (accessFlags & ACC_ENUM.getValue());
    }

    public boolean isLongOrDouble() {
        return "J".equals(descroptor) || "D".equals(descroptor);
    }

}
