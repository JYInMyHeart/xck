package jawa.instructions.stack;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;
import jawa.rtda.Slot;

/**
 * @author xck
 */
public class DUP_X2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot = stack.popSlot();
        stack.pushSlot(slot);
        stack.pushSlot(slot);
    }
}
