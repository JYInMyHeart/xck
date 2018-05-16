package cmd;

import classpath.Classpath;

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
                    cmd.setClasssName(msg[1]);
                    cmd.setCpOption(msg[0]);
                }
            }
        }
    }

    public void startJvm(Cmd cmd){
        Classpath cp = new Classpath().parse(cmd.getxJreOption(),cmd.getCpOption());
        System.out.println("classpath: " + cmd.getClasssName());
        byte[] classData = cp.raedClass(cmd.getClasssName().replace(".","/"));
        System.out.println(Arrays.toString(classData));
        System.out.println(cmd);
    }
}
