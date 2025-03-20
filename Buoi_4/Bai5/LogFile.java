package Buoi_4.Bai5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogFile extends Thread{
    private String logFile;
    private BlockingQueue<String> queue;
    private boolean running = true;

    public LogFile(String logFile){
        this.logFile = logFile;
        this.queue = new LinkedBlockingQueue<>();
    }

    public void log(String message){
        try{
            queue.put(message);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    public void stopLog(){
        running = false;
        queue.add("End");
    }

    @Override
    public void run() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))){
            while (running || !queue.isEmpty()){
                String message = queue.take();
                if("End".equals(message)){
                    break;
                }
                String timesTamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" ).format(new Date());
                String logEntry = timesTamp + " - " + message;
                writer.write(logEntry+"\n");
                writer.flush();
                System.out.println("Ghi log : "+logEntry);
                sleep(1000);
            }
        }catch (IOException | InterruptedException e){
            System.out.println("Lỗi khi ghi log : "+e.getMessage());
        }
    }

}
