package jawa.instructions.stores;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class ASTORE_0 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setRef(0, frame.getOperandStack().popRef());
    }
}
