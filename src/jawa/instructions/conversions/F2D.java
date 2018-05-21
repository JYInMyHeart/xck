package jawa.instructions.conversions;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;

/**
 * @author xck
 */
public class F2D extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float d = stack.popFloat();
        stack.pushDouble((double) d);
    }
}
