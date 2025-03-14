package Buoi_2.Bai4;

public class InterruptExample {
    public static void main(String[] args) throws  InterruptedException{
        Thread worker = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
                try{
                    System.out.println("Worker is running...");
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    System.out.println("Worker thread inerrupted!");
                    Thread.currentThread().interrupt();
                }
            }

            System.out.println("Worker stopped.");
        });

        worker.start();
        Thread.sleep(3000);
        worker.interrupt();
    }
}
