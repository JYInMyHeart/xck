package jawa.instructions.references;

import jawa.instructions.base.Index16Instruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;
import jawa.rtda.Slot;
import jawa.rtda.heap.*;

public class PUT_STATIC extends Index16Instruction {
    public String toString() {
        return "PUT_STATIC{" +
                "index=" + index +
                '}';
    }

    @Override
    public void execute(Frame frame) {
        XMethod currentMethod = frame.getMethod();
        XClass currentClass = currentMethod.getxClass();
        ConstantPool cp = currentClass.getConstantPool();
        XFieldRef fieldRef = (XFieldRef) cp.getConstant(index).get();
        XFields fields = fieldRef.resolvedField();
        XClass xClass = fields.getxClass();
        if (!fields.isStatic())
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        if (fields.isFinal()) {
            if (currentClass != xClass || currentMethod.getName() != "'<clinit>'")
                throw new RuntimeException("java.lang.IllegalAccessError");
        }
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
                slots[slotId] = new Slot(stack.popInt(), null);
            }
            break;
            case 'F': {
                slots[slotId] = new Slot((int) stack.popFloat(), null);
            }
            break;
            case 'J': {
                slots[slotId] = new Slot((int) stack.popLong(), null);
            }
            break;
            case 'D': {
                slots[slotId] = new Slot((int) stack.popDouble(), null);
            }
            break;
            case 'L': {
                slots[slotId] = new Slot(0, stack.popRef());
            }
            break;
        }


    }
}
