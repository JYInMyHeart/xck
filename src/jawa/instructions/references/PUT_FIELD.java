package jawa.instructions.references;

import jawa.instructions.base.Index16Instruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;
import jawa.rtda.Slot;
import jawa.rtda.heap.*;

public class PUT_FIELD extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        XMethod currentMethod = frame.getMethod();
        XClass currentClass = currentMethod.getxClass();
        ConstantPool cp = currentClass.getConstantPool();
        XFieldRef fieldRef = (XFieldRef) cp.getConstant(index).get();
        XFields fields = fieldRef.resolvedField();
        XClass xClass = fields.getxClass();
        if (fields.isStatic())
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        if (fields.isFinal()) {
            if (!currentClass.getName().equals(xClass.getName()) || currentMethod.getName().equals( "'<init>'"))
                throw new RuntimeException("java.lang.IllegalAccessError");
        }
        String descriptor = fields.getDescroptor();
        int slotId = fields.getSlotId();
        OperandStack stack = frame.getOperandStack();
        switch (descriptor.charAt(1)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I': {
                int value = stack.popInt();
                XObject ref = stack.popRef();
                if (ref == null)
                    throw new RuntimeException("java.lang.NullPointerException");
                ref.getData()[slotId] = new Slot(value, null);
            }
            break;
            case 'F': {
                float value = stack.popFloat();
                XObject ref = stack.popRef();
                if (ref == null)
                    throw new RuntimeException("java.lang.NullPointerException");
                ref.getData()[slotId] = new Slot((int) value, null);
            }
            break;
            case 'J': {
                long value = stack.popLong();
                XObject ref = stack.popRef();
                if (ref == null)
                    throw new RuntimeException("java.lang.NullPointerException");
                ref.getData()[slotId] = new Slot((int) value, null);
            }
            break;
            case 'D': {
                double value = stack.popDouble();
                XObject ref = stack.popRef();
                if (ref == null)
                    throw new RuntimeException("java.lang.NullPointerException");
                ref.getData()[slotId] = new Slot((int) value, null);
                ;
            }
            break;
            case '[':
            case 'L': {
                XObject value = stack.popRef();
                XObject ref = stack.popRef();
                if (ref == null)
                    throw new RuntimeException("java.lang.NullPointerException");
                ref.getData()[slotId] = new Slot(0, value);
            }
            break;
        }
    }

    public String toString() {
        return "PUT_FIELD{" +
                "index=" + index +
                '}';
    }
}
