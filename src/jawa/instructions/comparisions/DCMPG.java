package jawa.instructions.comparisions;

import jawa.rtda.Frame;

/**
 * @author xck
 */
public class DCMPG extends DCMP {
    @Override
    public void execute(Frame frame) {
        fcmp(frame, true);
    }
}
