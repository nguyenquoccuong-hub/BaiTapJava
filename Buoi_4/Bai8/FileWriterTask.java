package Buoi_4.Bai8;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class FileWriterTask implements Runnable{

    private final String destFile;
    private final BlockingQueue<String> queue;

    public FileWriterTask(String destFile, BlockingQueue<String> queue){
        this.destFile = destFile;
        this.queue = queue;
    }

    @Override
    public void run() {
        try(BufferedWriter writer = new BufferedWriter((new FileWriter(destFile)))){
            while (true){
                String line = queue.take();
                if("end".equals(line)){
                    break;
                }
                writer.write(line);
                writer.newLine();
            }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
