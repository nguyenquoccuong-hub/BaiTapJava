package Buoi_4.Bai8;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class FileReadTask implements Runnable{

    private final String sourceFile;
    private final BlockingQueue<String> queue;

    public FileReadTask(String sourceFile, BlockingQueue<String> queue){
        this.sourceFile = sourceFile;
        this.queue = queue;
    }

    @Override
    public void run() {
        try(BufferedReader reader = new BufferedReader(new FileReader(sourceFile))){
            String line;
            while ((line=reader.readLine()) != null){
                queue.put(line);
            }
            queue.put("end");
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
