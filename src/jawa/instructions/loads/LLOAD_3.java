package jawa.instructions.loads;

import jawa.instructions.base.Index8Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class LLOAD_3 extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushLong(frame.getLocalVars().getLong(3));
    }
}
