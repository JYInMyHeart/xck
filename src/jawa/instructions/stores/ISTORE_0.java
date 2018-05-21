package jawa.instructions.stores;

import jawa.instructions.base.Index8Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class ISTORE_0 extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setInt(0,frame.getOperandStack().popInt());
    }

    public String toString() {
        return "ISTORE_0";
    }
}
