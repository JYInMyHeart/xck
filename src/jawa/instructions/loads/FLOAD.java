package jawa.instructions.loads;

import jawa.instructions.base.Index8Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class FLOAD extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(frame.getLocalVars().getFloat(index));
    }
}
