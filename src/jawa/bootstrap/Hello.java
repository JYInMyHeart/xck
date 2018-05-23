package jawa.bootstrap;

public abstract class Hello {
    public static final boolean FLAG = true;
    public static final byte BYTE = 123;
    public static final char X = 'X';
    public static final short SHORT = 12345;
    public static final int INT = 123456789;
    public static final long LONG = -6849794470754667710L;
    public static final float PI = 3.14f;
    public static final double E = 2.71828;

    public static void main(String[] args) throws RuntimeException {
        System.out.println("Hello, World!");
    }

    @Override
    public String toString() {
        return super.toString();
    }


    public abstract void s();
}
