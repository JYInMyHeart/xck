package jawa.bootstrap;

public class Test5 {
    public static void main(String[] args) {
        int a = fibonacci(30);
        int b = 4;
        int c = a + b;
        Long d = Long.valueOf(c);

    }
    private static int fibonacci(int n) {
        if (n <= 1) { return n; }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
