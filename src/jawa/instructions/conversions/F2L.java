package jawa.instructions.conversions;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;

/**
 * @author xck
 */
public class F2L extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float d = stack.popFloat();
        stack.pushLong((long) d);
    }
}
