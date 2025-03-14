package Buoi_1.Chan_Le;

public class Bai1 {
    private int count = 1;
    private int limit;

    public Bai1(int limit) {
        this.limit = limit;
    }

    public synchronized void printOdd() {
        while (count < limit) {
            while (count % 2 == 0) { // Chờ nếu đang là số chẵn
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("lẻ: " + count);
            count++;
            notify(); // Đánh thức luồng chẵn
        }

    }

    public synchronized void printEven() {

        while (count <= limit) {
            while (count % 2 != 0) { // Chờ nếu đang là số lẻ
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("chẵn: " + count);
            count++;
            notify(); // Đánh thức luồng lẻ
        }

    }
}
