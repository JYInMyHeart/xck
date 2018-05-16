package bootstrap;

import cmd.Cmd;
import cmd.CmdOperations;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            try {
                System.out.print("xckJxm>");
                String msg = scanner.nextLine();
                Cmd cmd = new Cmd();
                CmdOperations cmdOperations = new CmdOperations();
                cmdOperations.parseCmd(cmd,msg);
                cmdOperations.startJvm(cmd);
            } catch (Exception e) {
                System.out.println("this cmd is undefined! please reset ur input!" + e);
                e.printStackTrace();
            }

        }
    }
}
