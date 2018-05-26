package jawa.rtda.heap;

import jawa.classfiles.ClassReader;
import jawa.classfiles.constant.ConstantInfo;

public class SymRef implements ConstantInfo {
    ConstantPool cp;
    String className;
    XClass xClass;

    public XClass resolvedClass() {
        if (xClass == null)
            resolvedClassRef();
        return xClass;
    }

    public void resolvedClassRef() {
        XClass xClass = cp.getxClass();
        XClass xClazz = xClass.getLoader().loadClass(className);
        if (!xClazz.isAccessibleTo(xClass))
            throw new RuntimeException("java.lang.IllegalAccessError");
        this.xClass = xClazz;
    }


    @Override
    public void readInfo(ClassReader reader) {

    }

    public XClass getxClass() {
        return xClass;
    }
}
