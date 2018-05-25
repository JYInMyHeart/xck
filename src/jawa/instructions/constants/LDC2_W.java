package jawa.instructions.constants;

import jawa.classfiles.constant.ConstantDoubleInfo;
import jawa.classfiles.constant.ConstantInfo;
import jawa.classfiles.constant.ConstantLongInfo;
import jawa.instructions.base.Index16Instruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;
import jawa.rtda.heap.ConstantPool;

public class LDC2_W extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        ConstantPool cp = frame.getMethod().getxClass().getConstantPool();
        ConstantInfo c = cp.getConstant(index).get();
        if (c instanceof ConstantLongInfo){
            stack.pushLong( ((ConstantLongInfo) c).getValue());
            return;
        }
        if (c instanceof ConstantDoubleInfo){
            stack.pushDouble(((ConstantDoubleInfo) c).getValue());
            return;
        }
        throw new RuntimeException("todo ldc");
    }

    public String toString() {
        return "LDC2_W{" +
                "index=" + index +
                '}';
    }
}
