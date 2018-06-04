package jawa.instructions.constants;

import jawa.instructions.base.Index16Instruction;
import jawa.rtda.Frame;

public class LDC_W extends Index16Instruction implements _LDC{
    @Override
    public void execute(Frame frame) {
        _ldc(frame, index);
    }
}
