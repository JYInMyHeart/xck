package jawa.instructions.loads;

import jawa.instructions.base.Index8Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class ALOAD_2 extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushRef(frame.getLocalVars().getRef(2));
    }
}
