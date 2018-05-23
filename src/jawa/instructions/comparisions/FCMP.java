package jawa.instructions.comparisions;

import jawa.rtda.Frame;
import jawa.rtda.OperandStack;

/**
 * @author xck
 */
public class FCMP extends CMP {
    protected void fcmp(Frame frame, boolean flag) {
        OperandStack stack = frame.getOperandStack();
        float v2 = stack.popFloat();
        float v1 = stack.popFloat();
        cmp(stack, v1, v2, flag);
    }
}
