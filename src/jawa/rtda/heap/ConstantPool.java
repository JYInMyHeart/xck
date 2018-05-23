package jawa.rtda.heap;

import jawa.classfiles.constant.*;

import java.util.Optional;

import static jawa.rtda.heap.InterfaceMethodRef.newInterfaceMethodRef;
import static jawa.rtda.heap.MethodRef.newMethodRef;
import static jawa.rtda.heap.XClassRef.newClassRef;
import static jawa.rtda.heap.XFieldRef.newFiledRef;

public class ConstantPool {
    private XClass xClass;
    private Constant[] constants;

    public static ConstantPool newConstatntPool(XClass xClass, jawa.classfiles.constant.ConstantPool cfcp) {
        int cpCount = cfcp.getConstantInfoList().length;
        ConstantPool cp = new ConstantPool();
        Constant[] constants = new Constant[cpCount];
        try {
            for (int i = 0; i < cpCount; i++) {
                ConstantInfo cfInfo = cfcp.getConstantInfoList()[i];
                if (cfInfo instanceof ConstantIntegerInfo)
                    constants[i] = new ConstantValue(((ConstantIntegerInfo) cfInfo).getValue());
                if (cfInfo instanceof ConstantFloatInfo)
                    constants[i] = new ConstantValue(((ConstantFloatInfo) cfInfo).getValue());
                if (cfInfo instanceof ConstantLongInfo) {
                    constants[i] = new ConstantValue(((ConstantLongInfo) cfInfo).getValue());
                    i++;
                }
                if (cfInfo instanceof ConstantDoubleInfo) {
                    constants[i] = new ConstantValue(((ConstantDoubleInfo) cfInfo).getValue());
                    i++;
                }
                if (cfInfo instanceof ConstantStringInfo) {
                    constants[i] = new ConstantValue(((ConstantStringInfo) cfInfo).getString());
                }
                if (cfInfo instanceof ConstantClassInfo)
                    constants[i] = newClassRef(cp, (ConstantClassInfo) cfInfo);
                if (cfInfo instanceof ConstantFieldrefInfo)
                    constants[i] = newFiledRef(cp, (ConstantFieldrefInfo) cfInfo);
                if (cfInfo instanceof ConstantMethodrefInfo)
                    constants[i] = newMethodRef(cp, (ConstantMethodrefInfo) cfInfo);
                if (cfInfo instanceof ConstantInterfaceMethodrefInfo)
                    constants[i] = newInterfaceMethodRef(cp, (ConstantInterfaceMethodrefInfo) cfInfo);

            }
            cp.constants = constants;
            return cp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public XClass getxClass() {
        return xClass;
    }

    public Optional<Constant> getConstant(int index) {
        return Optional.ofNullable(constants[index]);
    }
}
