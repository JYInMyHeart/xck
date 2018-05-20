package jawa.instructions.comparisions;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;

/**
 * @author xck
 */
public class CMP extends NoOperandsInstruction {
    public void cmp(OperandStack stack, double v1, double v2,boolean flag){
        if(v1 > v2)
            stack.pushInt(1);
        else if(v1 == v2)
            stack.pushInt(0);
        else if(v1 < v2)
            stack.pushInt(-1);
        else if(flag)
            stack.pushInt(1);
        else
            stack.pushInt(-1);
    }
}
