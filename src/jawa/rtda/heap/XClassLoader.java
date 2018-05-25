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
        System.out.println(String.format("Loaded %s from s", name));
        return xclass;
    }

    public Optional<byte[]> readClass(String name) {
        byte[] data = cp.readClass(name);
        return Optional.ofNullable(data);
    }

    public Optional<XClass> defineClass(byte[] data) throws Exception {
        XClass xClass = parseClass(data).get();
        xClass.setLoader(this);
        resolveSuperClass(xClass);
        resolveInterfaces(xClass);
        classMap.put(xClass.getName(), xClass);
        return Optional.ofNullable(xClass);
    }

    public Optional<XClass> parseClass(byte[] data) throws Exception {
        ClassFile cf = new ClassFile().parse(data);
        return Optional.ofNullable(newXClass(cf));
    }

    public void resolveSuperClass(XClass xClass) {
        if (!Objects.equals(xClass.getName(), "'java/lang/Object'"))
            xClass.setSuperClass(xClass.getLoader().loadClass(xClass.getSuperClassName()));
    }

    public void resolveInterfaces(XClass xClass) {
        int interfaceCount = xClass.getInterfaceNames().size();
        if (interfaceCount > 0) {
            for (int i = 0; i < interfaceCount; i++) {
                xClass.getInterfaces()[i] = xClass.getLoader().loadClass(xClass.getInterfaceNames().get(i));
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
        if (xClass.getSuperClass() != null) {
            slotId = xClass.getSuperClass().getInstanceSlotCount();
        }
        for (int i = 0; i < xClass.getFields().size(); i++) {
            if (!xClass.getFields().get(i).isStatic()) {
                xClass.getFields().get(i).setSlotId(slotId);
                slotId++;
                if (xClass.getFields().get(i).isLongOrDouble())
                    slotId++;
            }
        }
        xClass.setInstanceSlotCount(slotId);
    }

    public void calcStaticFieldSlotIds(XClass xClass) {
        int slotId = 0;
        for (int i = 0; i < xClass.getFields().size(); i++) {
            if (xClass.getFields().get(i).isStatic()) {
                xClass.getFields().get(i).setSlotId(slotId);
                slotId++;
                if (xClass.getFields().get(i).isLongOrDouble())
                    slotId++;
            }
        }
        xClass.setStaticSlotCount(slotId);
    }

    public void allocAndInitStaticVars(XClass xClass) {
        xClass.setStaticVars(new Slot[xClass.getStaticSlotCount()]);
        for (int i = 0; i < xClass.getStaticSlotCount(); i++) {
            XFields field = xClass.getFields().get(i);
            xClass.getStaticVars()[i] = new Slot(0,xClass.getFields().get(i));
            if (field.isStatic() && field.isFinal())
                initStaticFinalVar(xClass, field);
        }
    }

    public void initStaticFinalVar(XClass xClass, XFields fields) {
        Slot[] vars = xClass.getStaticVars();
        ConstantPool cp = xClass.getConstantPool();
        int slotId = fields.getSlotId();
        int cpIndex = fields.getConstValueIndex();
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
                case "D": {
                    double value = (float) ((ConstantValue) cp.getConstant(cpIndex).get()).value;
                    vars[slotId] = new Slot((int) value, null);
                }
                case "'Ljava/lang/String;'": {

                }


            }
        }
    }


}
