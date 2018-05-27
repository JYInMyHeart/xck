package jawa.instructions.references;

import jawa.instructions.base.Index16Instruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;
import jawa.rtda.Slot;
import jawa.rtda.heap.ConstantPool;
import jawa.rtda.heap.XClass;
import jawa.rtda.heap.XFieldRef;
import jawa.rtda.heap.XFields;

import static jawa.instructions.base.InitLogic.initClass;

public class GET_STATIC extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool cp = frame.getMethod().getxClass().getConstantPool();
        XFieldRef fieldRef = (XFieldRef) cp.getConstant(index).get();
        XFields fields = fieldRef.resolvedField();
        XClass xClass = fields.getxClass();
        if(!xClass.isInitStarted()){
            frame.revertNextPc();
            initClass(frame.getThread(),xClass);
            return;
        }
        if (!fields.isStatic())
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        String descriptor = fields.getDescroptor();
        int slotId = fields.getSlotId();
        Slot[] slots = xClass.getStaticVars();
        OperandStack stack = frame.getOperandStack();
        switch (descriptor.charAt(1)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I': {
                stack.pushInt(slots[slotId].getNum());
            }
            break;
            case 'F': {
                stack.pushFloat(slots[slotId].getNum());
            }
            break;
            case 'J': {
                stack.pushLong(slots[slotId].getNum());
            }
            break;
            case 'D': {
                stack.pushDouble(slots[slotId].getNum());
            }
            break;
            case'[':
            case 'L': {
                stack.pushRef(slots[slotId].getRef());
            }
            break;
        }
    }

    public String toString() {
        return "GET_STATIC{" +
                "index=" + index +
                '}';
    }
}
