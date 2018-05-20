package jawa.instructions.math;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;

/**
 * @author xck
 */
public class LSHL extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int v2 = stack.popInt();
        long v1 = stack.popLong();
        int s = v2 & 0x3f;
        long result = v1 << s;
        stack.pushLong(result);
    }
}
