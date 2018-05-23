package jawa.rtda.heap;

import jawa.classfiles.constant.*;

import java.util.Optional;

import static jawa.rtda.heap.InterfaceMethodRef.newInterfaceMethodRef;
import static jawa.rtda.heap.MethodRef.newMethodRef;
import static jawa.rtda.heap.XClassRef.newClassRef;
import static jawa.rtda.heap.XFieldRef.newFiledRef;

public class ConstantPool {
    private XClass xClass;
    private ConstantInfo[] constants;

    public static ConstantPool newConstatntPool(XClass xClass, jawa.classfiles.constant.ConstantPool cfcp) {
        int cpCount = cfcp.getConstantInfoList().length;
        ConstantPool cp = new ConstantPool();
        ConstantInfo[] constants = new ConstantInfo[cpCount];
        try {
            for (int i = 1; i < cpCount; i++) {
                ConstantInfo cfInfo = cfcp.getConstantInfoList()[i];
                if (cfInfo instanceof ConstantIntegerInfo)
                    constants[i] = cfInfo;
                if (cfInfo instanceof ConstantFloatInfo)
                    constants[i] = cfInfo;
                if (cfInfo instanceof ConstantLongInfo) {
                    constants[i] = cfInfo;
                    i++;
                }
                if (cfInfo instanceof ConstantDoubleInfo) {
                    constants[i] = cfInfo;
                    i++;
                }
                if (cfInfo instanceof ConstantStringInfo) {
                    constants[i] = cfInfo;
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
            cp.xClass = xClass;
            return cp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public XClass getxClass() {
        return xClass;
    }

    public Optional<ConstantInfo> getConstant(int index) {
        return Optional.ofNullable(constants[index]);
    }
}
