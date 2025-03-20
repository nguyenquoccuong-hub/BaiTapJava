package Buoi_4.Bai4;

import Buoi_4.Bai1.FileReadThread;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String file = "src/Buoi_4/Bai4/WordCount";
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        System.out.println("ghi nội dung vào file : ");
        StringBuilder content = new StringBuilder();
        while (true){
            String line = sc.nextLine();
            if("exit".equalsIgnoreCase(line)){
                break;
            }
            content.append(line).append("\n");
        }
        sc.close();

        WriteFile.writerFile(file, content.toString());

        ReadFile readFile = new ReadFile(file, queue);
        WordCountThread wordCountThread = new WordCountThread(queue);

        readFile.start();
        wordCountThread.start();
    }
}
