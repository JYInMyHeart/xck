package jawa.instructions.stack;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class Pop2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().popSlot();
        frame.getOperandStack().popSlot();
    }
}
