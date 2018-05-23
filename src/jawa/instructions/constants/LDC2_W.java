package jawa.instructions.constants;

import jawa.instructions.base.Index16Instruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;
import jawa.rtda.heap.ConstantPool;

public class LDC2_W extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        ConstantPool cp = frame.getMethod().getxClass().getConstantPool();
        Object c = cp.getConstant(index);
        if (c.getClass().equals(long.class))
            stack.pushLong((long) c);
        if (c.getClass().equals(double.class))
            stack.pushDouble((double) c);
        throw new RuntimeException("todo ldc");
    }
}
