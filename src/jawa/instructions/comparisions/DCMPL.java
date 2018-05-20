package jawa.instructions.comparisions;

import jawa.rtda.Frame;

/**
 * @author xck
 */
public class DCMPL extends DCMP{
    @Override
    public void execute(Frame frame) {
        fcmp(frame,false);
    }
}
