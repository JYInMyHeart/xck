package jawa.instructions.constants;

import jawa.instructions.base.Index8Instruction;
import jawa.rtda.Frame;

public class LDC extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        _ldc(frame, index);
    }

    public String toString() {
        return "LDC{" +
                "index=" + index +
                '}';
    }
}
