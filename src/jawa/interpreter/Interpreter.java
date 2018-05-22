package jawa.interpreter;

import jawa.classfiles.members.MemberInfo;
import jawa.classfiles.members.attributeinfos.CodeAttribute;
import jawa.instructions.base.ByteCodeReader;
import jawa.instructions.base.Instruction;
import jawa.rtda.Frame;
import jawa.rtda.XThread;

/**
 * @author xck
 */
public class Interpreter {
    public static void interpret(MemberInfo memberInfo){
        CodeAttribute codeAttribute = memberInfo.getCodeAttribute();
        int maxLocals = codeAttribute.getMaxLocals();
        int maxStack = codeAttribute.getMaxStack();
        byte[] byteCode = codeAttribute.getCode();
        XThread thread = new XThread();
        Frame frame = thread.newFrame(maxLocals,maxStack);
        thread.pushFrame(frame);

        loop(thread,byteCode);
    }

    public static void catchErr(Frame frame){
        System.out.println(frame.getLocalVars());
    }

    public static void loop(XThread thread,byte[] byteCode){
        Frame frame = thread.popFrame();
        ByteCodeReader reader = new ByteCodeReader();
        try {
            while(true){
                int pc = frame.getNextPc();
                thread.setPc(pc);
                reader.reset(byteCode,pc);
                int opcode =  reader.readUInt8();
                Instruction inst = Instruction.newInstruction(opcode);
                inst.fetchOperands(reader);
                frame.setNextPc(reader.getPc());
                System.out.println("" + pc + "    " + inst);
                inst.execute(frame);
            }
        } catch (Exception e) {
            catchErr(frame);
            System.out.println(e);
        }
    }
}
