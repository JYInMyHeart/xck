package jawa.instructions.stores;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class ASTORE_2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setRef(2, frame.getOperandStack().popRef());
    }

    public String toString() {
        return "ASTORE_2{}";
    }
}
