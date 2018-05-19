package jawa.instructions.stack;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;
import jawa.rtda.Slot;

/**
 * @author xck
 */
public class SWAP extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        stack.pushSlot(slot1);
        stack.pushSlot(slot2);
    }
}
