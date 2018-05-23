package jawa.instructions.references;

import jawa.instructions.base.Index16Instruction;
import jawa.rtda.Frame;

public class INVOKE_SPECIAL extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().popRef();
    }

    public String toString() {
        return "INVOKE_SPECIAL{" +
                "index=" + index +
                '}';
    }
}
