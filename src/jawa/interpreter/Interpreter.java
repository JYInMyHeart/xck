package jawa.interpreter;

import jawa.instructions.base.ByteCodeReader;
import jawa.instructions.base.Instruction;
import jawa.rtda.Frame;
import jawa.rtda.XThread;
import jawa.rtda.heap.XMethod;

/**
 * @author xck
 */
public class Interpreter {
    public static void interpret(XMethod memberInfo) {
        XThread thread = new XThread();
        Frame frame = thread.newFrame(memberInfo);
        thread.pushFrame(frame);

        loop(thread, memberInfo.getCode());
    }

    public static void catchErr(Frame frame) {
        System.out.println(frame.getLocalVars());
    }

    public static void loop(XThread thread, byte[] byteCode) {

        ByteCodeReader reader = new ByteCodeReader();
        try {
            while (true) {
                Frame frame = thread.currentFrame();
                int pc = frame.getNextPc();
                thread.setPc(pc);
                byte[] codes = frame.getMethod().getCode();
                reader.reset(codes, pc);
                int opcode = reader.readUInt8();
                Instruction inst = Instruction.newInstruction(opcode);
                inst.fetchOperands(reader);
                frame.setNextPc(reader.getPc());
//                if(logInst)
//                    logInstruction(frame,inst);
//                System.out.println("" + pc + "    " + inst);
                inst.execute(frame);
                if(thread.isStackEmpty()){
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logInstruction(Frame frame,Instruction inst){
        XMethod method = frame.getMethod();
        String className = method.getxClass().getName();
        String methodName = method.getName();
        int pc = frame.getNextPc();
        System.out.println(String.format("%s.%s() #%2d %s %s",className,methodName,pc,inst,inst));
    }
}
