package jawa.instructions.comparisions;

import jawa.rtda.Frame;

/**
 * @author xck
 */
public class FCMPG extends FCMP{
    @Override
    public void execute(Frame frame) {
        fcmp(frame,true);
    }
}
