import jawa.rtda.Frame;
import jawa.rtda.LocalVars;
import jawa.rtda.OperandStack;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xck
 */
public class Chapter4Test {
    @Test
    public void cp4_1(){
        Frame frame = new Frame(100,100);
        LocalVars vars = frame.getLocalVars();
        vars.setInt(0,100);
        vars.setInt(1,-100);
        vars.setRef(9,null);
        Assert.assertEquals(100,vars.getInt(0));
        Assert.assertEquals(-100,vars.getInt(1));
        Assert.assertEquals(null,vars.getRef(9));

        OperandStack ops = frame.getOperandStack();
        ops.pushInt(100);
        ops.pushInt(-100);
        ops.pushRef(null);
        Assert.assertEquals(null,ops.popRef());
        Assert.assertEquals(-100,ops.popInt());
        Assert.assertEquals(100,ops.popInt());

    }
}
