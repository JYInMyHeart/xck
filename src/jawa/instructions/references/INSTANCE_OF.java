package jawa.instructions.references;

import jawa.instructions.base.Index16Instruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;
import jawa.rtda.heap.ConstantPool;
import jawa.rtda.heap.XClass;
import jawa.rtda.heap.XClassRef;
import jawa.rtda.heap.XObject;

public class INSTANCE_OF extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        XObject ref = stack.popRef();
        if (ref == null) {
            stack.pushInt(0);
            return;
        }
        ConstantPool cp = frame.getMethod().getxClass().getConstantPool();
        XClassRef xClassRef = (XClassRef) cp.getConstant(index).get();
        XClass xClass = xClassRef.resolvedClass();
        if (ref.isInstanceof(xClass)) {
            stack.pushInt(1);
        } else {
            stack.pushInt(0);
        }
    }
}
