package jawa.rtda.heap;

import jawa.classfiles.ClassFile;
import jawa.rtda.Slot;

import java.util.List;

import static jawa.rtda.heap.ACCESS_FLAG.*;
import static jawa.rtda.heap.ConstantPool.newConstatntPool;
import static jawa.rtda.heap.XFields.newFields;
import static jawa.rtda.heap.XMethod.newMethods;
import static jawa.utils.ClassNameHelper.getArrayClassName;

public class XClass {
    private int accessFlags;
    private String name;
    private String superClassName;
    private List<String> interfaceNames;
    private ConstantPool constantPool;
    private List<XFields> fields;
    private List<XMethod> methods;
    private XClassLoader loader;
    private XClass superClass;
    private XClass[] interfaces;
    private int instanceSlotCount;
    private int staticSlotCount;
    private Slot[] staticVars;
    private boolean initStarted;

    public static XClass newXClass(ClassFile cf) {
        XClass clazz = new XClass();
        try {
            clazz.accessFlags = cf.getAccessFlags();
            clazz.name = cf.getClassName();
            clazz.superClassName = cf.getSuperClassName();
            clazz.interfaceNames = cf.getInterfaces();
            clazz.interfaces = new XClass[cf.getInterfaces().size()];
            clazz.constantPool = newConstatntPool(clazz, cf.getConstantPool());
            clazz.fields = newFields(clazz, cf.getClassFileds());
            clazz.methods = newMethods(clazz, cf.getClassMethods());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public boolean isAssignableFrom(XClass xClass) {
        if (xClass.getName().equals(this.getName()))
            return true;
        if (!isInterface())
            return xClass.isSubClassOf(this);
        else
            return xClass.isImplements(this);
    }

    public int getInstanceSlotCount() {
        return instanceSlotCount;
    }

    public XObject newObject() {
        return new XObject(this);
    }

    public boolean isAccessibleTo(XClass other) {
        return isPublic() || getPackageName() == other.getPackageName();
    }

    public String getPackageName() {
        int i = name.lastIndexOf("/");
        if (i >= 0)
            return name.substring(0, i);
        return "";
    }

    public boolean isSubClassOf(XClass z) {
        return this.superClassName.equals(z.name);
    }
    public boolean isSuperClassOf(XClass z) {
        return z.superClassName.equals(this.name);
    }



    public boolean isImplements(XClass z) {
        for (int i = 0; i < this.getInterfaces().length; i++) {
            if (this.getInterfaces()[i].name.equals(z.name)
                    || this.getInterfaces()[i].isSubInterfaceOf(z))
                return true;
        }
        return false;
    }

    public boolean isSubInterfaceOf(XClass iface) {
        for (int i = 0; i < this.interfaces.length; i++) {
            if (this.getInterfaces()[i].getName().equals(iface.getName())
                    || this.getInterfaces()[i].isSubInterfaceOf(iface))
                return true;
        }
        return false;
    }

    public XMethod getMainMethod(XClass xClass) {
        return getStaticMethod("'main'", "'([Ljava/lang/String;)V'");
    }

    private XMethod getStaticMethod(String s, String s1) {
        for (XMethod m : this.getMethods()) {
            if (s.equals(m.getName()) && s1.equals(m.descroptor))
                return m;
        }
        return null;
    }

    public XClass getArrayClass(){
        String arrayClassName = getArrayClassName(name);
        return loader.loadClass(arrayClassName);
    }

    public void startInit(){
        initStarted = true;
    }

    public XMethod getClinitMethod(){
        return getStaticMethod("'<clinit>'","'()V'");
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public String getName() {
        return name;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public List<String> getInterfaceNames() {
        return interfaceNames;
    }

    public List<XFields> getFields() {
        return fields;
    }

    public List<XMethod> getMethods() {
        return methods;
    }

    public XClassLoader getLoader() {
        return loader;
    }

    public XClass getSuperClass() {
        return superClass;
    }

    public XClass[] getInterfaces() {
        return interfaces;
    }


    public boolean isInitStarted() {
        return initStarted;
    }

    public void setInitStarted(boolean initStarted) {
        this.initStarted = initStarted;
    }

    public int getStaticSlotCount() {
        return staticSlotCount;
    }

    public Slot[] getStaticVars() {
        return staticVars;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSuperClassName(String superClassName) {
        this.superClassName = superClassName;
    }

    public void setInterfaceNames(List<String> interfaceNames) {
        this.interfaceNames = interfaceNames;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void setFields(List<XFields> fields) {
        this.fields = fields;
    }

    public void setMethods(List<XMethod> methods) {
        this.methods = methods;
    }

    public void setLoader(XClassLoader loader) {
        this.loader = loader;
    }

    public void setSuperClass(XClass superClass) {
        this.superClass = superClass;
    }

    public void setInterfaces(XClass[] interfaces) {
        this.interfaces = interfaces;
    }

    public void setInstanceSlotCount(int instanceSlotCount) {
        this.instanceSlotCount = instanceSlotCount;
    }

    public void setStaticSlotCount(int staticSlotCount) {
        this.staticSlotCount = staticSlotCount;
    }

    public void setStaticVars(Slot[] staticVars) {
        this.staticVars = staticVars;
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
}
