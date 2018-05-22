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
    public XFields resolvedField(){
        if(fields == null)
            resolvedFieldRef();
        return fields;
    }

    public void resolvedFieldRef(){
        XClass xClass = cp.getxClass();
        XClass xClazz = resolvedClass();
        XFields fields = lookupField(xClazz,name,descriptor);
        if(fields == null)
            throw new RuntimeException("java.lang.NoSuchFieldError");
        if(!fields.isAccessibleTo(xClass))
            throw new RuntimeException("java.lang.IllegalAccessError");
        this.fields = fields;
    }

    public XFields lookupField(XClass xClass,String name,String descriptor){
        for (int i = 0; i < xClass.fields.size(); i++) {
            if(xClass.fields.get(i).name == name && xClass.fields.get(i).descroptor == descriptor)
                return xClass.fields.get(i);
        }
        for (int i = 0; i < xClass.interfaces.length; i++) {
            XFields fields = lookupField(xClass.interfaces[i],name,descriptor);
            if(fields != null)
                return fields;
        }
        if(xClass.superClass != null)
            return lookupField(xClass.superClass,name,descriptor);
        return null;
    }
}
