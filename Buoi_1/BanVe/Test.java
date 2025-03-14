package Buoi_1.BanVe;

public class Test {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new QuayVe("A"));
        Thread thread2 = new Thread(new QuayVe("B"));
        Thread thread3 = new Thread(new QuayVe("C"));


        thread1.start();
        thread2.start();
        thread3.start();
    }
}
