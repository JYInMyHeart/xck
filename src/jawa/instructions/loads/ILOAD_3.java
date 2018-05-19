package jawa.instructions.loads;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class ILOAD_3 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(frame.getLocalVars().getInt(3));
    }
}
