package Buoi_4.Bai4;

import java.util.concurrent.BlockingQueue;

public class WordCountThread extends Thread{
    private BlockingQueue<String> queue;
    public WordCountThread(BlockingQueue <String> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        int wordCount = 0;
        int dong = 0;
        try{
            while (true){
                String line = queue.take();
                if("End".equals(line)){
                    break;
                }
                int count = line.split("\\s+").length;
                wordCount += count;
                dong++;
                System.out.println("Đếm : "+count+" từ");
            }
            System.out.println("Tổng số từ : "+wordCount);
            System.out.println("Tổng số dòng : "+dong);
        }catch (InterruptedException e){
            System.out.println("Lỗi khi xử lý dữ liệu : "+e.getMessage());
        }
    }
}
