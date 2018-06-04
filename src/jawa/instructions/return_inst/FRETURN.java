package jawa.instructions.return_inst;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;
import jawa.rtda.XThread;

/**
 * @author xck
 */
public class FRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        XThread thread = frame.getThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.topFrame();
        float retVal = currentFrame.getOperandStack().popFloat();
        invokerFrame.getOperandStack().pushFloat(retVal);
    }
}
