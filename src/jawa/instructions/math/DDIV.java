package jawa.instructions.math;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;

/**
 * @author xck
 */
public class DDIV extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double v2 = stack.popDouble();
        double v1 = stack.popDouble();
        double result = v1 / v2;
        stack.pushDouble(result);
    }
}
