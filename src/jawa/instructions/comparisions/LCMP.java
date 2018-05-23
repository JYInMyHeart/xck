package jawa.instructions.comparisions;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;

/**
 * @author xck
 */
public class LCMP extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long v2 = stack.popLong();
        long v1 = stack.popLong();
        if (v1 > v2)
            stack.pushInt(1);
        else if (v1 == v2)
            stack.pushInt(0);
        else
            stack.pushInt(-1);
    }
}
