package jawa.rtda.heap;

import jawa.classfiles.constant.ConstantFieldrefInfo;

public class XFieldRef extends MemberRef {
    XFields fields;

    public static XFieldRef newFiledRef(ConstantPool cp, ConstantFieldrefInfo refInfo) throws Exception {
        XFieldRef XFieldRef = new XFieldRef();
        XFieldRef.cp = cp;
        XFieldRef.copyMemerRefInfo(refInfo);
        return XFieldRef;
    }

    public XFields resolvedField() {
        if (fields == null)
            resolvedFieldRef();
        return fields;
    }

    public void resolvedFieldRef() {
        XClass xClass = cp.getxClass();
        XClass xClazz = resolvedClass();
        XFields fields = lookupField(xClazz, name, descriptor);
        if (fields == null)
            throw new RuntimeException("java.lang.NoSuchFieldError");
        if (!fields.isAccessibleTo(xClass))
            throw new RuntimeException("java.lang.IllegalAccessError");
        this.fields = fields;
    }

    public XFields lookupField(XClass xClass, String name, String descriptor) {
        for (int i = 0; i < xClass.getFields().size(); i++) {
            if (xClass.getFields().get(i).name == name && xClass.getFields().get(i).descroptor == descriptor)
                return xClass.getFields().get(i);
        }
        for (int i = 0; i < xClass.getInterfaces().length; i++) {
            XFields fields = lookupField(xClass.getInterfaces()[i], name, descriptor);
            if (fields != null)
                return fields;
        }
        if (xClass.getSuperClass() != null)
            return lookupField(xClass.getSuperClass(), name, descriptor);
        return null;
    }
}
