package jawa.instructions.references;

import jawa.instructions.base.Index16Instruction;
import jawa.rtda.Frame;
import jawa.rtda.heap.ConstantPool;
import jawa.rtda.heap.MethodRef;
import jawa.rtda.heap.XMethod;

import static jawa.instructions.base.InvokeLogic.invokeMethod;

/**
 * @author xck
 */
public class INVOKE_STATIC extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool cp = frame.getMethod().getxClass().getConstantPool();
        MethodRef methodRef = (MethodRef) cp.getConstant(index).get();
        XMethod resolvedMethod = methodRef.resolvedMethod();
        if(!resolvedMethod.isStatic())
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        invokeMethod(frame,resolvedMethod);
    }
}
