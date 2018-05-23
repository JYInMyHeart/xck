package jawa.instructions.references;

import jawa.instructions.base.Index16Instruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;
import jawa.rtda.heap.ConstantPool;
import jawa.rtda.heap.XClass;
import jawa.rtda.heap.XClassRef;
import jawa.rtda.heap.XObject;

public class CHECK_CAST extends Index16Instruction {
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
        if (!ref.isInstanceof(xClass)) {
            throw new RuntimeException("java.lang.ClassCastException");
        }
    }

    public String toString() {
        return "CHECK_CAST{" +
                "index=" + index +
                '}';
    }
}
