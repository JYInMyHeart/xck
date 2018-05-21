package jawa.instructions.math;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;

/**
 * @author xck
 */
public class INEG extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int v1 = stack.popInt();
        int result = -v1;
        stack.pushInt(result);
    }
}
