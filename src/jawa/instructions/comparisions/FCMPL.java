package jawa.instructions.comparisions;

import jawa.rtda.Frame;

/**
 * @author xck
 */
public class FCMPL extends FCMP{
    @Override
    public void execute(Frame frame) {
        fcmp(frame,false);
    }
}
