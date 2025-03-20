package Buoi_4.Bai6;

import java.io.RandomAccessFile;
import java.nio.file.Path;

public class FileReaderTask implements Runnable{
    private final Path filePath;
    private final long start;
    private final long end;

    public FileReaderTask(Path filePath, long start, long end){
        this.filePath = filePath;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        try(RandomAccessFile file = new RandomAccessFile(filePath.toFile(), "r")){
            file.seek(start);
            byte[] buffer = new byte[(int) (end-start)];
            file.read(buffer);
            String content = new String(buffer);
            System.out.println(Thread.currentThread().getName()+" read: "+content);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}