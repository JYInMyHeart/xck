package jawa.instructions.base.return_inst;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;
import jawa.rtda.XThread;
import jawa.rtda.heap.XObject;

/**
 * @author xck
 */
public class ARETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        XThread thread = frame.getThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.popFrame();
        XObject retVal = currentFrame.getOperandStack().popRef();
        invokerFrame.getOperandStack().pushRef(retVal);
    }
}
