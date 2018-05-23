package jawa.instructions.loads;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class ALOAD_2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushRef(frame.getLocalVars().getRef(2));
    }

    public String toString() {
        return "ALOAD_2{}";
    }
}
