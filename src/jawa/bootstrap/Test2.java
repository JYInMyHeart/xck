package jawa.bootstrap;

public class Test2 {
    public static int staticVar;
    public int instanceVar;

    public static void main(String[] args) {
        int x = 32768; // ldc
        Test2 myObj = new Test2(); // new
        Test2.staticVar = x; // putstatic
        x = Test2.staticVar; // getstatic
        myObj.instanceVar = x; // putfield
        x = myObj.instanceVar; // getfield
        Object obj = myObj;
        if (obj instanceof Test2) { // instanceof
            myObj = (Test2) obj; // checkcast
            System.out.println(myObj.instanceVar);
        }
    }
}
