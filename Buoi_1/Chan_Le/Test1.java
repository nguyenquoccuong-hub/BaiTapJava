package Buoi_1.Chan_Le;

public class Test1 {
    public static void main(String[] args) {
        Bai1 b1= new Bai1(1000);

        Thread leThread = new Thread(b1::printOdd);
        Thread chanThread = new Thread(b1::printEven);

        leThread.start();
        chanThread.start();
    }
}
