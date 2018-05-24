package jawa.instructions.references;

import jawa.instructions.base.Index16Instruction;
import jawa.rtda.Frame;
import jawa.rtda.heap.*;

import static jawa.instructions.base.InvokeLogic.invokeMethod;

public class INVOKE_SPECIAL extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        XClass currentClass = frame.getMethod().getxClass();
        ConstantPool cp = currentClass.getConstantPool();
        MethodRef methodRef = (MethodRef) cp.getConstant(index).get();
        XClass resolvedClass = methodRef.resolvedClass();
        XMethod resolvedMethod = methodRef.resolvedMethod();
        if(resolvedMethod.getName().equals("'<init>'")
                && resolvedMethod.getxClass() != resolvedClass)
            throw new RuntimeException("java.lang.NoSuchMethodError");
        if(resolvedMethod.isStatic())
            throw new RuntimeException("java.lang.IncompatibleChangeError");
        XObject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount());
        if(ref == null)
            throw new RuntimeException("java.lang.NullPointerException");
        if(resolvedMethod.isProtected()
                && resolvedMethod.getxClass().isSuperClassOf(currentClass)
                && resolvedMethod.getxClass().getPackageName() != currentClass.getPackageName()
                && ref.getxClass() != currentClass
                && !ref.getxClass().isSubClassOf(currentClass)){
            throw new RuntimeException("java.lang.IllegalAccessError");
        }
        XMethod methodToBeInvoked = resolvedMethod;
        if(currentClass.isSuper()
                && resolvedClass.isSuperClassOf(currentClass)
                && resolvedClass.getName() != "<init>"){
            methodToBeInvoked = methodRef.lookupMethodInClass(currentClass.getSuperClass()
                    ,methodRef.getName()
                    ,methodRef.getDescriptor()).get();
        }
        if(methodToBeInvoked == null || methodToBeInvoked.isAbstract())
            throw new RuntimeException("java.lang.AbstractMethodError");
        invokeMethod(frame,methodToBeInvoked);
    }

    public String toString() {
        return "INVOKE_SPECIAL{" +
                "index=" + index +
                '}';
    }
}
