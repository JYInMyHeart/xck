package jawa.instructions.math;

import jawa.instructions.base.ByteCodeReader;
import jawa.instructions.base.Instruction;
import jawa.rtda.Frame;
import jawa.rtda.LocalVars;

/**
 * @author xck
 */
public class IINC implements Instruction {
    private int index;
    private int constValue;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getConstValue() {
        return constValue;
    }

    public void setConstValue(int constValue) {
        this.constValue = constValue;
    }

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        index = reader.readUInt8();
        constValue = reader.readInt8();
    }

    @Override
    public void execute(Frame frame) {
        LocalVars localVars = frame.getLocalVars();
        int value = localVars.getInt(index);
        value += constValue;
        localVars.setInt(index, value);
    }

    public String toString() {
        return "IINC{" +
                "index=" + index +
                ", constValue=" + constValue +
                '}';
    }
}
