package jawa.instructions.math;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;

/**
 * @author xck
 */
public class LNEG extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long v1 = stack.popLong();
        long result = -v1;
        stack.pushLong(result);
    }
}
