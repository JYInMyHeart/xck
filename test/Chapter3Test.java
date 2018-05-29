import jawa.classfiles.ClassFile;
import jawa.classpath.Classpath;
import jawa.cmd.CmdOperations;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xck
 */

public class Chapter3Test {
    @Test
    public void cp3_1() {
        try {
            Classpath cp = Classpath.parse("test", "Test");
            ClassFile cf = CmdOperations.loadClass("Test", cp);
            Assert.assertEquals("'jawa/bootstrap/Test'", cf.getClassName());
            Assert.assertEquals("'java/lang/Object'", cf.getSuperClassName());
            Assert.assertEquals(1537, cf.getAccessFlags());
            System.out.println("pass cp3_1!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void cp3_2() {
        try {
            Classpath cp1 = Classpath.parse("test", "Test");
            ClassFile cf1 = CmdOperations.loadClass("Hello", cp1);
            Assert.assertEquals("'jawa/bootstrap/Hello'", cf1.getClassName());
            Assert.assertEquals("'java/lang/Object'", cf1.getSuperClassName());
            Assert.assertEquals(1057, cf1.getAccessFlags());
            Assert.assertEquals(25, cf1.getClassFileds().get(0).getAccessFlags());
            Assert.assertEquals("'Z'", cf1.getClassFileds().get(0).getDescriptor());
            Assert.assertEquals("'FLAG'", cf1.getClassFileds().get(0).getName());
            System.out.println("pass cp3_2!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
