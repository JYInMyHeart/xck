package jawa.rtda.heap;

import jawa.classfiles.members.MemberInfo;

import static jawa.rtda.heap.ACCESS_FLAG.*;

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

    public boolean isAccessibleTo(XClass c) {
        if (isPublic())
            return true;
        XClass xClazz = xClass;
        if (isProtected())
            return c.getName().equals(xClazz.getName()) || c.isSubClassOf(xClazz) || xClazz.getPackageName().equals(c.getPackageName());
        if (!isPrivate()) {
            return xClazz.getPackageName().equals(c.getPackageName());
        }
        return c.getName().equals(xClazz.getName());
    }

    public XClass getxClass() {
        return xClass;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public String getName() {
        return name;
    }

    public String getDescroptor() {
        return descroptor;
    }

    public boolean isPublic() {
        return 0 != (accessFlags & ACC_PUBLIC.getValue());
    }

    public boolean isProtected() {
        return 0 != (accessFlags & ACC_PROTECTED.getValue());
    }

    public boolean isAbstract() {
        return 0 != (accessFlags & ACC_ABSTRACT.getValue());
    }

    public boolean isFinal() {
        return 0 != (accessFlags & ACC_FINAL.getValue());
    }

    public boolean isSuper() {
        return 0 != (accessFlags & ACC_SUPER.getValue());
    }

    public boolean isInterface() {
        return 0 != (accessFlags & ACC_INTERFACE.getValue());
    }

    public boolean isSynthetic() {
        return 0 != (accessFlags & ACC_SYNCHRONIZED.getValue());
    }

    public boolean isAnnotation() {
        return 0 != (accessFlags & ACC_ANNOTATION.getValue());
    }

    public boolean isEnum() {
        return 0 != (accessFlags & ACC_ENUM.getValue());
    }

    public boolean isPrivate() {
        return 0 != (accessFlags & ACC_PRIVATE.getValue());
    }
}
