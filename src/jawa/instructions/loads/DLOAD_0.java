package jawa.instructions.loads;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class DLOAD_0 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(frame.getLocalVars().getDouble(0));
    }
}
