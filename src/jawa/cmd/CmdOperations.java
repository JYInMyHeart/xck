package jawa.cmd;

import jawa.Utils.Sth;
import jawa.classfiles.ClassFile;
import jawa.classpath.Classpath;

import java.util.Arrays;

public class CmdOperations {


    public void parseCmd(Cmd cmd,String args){
        switch (args){
            case "help":cmd.setHelpFlag(true);
                System.out.println("help msg");break;
            case "?":cmd.setHelpFlag(true);
                System.out.println("help msg");break;
            case "version":cmd.setVersionFlag(true);
                System.out.println("version 0.0.1");break;
            case "exit":System.exit(0);
            default:{
                String[] msg = args.split(" ");
                if(msg.length != 2) throw new RuntimeException("wrong cmd");
                else {
                    cmd.setClassName(msg[1]);
                    cmd.setCpOption(msg[0]);
                }
            }
        }
    }

    public void startJvm(Cmd cmd){
        Classpath cp = null;
        try {
            cp = new Classpath().parse(cmd.getxJreOption(),cmd.getCpOption());
            assert cp != null;
            byte[] classData = cp.readClass(cmd.getClassName().replace(".","/"));
//            System.out.println(Arrays.toString(classData));
//            System.out.println(cmd);
            ClassFile cf = loadClass(cmd.getClassName().replace(".","/"),cp);
            printClassInfo(cf);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public ClassFile loadClass(String className,Classpath cp) throws Exception {
        byte[] classData = cp.readClass(className);
        assert classData != null;
        ClassFile cf = new ClassFile().parse(classData);
        assert cf != null;
        return cf;
    }

    public void printClassInfo(ClassFile classFile) throws Exception {
        System.out.println("version: " + classFile.getMajorVersion() + "  " + classFile.getMinorVersion());
        System.out.println("constants count: " + classFile.getConstantPool().getConstantInfoList().length);
        System.out.println("access flags: " + classFile.getAccessFlags());
        System.out.println("this class: " + classFile.getClassName());
        System.out.println("super class: " + classFile.getSuperClassName());
        System.out.println("interfaces: " + classFile.getInterfaces());
        classFile.getClassFileds().forEach(System.out::print);
        System.out.println();
        classFile.getClassMethods().forEach(System.out::print);
        System.out.println();
    }
}
