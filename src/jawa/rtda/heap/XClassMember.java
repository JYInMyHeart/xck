package jawa.rtda.heap;

import jawa.classfiles.members.MemberInfo;

import static jawa.rtda.heap.ACCESS_FLAG.*;

public class XClassMember extends AbstractAccessFlag{
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


}
