package Buoi_2.Bai1;

public class inSo2 {
    public static void main(String[] args) throws InterruptedException {
        inSo i1 = new inSo();
        Thread t1 = new Thread(()->{
            for(int i=0; i<1000;i++)i1.increment();
        });

        Thread t2 = new Thread(()->{
            for(int i=0; i<1000;i++)i1.increment();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("count : "+i1.getCount());
    }


}
