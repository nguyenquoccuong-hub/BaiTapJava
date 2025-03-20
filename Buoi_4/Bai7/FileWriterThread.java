package Buoi_4.Bai7;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWriterThread implements Runnable{
    private final String filePath;

    public FileWriterThread(String filePath){
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try (Scanner sc = new Scanner(System.in);
             FileWriter writer = new FileWriter(filePath, true)){
            System.out.println("Nhập dữ liệu vào file : ");
            while (true){
                String input = sc.nextLine();
                if("exit".equalsIgnoreCase(input)){
                    break;
                }
                writer.write(input+"\n");
                writer.flush();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
