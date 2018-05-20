package jawa.instructions.extended;

import jawa.instructions.base.ByteCodeReader;
import jawa.instructions.base.Instruction;
import jawa.instructions.loads.*;
import jawa.instructions.math.IINC;
import jawa.instructions.stores.*;
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
                ILOAD iload = new ILOAD();
                iload.setIndex(reader.readInt16());
                modifiedInstruction = iload;
                break;
            case 0x16:  // lload
                LLOAD lload = new LLOAD();
                lload.setIndex(reader.readInt16());
                modifiedInstruction = lload;
                break;
            case 0x17:  // fload
                FLOAD fload = new FLOAD();
                fload.setIndex(reader.readInt16());
                modifiedInstruction = fload;
                break;
            case 0x18:  // dload
                DLOAD dload = new DLOAD();
                dload.setIndex(reader.readInt16());
                modifiedInstruction = dload;
                break;
            case 0x19:  // aload
                ALOAD aload = new ALOAD();
                aload.setIndex(reader.readInt16());
                modifiedInstruction = aload;
                break;
            case 0x36:  // istore
                ISTORE istore = new ISTORE();
                istore.setIndex(reader.readInt16());
                modifiedInstruction = istore;
                break;
            case 0x37:  // lstore
                LSTORE lstore = new LSTORE();
                lstore.setIndex(reader.readInt16());
                modifiedInstruction = lstore;
                break;
            case 0x38:  // fstore
                FSTORE fstore = new FSTORE();
                fstore.setIndex(reader.readInt16());
                modifiedInstruction = fstore;
                break;
            case 0x39:  // dstore
                DSTORE dstore = new DSTORE();
                dstore.setIndex(reader.readInt16());
                modifiedInstruction = dstore;
                break;
            case 0x3a:  // astore
                ASTORE astore = new ASTORE();
                astore.setIndex(reader.readInt16());
                modifiedInstruction = astore;
                break;
            case 0x84:  // iinc
                IINC iinc = new IINC();
                iinc.setIndex(reader.readInt16());
                iinc.setConstValue(reader.readInt16());
                modifiedInstruction = iinc;
                break;
            case 0xa9:  //ret
                throw new RuntimeException("Unsupporsted opcodeL 0xa9");
        }
    }

    @Override
    public void execute(Frame frame) {
        modifiedInstruction.execute(frame);
    }
}
