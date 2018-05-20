package jawa.instructions.extended;

import jawa.instructions.base.ByteCodeReader;
import jawa.instructions.base.Instruction;
import jawa.instructions.loads.*;
import jawa.instructions.math.IINC;
import jawa.instructions.stores.LSTORE;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class WIDE implements Instruction {
    private Instruction modifiedInstruction;
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        int opcode = reader.readInt8();
        switch (opcode){
            case 0x15:  // iload
                ILOAD inst = new ILOAD();
                inst.setIndex(reader.readInt16());
                modifiedInstruction = inst;
            case 0x16:  // lload
                LLOAD inst = new LLOAD();
                inst.setIndex(reader.readInt16());
                modifiedInstruction = inst;
            case 0x17:  // fload
                FLOAD inst = new FLOAD();
                inst.setIndex(reader.readInt16());
                modifiedInstruction = inst;
            case 0x18:  // dload
                DLOAD inst = new DLOAD();
                inst.setIndex(reader.readInt16());
                modifiedInstruction = inst;
            case 0x19:  // aload
                ALOAD inst = new ALOAD();
                inst.setIndex(reader.readInt16());
                modifiedInstruction = inst;
            case 0x36:  // istore
                ISTORE inst = new ISTORE();
                inst.setIndex(reader.readInt16());
                modifiedInstruction = inst;
            case 0x37:  // lstore
                LSTORE inst = new LSTORE();
                inst.setIndex(reader.readInt16());
                modifiedInstruction = inst;
            case 0x38:  // fstore


                FSTORE inst = new FSTORE();
                inst.setIndex(reader.readInt16());
                modifiedInstruction = inst;
            case 0x39:  // dstore
                DSTORE inst = new DSTORE();
                inst.setIndex(reader.readInt16());
                modifiedInstruction = inst;
            case 0x3a:  // astore
                ASTORE inst = new ASTORE();
                inst.setIndex(reader.readInt16());
                modifiedInstruction = inst;
            case 0x84:  // iinc
                IINC inst = new IINC();
                inst.setIndex(reader.readInt16());
                inst.setConstValue(reader.readInt16());
                modifiedInstruction = inst;
            case 0xa9:  //ret
                throw new RuntimeException("Unsupporsted opcodeL 0xa9");
        }
    }

    @Override
    public void execute(Frame frame) {
        modifiedInstruction.execute(frame);
    }
}
