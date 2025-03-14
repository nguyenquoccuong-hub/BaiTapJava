package Buoi_2.Bai3;

public class StarvationExample {
    public static void main(String[] args) {

        Thread highPriority = new Thread(()->{
            int count=0;
            while (count<20){
                System.out.println("High priority thread running..");
                Thread.yield();
                count++;
            }

        });

        Thread lowPriority = new Thread(()->{
            int count=0;
            while (count<20) {
                System.out.println("Low priority thread running..");
                count++;
            }

        });

        highPriority.setPriority(Thread.MAX_PRIORITY);
        lowPriority.setPriority(Thread.MIN_PRIORITY);

        highPriority.start();
        lowPriority.start();
    }
}
