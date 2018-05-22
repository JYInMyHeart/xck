package jawa.rtda.heap;

import jawa.classfiles.ClassFile;
import jawa.classpath.Classpath;
import jawa.rtda.Slot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static jawa.rtda.heap.XClass.newXClass;

public class XClassLoader {
    Classpath cp;
    Map<String, XClass> classMap;

    public XClassLoader(Classpath cp, Map<String, XClass> classMap) {
        this.cp = cp;
        this.classMap = classMap;
    }

    public static XClassLoader newClassLoader(Classpath cp) {
        return new XClassLoader(cp, new HashMap<>());
    }

    public XClass loadClass(String name) {
        if (classMap.containsKey(name)) {
            return classMap.get(name);
        }
        return loadNonArrayClass(name);
    }

    public XClass loadNonArrayClass(String name) {
        byte[] data = readClass(name).get();
        XClass xclass = null;
        try {
            xclass = defineClass(data).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        link(xclass);
        System.out.println(String.format("Loaded %s from %s", name, cp));
        return xclass;
    }

    public Optional<byte[]> readClass(String name) {
        byte[] data = cp.readClass(name);
        return Optional.ofNullable(data);
    }

    public Optional<XClass> defineClass(byte[] data) throws Exception {
        XClass xClass = parseClass(data).get();
        xClass.loader = this;
        resolveSuperClass(xClass);
        resolveInterfaces(xClass);
        classMap.put(xClass.name, xClass);
        return Optional.ofNullable(xClass);
    }

    public Optional<XClass> parseClass(byte[] data) throws Exception {
        ClassFile cf = new ClassFile().parse(data);
        return Optional.ofNullable(newXClass(cf));
    }

    public void resolveSuperClass(XClass xClass) {
        if (!Objects.equals(xClass.name, "'java/lang/Object'"))
            xClass.superClass = xClass.loader.loadClass(xClass.superClassName);
    }

    public void resolveInterfaces(XClass xClass) {
        int interfaceCount = xClass.interfaceNames.size();
        if (interfaceCount > 0) {
            for (int i = 0; i < interfaceCount; i++) {
                xClass.interfaces[i] = xClass.loader.loadClass(xClass.interfaceNames.get(i));
            }
        }
    }

    public void link(XClass xClass) {
        verify(xClass);
        prepare(xClass);
    }

    public void verify(XClass xclass) {

    }

    public void prepare(XClass xClass) {
        calcInstanceFieldSlotIds(xClass);
        calcStaticFieldSlotIds(xClass);
        allocAndInitStaticVars(xClass);
    }

    public void calcInstanceFieldSlotIds(XClass xClass) {
        int slotId = 0;
        if (xClass.superClass != null) {
            slotId = xClass.superClass.instanceSlotCount;
        }
        for (int i = 0; i < xClass.fields.size(); i++) {
            if (!xClass.fields.get(i).isStatic()) {
                xClass.fields.get(i).slotId = slotId;
                slotId++;
                if (xClass.fields.get(i).isLongOrDouble())
                    slotId++;
            }
        }
        xClass.instanceSlotCount = slotId;
    }

    public void calcStaticFieldSlotIds(XClass xClass) {
        int slotId = 0;
        for (int i = 0; i < xClass.fields.size(); i++) {
            if (xClass.fields.get(i).isStatic()) {
                xClass.fields.get(i).slotId = slotId;
                slotId++;
                if (xClass.fields.get(i).isLongOrDouble())
                    slotId++;
            }
        }
        xClass.staticSlotCount = slotId;
    }

    public void allocAndInitStaticVars(XClass xClass) {
        xClass.staticVars = new Slot[xClass.staticSlotCount];
        for (int i = 0; i < xClass.staticSlotCount; i++) {
            XFields field = xClass.fields.get(i);
            if (field.isStatic() && field.isFinal())
                initStaticFinalVar(xClass, field);
        }
    }

    public void initStaticFinalVar(XClass xClass, XFields fields) {
        Slot[] vars = xClass.staticVars;
        ConstantPool cp = xClass.constantPool;
        int slotId = fields.slotId;
        int cpIndex = fields.constValueIndex;
        if (cpIndex > 0) {
            switch (fields.descroptor) {
                case "Z":
                case "B":
                case "C":
                case "I": {
                    int value = (int) ((ConstantValue) cp.getConstant(cpIndex).get()).value;
                    vars[slotId] = new Slot(value, null);
                }
                case "J": {
                    long value = (long) ((ConstantValue) cp.getConstant(cpIndex).get()).value;
                    vars[slotId] = new Slot((int) value, null);
                }
                case "F": {
                    float value = (float) ((ConstantValue) cp.getConstant(cpIndex).get()).value;
                    vars[slotId] = new Slot((int) value, null);
                }
                case "D":{
                    double value = (float) ((ConstantValue) cp.getConstant(cpIndex).get()).value;
                    vars[slotId] = new Slot((int) value, null);
                }
                case "'Ljava/lang/String;'":{

                }


            }
        }
    }


}
