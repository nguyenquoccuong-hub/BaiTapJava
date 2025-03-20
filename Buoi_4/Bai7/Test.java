package Buoi_4.Bai7;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        String filePath = "src/Buoi_4/Bai7/output.txt";
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new FileWriterThread(filePath));
        executor.shutdown();
    }
}
