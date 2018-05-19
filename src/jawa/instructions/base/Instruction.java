package jawa.instructions.base;

import jawa.rtda.Frame;

/**
 * @author xck
 */
public interface Instruction {
    void fetchOperands(ByteCodeReader reader);
    void execute(Frame frame);
}
