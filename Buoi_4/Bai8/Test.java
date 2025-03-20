package Buoi_4.Bai8;

import Buoi_4.Bai6.FileReaderTask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {
    private static final String sourceFile = "src/Buoi_4/Bai8/source.txt";
    private static final String destFile = "src/Buoi_4/Bai8/dest.txt";
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private static final String end = "end";

    public static void main(String[] args) {
        Thread readerThread = new Thread(new FileReadTask(sourceFile, queue));
        Thread writerThread = new Thread(new FileWriterTask(destFile, queue));

        readerThread.start();
        writerThread.start();

        try{
            readerThread.join();
            writerThread.join();
            System.out.println("Ghi đến file đích thành công");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
