package jawa.instructions.stores;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class ASTORE_3 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setRef(3, frame.getOperandStack().popRef());
    }

    public String toString() {
        return "ASTORE_3{}";
    }
}
