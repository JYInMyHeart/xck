package jawa.rtda.heap;

import jawa.classfiles.constant.ConstantMethodrefInfo;

import java.util.Optional;

public class MethodRef extends MemberRef {
    private XMethod method;

    public static MethodRef newMethodRef(ConstantPool cp, ConstantMethodrefInfo refInfo) throws Exception {
        MethodRef methodRef = new MethodRef();
        methodRef.cp = cp;
        methodRef.copyMemerRefInfo(refInfo);
        return methodRef;
    }



    public XMethod getMethod() {
        return method;
    }

    public void setMethod(XMethod method) {
        this.method = method;
    }

    public XMethod resolvedMethod(){
        if(method == null)
            resolvedMethodRef();
        return method;
    }

    public XMethod resolvedInterfaceMethod(){
        if(method == null)
            resolvedInterfaceMethodRef();
        return method;
    }

    public void resolvedInterfaceMethodRef(){
        XClass xClass = cp.getxClass();
        XClass xClazz = resolvedClass();
        if(!xClazz.isInterface())
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        XMethod method = lookupInterfaceMethod(xClazz,name,descriptor).orElse(null);
        if(method == null)
            throw new RuntimeException("java.lang.NoSuchMethodError");
        if(!method.isAccessibleTo(xClass))
            throw new RuntimeException("java.lang.IllegalAccessError");
        this.method = method;
    }

    public Optional<XMethod> lookupInterfaceMethod(XClass xClass,String name,String descriptor){
        for(int i = 0;i < xClass.getMethods().size();i++){
            if(xClass.getMethods().get(i).name.equals(name) && xClass.getMethods().get(i).descroptor.equals(descriptor)){
                return Optional.ofNullable(xClass.getMethods().get(i));
            }
        }
        return lookupMethodInInterfaces(xClass.getInterfaces(),name,descriptor);
    }

    public void resolvedMethodRef(){
        XClass xClass = cp.getxClass();
        XClass xClazz = resolvedClass();
        if(xClazz.isInterface())
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        XMethod method = lookupMethod(xClazz,name,descriptor).orElse(null);
        if(method == null)
            throw new RuntimeException("java.lang.NoSuchMethodError");
        if(!method.isAccessibleTo(xClass))
            throw new RuntimeException("java.lang.IllegalAccessError");
        this.method = method;
    }
    public Optional<XMethod> lookupMethod(XClass xClass,String name,String descriptor){
        XMethod method = lookupMethodInClass(xClass,name,descriptor).orElse(null);
        if(method == null)
            method = lookupMethodInInterfaces(xClass.getInterfaces(),name,descriptor).orElse(null);
        return Optional.ofNullable(method);
    }
    public Optional<XMethod> lookupMethodInClass(XClass xClass,String name,String descriptor){
        while(xClass.getSuperClass() != null){
            XClass xClazz = xClass.getSuperClass();
            for (int i = 0; i < xClass.getMethods().size(); i++) {
                if(name.endsWith(xClass.getMethods().get(i).name) && descriptor.endsWith(xClass.getMethods().get(i).descroptor)){
                    return Optional.ofNullable(xClass.getMethods().get(i));
                }
            }
            xClass = xClazz;
        }
        if(xClass.getName().equals("'java/lang/Object'")){
            for (int i = 0; i < xClass.getMethods().size(); i++) {
                if(name.endsWith(xClass.getMethods().get(i).name) && descriptor.endsWith(xClass.getMethods().get(i).descroptor)){
                    return Optional.ofNullable(xClass.getMethods().get(i));
                }
            }
        }
        return Optional.empty();
    }

    public Optional<XMethod> lookupMethodInInterfaces(XClass[] xClass,String name,String descriptor){
        for (int i = 0; i < xClass.length; i++) {
            for (int j = 0; j < xClass[i].getMethods().size(); j++) {
                if(xClass[i].getMethods().get(j).getName().equals(name)
                        && xClass[i].getMethods().get(j).getDescroptor().equals(descriptor)){
                    return Optional.ofNullable(xClass[i].getMethods().get(j));
                }
            }
            return lookupMethodInInterfaces(xClass[i].getInterfaces(),name,descriptor);
        }
        return Optional.empty();
    }
}
