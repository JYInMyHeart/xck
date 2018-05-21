package jawa.instructions.loads;

import jawa.instructions.base.Index8Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class DLOAD_3 extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(frame.getLocalVars().getDouble(3));
    }
}
