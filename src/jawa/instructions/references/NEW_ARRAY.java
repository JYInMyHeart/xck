package jawa.instructions.references;

import jawa.instructions.base.ByteCodeReader;
import jawa.instructions.base.Instruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;
import jawa.rtda.heap.*;

import static jawa.instructions.references.NEW_ARRAY.AT.*;
import static jawa.rtda.heap.ArrayClass.newArray;

public class NEW_ARRAY implements Instruction {
    private int arrayType;

     enum AT{
        AT_BOOLEAN(4),
        AT_CHAR(5),
        AT_INT(10),
        AT_FLOAT(6),
        AT_DOUBLE(7),
        AT_SHORT(9),
        AT_BYTE(8),
        AT_LONG(11);

        private final int value;

        AT(int value) {
            this.value = value;
        }

        public final int getValue() {
            return value;
        }
    }


    public int getArrayType() {
        return arrayType;
    }

    public void setArrayType(int arrayType) {
        this.arrayType = arrayType;
    }

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        arrayType = reader.readUInt8();
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if(count < 0)
            throw new RuntimeException("java.lang.NativeArraySizeException");
        XClassLoader xClassLoader = frame.getMethod().getxClass().getLoader();
        XClass arrayClass = getPrimitiveArrayClass(xClassLoader,arrayType);
        XObject arrayObject = newArray(count,arrayClass);
        stack.pushRef(arrayObject);
    }

    public XClass getPrimitiveArrayClass(XClassLoader xClassLoader, int type){
        if(AT_BOOLEAN.value == type) return xClassLoader.loadClass("[Z");
        else if(AT_BYTE.value == type) return xClassLoader.loadClass("[B");
        else if(AT_CHAR.value == type) return xClassLoader.loadClass("[C");
        else if(AT_SHORT.value == type) return xClassLoader.loadClass("[S");
        else if(AT_INT.value == type) return xClassLoader.loadClass("[I");
        else if(AT_LONG.value == type) return xClassLoader.loadClass("[J");
        else if(AT_FLOAT.value == type) return xClassLoader.loadClass("[F");
        else if(AT_DOUBLE.value == type) return xClassLoader.loadClass("[D");
        else throw new RuntimeException("Invalid arrayType");
    }
}
