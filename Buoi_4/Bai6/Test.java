package Buoi_4.Bai6;

import java.nio.file.Path;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws Exception {
        Path filePath = Path.of("src/Buoi_4/Bai6/largefile.txt");
        long fileSize = filePath.toFile().length();
        int numThreads = 4;
        long chunkSize = fileSize/numThreads;

        ExecutorService executor = Executors.newWorkStealingPool(numThreads);
        
        for (int i=0; i<numThreads; i++){
            long start = i*chunkSize;
            long end = (i==numThreads-1)?fileSize : start+chunkSize;
            executor.execute(new FileReaderTask(filePath,start,end));
        }
        
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

    }
}
