package jawa.instructions.stores;

import jawa.instructions.base.Index8Instruction;
import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class LSTORE_2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setLong(2,frame.getOperandStack().popLong());
    }
}
