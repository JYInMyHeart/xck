package jawa.bootstrap;

public class Test4 implements Runnable {
    public static void main(String[] args) {
        new Test4().test();
    }

    public void test() {
        Test4.staticMethod(); // invokestatic
        Test4 demo = new Test4(); // invokespecial
        demo.instanceMethod(); // invokespecial
        super.equals(null); // invokespecial
        this.run(); // invokevirtual
        ((Runnable) demo).run(); // invokeinterface
    }

    public static void staticMethod() {
    }

    private void instanceMethod() {
    }

    @Override
    public void run() {
    }
}