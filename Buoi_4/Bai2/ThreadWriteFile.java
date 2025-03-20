package Buoi_4.Bai2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ThreadWriteFile extends Thread{
    private String file;
    private String content;

    public ThreadWriteFile(String file, String content){
        this.file = file;
        this.content = content;
    }

    @Override
    public void run() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
            for(int i=0; i<5; i++){
                writer.write(content+"\n");
                writer.flush();
                System.out.println(Thread.currentThread().getName()+"ghi : "+content);
                Thread.sleep(1000);
            }
        }catch (IOException | InterruptedException e){
            System.out.println("Lỗi khi ghi file : "+e.getMessage());
        }
    }
}
