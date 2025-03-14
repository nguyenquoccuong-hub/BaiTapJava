package Buoi_2.Bai6;

public class DemNguoc {
    public static void main(String[] args) {

        Thread t1 = new Thread(()->{
            for(int i=10; i>=0; i--){
                try{
                    System.out.println(i);
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        });
        t1.start();
    }
}
