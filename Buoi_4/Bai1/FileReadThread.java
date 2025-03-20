package Buoi_4.Bai1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReadThread extends Thread{
    private String file;

    public FileReadThread(String file){
        this.file = file;
    }

    @Override
    public void run() {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
                Thread.sleep(1000);
            }
        }catch (IOException | InterruptedException e){
            System.out.println("Lỗi khi đọc file : " + e.getMessage());
        }
    }
}
