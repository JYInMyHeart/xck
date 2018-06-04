package jawa.instructions.return_inst;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class RETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getThread().popFrame();
    }
}
