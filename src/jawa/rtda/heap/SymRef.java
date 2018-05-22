package jawa.rtda.heap;

public class SymRef implements Constant{
    ConstantPool cp;
    String className;
    XClass xClass;

    public XClass resolvedClass(){
        if(xClass == null)
            resolvedClassRef();
        return xClass;
    }

    public void resolvedClassRef(){
        XClass xClass = cp.getxClass();
        XClass xClazz = xClass.loader.loadClass(className);
        if(!xClazz.isAccessibleTo(xClass))
            throw new RuntimeException("java.lang.IllegalAccessError");
        this.xClass = xClazz;
    }



}
