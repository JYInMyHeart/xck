package jawa.instructions.stores;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class ISTORE_2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setInt(2, frame.getOperandStack().popInt());
    }

    public String toString() {
        return "ISTORE_2";
    }
}
