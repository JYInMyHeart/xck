package jawa.rtda.heap;

import jawa.classfiles.ClassFile;
import jawa.rtda.Slot;

import java.util.List;

import static jawa.rtda.heap.ACCESS_FLAG.*;
import static jawa.rtda.heap.ConstantPool.newConstatntPool;
import static jawa.rtda.heap.XFields.newFields;
import static jawa.rtda.heap.XMethod.newMethods;

public class XClass {
    private int accessFlags;
    String name;
    String superClassName;
    List<String> interfaceNames;
    ConstantPool constantPool;
    List<XFields> fields;
    List<XMethod> methods;
    XClassLoader loader;
    XClass superClass;
    XClass[] interfaces;
    int instanceSlotCount;
    int staticSlotCount;
    Slot[] staticVars;

    public static XClass newXClass(ClassFile cf){
        XClass clazz = new XClass();
        try {
        clazz.accessFlags = cf.getAccessFlags();
        clazz.name = cf.getClassName();
        clazz.superClassName = cf.getSuperClassName();
        clazz.interfaceNames = cf.getInterfaces();

            clazz.constantPool = newConstatntPool(clazz,cf.getConstantPool());
            clazz.fields = newFields(clazz,cf.getClassFileds());
            clazz.methods = newMethods(clazz,cf.getClassMethods());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public boolean isAccessibleTo(XClass other){
        return isPublic() || getPackageName() == other.getPackageName();
    }

    public String getPackageName(){
        int i = name.lastIndexOf("/");
        if(i >= 0)
            return name.substring(0,i);
        return "";
    }

    public boolean isPublic(){
        return 0 != (accessFlags & ACC_PUBLIC.getValue());
    }
    public boolean isProtected(){
        return 0 != (accessFlags & ACC_PROTECTED.getValue());
    }
    public boolean isAbstract(){
        return 0 != (accessFlags & ACC_ABSTRACT.getValue());
    }
    public boolean isFinal(){
        return 0 != (accessFlags & ACC_FINAL.getValue());
    }
    public boolean isSuper(){
        return 0 != (accessFlags & ACC_SUPER.getValue());
    }
    public boolean isInterface(){
        return 0 != (accessFlags & ACC_INTERFACE.getValue());
    }
    public boolean isSynthetic(){
        return 0 != (accessFlags & ACC_SYNCHRONIZED.getValue());
    }
    public boolean isAnnotation(){
        return 0 != (accessFlags & ACC_ANNOTATION.getValue());
    }
    public boolean isEnum(){
        return 0 != (accessFlags & ACC_ENUM.getValue());
    }
}
