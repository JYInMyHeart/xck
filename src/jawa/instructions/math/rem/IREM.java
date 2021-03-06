package jawa.instructions.math.rem;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;

/**
 * @author xck
 */
public class IREM extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int v2 = stack.popInt();
        int v1 = stack.popInt();
        if (v2 == 0)
            throw new RuntimeException("java.lang.ArithmeticException: / by zero");
        int result = v1 % v2;
        stack.pushInt(result);
    }
}
