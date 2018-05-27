package jawa.instructions.references;

import jawa.instructions.base.ByteCodeReader;
import jawa.instructions.base.Instruction;
import jawa.rtda.Frame;
import jawa.rtda.heap.*;

import static jawa.instructions.base.InvokeLogic.invokeMethod;

public class INVOKE_INTERFACE implements Instruction {
    int index;
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        index = reader.readUInt16();
        reader.readUInt8();//count
        reader.readUInt8();//must be 0;
    }

    @Override
    public void execute(Frame frame) {
        ConstantPool cp = frame.getMethod().getxClass().getConstantPool();
        InterfaceMethodRef methodRef = (InterfaceMethodRef) cp.getConstant(index).get();
        XMethod resolvedMethod = methodRef.resolvedInterfaceMethod();
        if(resolvedMethod.isStatic())
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        XObject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);
        if(ref == null){
            throw new RuntimeException("java.lang.NullPointerException");
        }
        if(!ref.getxClass().isImplements(methodRef.resolvedClass()))
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        XMethod methodToBeInvoked = methodRef.lookupMethodInClass(
                ref.getxClass(),methodRef.getName(),methodRef.getDescriptor()).get();
        if(methodToBeInvoked == null || methodToBeInvoked.isAbstract())
            throw new RuntimeException("java.lang.AbstractMethodError");
        if(!methodToBeInvoked.isPublic())
            throw new RuntimeException("IllegalAccessError");
        invokeMethod(frame,methodToBeInvoked);
    }
}
