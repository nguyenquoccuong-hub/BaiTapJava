package Buoi_2.Bai1;
public class inSo {

    private int count = 0;
    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount(){
        return count;
    }
}
