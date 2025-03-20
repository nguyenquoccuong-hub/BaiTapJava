package Buoi_4.Bai4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class ReadFile extends Thread{
    private String file;
    private BlockingQueue<String> queue;

    public ReadFile(String file, BlockingQueue <String> queue){
        this.file = file;
        this.queue = queue;
    }

    @Override
    public void run() {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null){
                queue.put(line);
                System.out.println("Đọc : "+line);
            }
            queue.put("End");
        }catch (IOException | InterruptedException e){
            System.out.println("Lỗi khi đọc file : "+e.getMessage());
        }
    }
}
