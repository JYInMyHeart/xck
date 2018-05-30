package jawa.cmd;

import jawa.classfiles.ClassFile;
import jawa.classfiles.members.MemberInfo;
import jawa.classpath.Classpath;
import jawa.interpreter.Interpreter;
import jawa.rtda.heap.XClass;
import jawa.rtda.heap.XClassLoader;
import jawa.rtda.heap.XMethod;

public class CmdOperations {


    public static void parseCmd(Cmd cmd, String args) {
        switch (args) {
            case "help":
                cmd.setHelpFlag(true);
                System.out.println("help msg");
                break;
            case "?":
                cmd.setHelpFlag(true);
                System.out.println("help msg");
                break;
            case "version":
                cmd.setVersionFlag(true);
                System.out.println("version 0.0.1");
                break;
            case "exit":
                System.exit(0);
            default: {
                String[] msg = args.split(";");
                if (msg.length != 2) throw new RuntimeException("wrong cmd");
                else {
                    cmd.setClassName(msg[1]);
                    cmd.setCpOption(msg[0]);
                }
            }
        }
    }

    public static void startJvm(Cmd cmd) {
        Classpath cp = null;
        try {
            cp = Classpath.parse(cmd.getxJreOption(), cmd.getCpOption());
            assert cp != null;
            XClassLoader xClassLoader = XClassLoader.newClassLoader(cp);
            ClassFile cf = loadClass(cmd.getClassName().replace(".", "/"), cp);
            XClass xClass = xClassLoader.loadClass(cmd.getClassName().replace(".", "/"));
            printClassInfo(cf);
            XMethod mainMethod = xClass.getMainMethod(xClass);
            if (mainMethod != null)
                Interpreter.interpret(mainMethod);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static ClassFile loadClass(String className, Classpath cp) throws Exception {
        byte[] classData = cp.readClass(className);
        assert classData != null;
        ClassFile cf = new ClassFile().parse(classData);
        assert cf != null;
        return cf;
    }

    public static void printClassInfo(ClassFile classFile) throws Exception {
        System.out.println("version: " + classFile.getMajorVersion() + "  " + classFile.getMinorVersion());
        System.out.println("constants count: " + classFile.getConstantPool().getConstantInfoList().length);
        System.out.println("access flags: " + classFile.getAccessFlags());
        System.out.println("this class: " + classFile.getClassName());
        System.out.println("super class: " + classFile.getSuperClassName());
        System.out.println("interfaces: " + classFile.getInterfaces());
        System.out.println("fields: ");
        classFile.getClassFileds().forEach(System.out::println);
        System.out.println("methods: ");
        classFile.getClassMethods().forEach(System.out::println);
        System.out.println();
    }

    public static MemberInfo getMainMethod(ClassFile cf) throws Exception {
        for (MemberInfo m : cf.getClassMethods()) {
            if ("'main'".equals(m.getName()) && "'([Ljava/lang/String;)V'".equals(m.getDescriptor()))
                return m;
        }
        return null;
    }
}
