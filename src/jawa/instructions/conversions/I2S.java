package jawa.instructions.conversions;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;

/**
 * @author xck
 */
public class I2S extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        short d = (short)stack.popInt();
        stack.pushInt(d);
    }
}
