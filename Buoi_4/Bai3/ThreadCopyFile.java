package Buoi_4.Bai3;

import java.io.*;

public class ThreadCopyFile extends Thread{
    private String file;
    private String fileCopy;
    private String content;

    public ThreadCopyFile(String file, String fileCopy, String content){
        this.file = file;
        this.fileCopy = fileCopy;
        this.content = content;
    }

    public static void writeFile(String file, String content){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(content);
        }catch (IOException e){
            System.out.println("Lỗi khi ghi file : "+e.getMessage());
        }
    }

    @Override
    public void run() {
        try(BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileCopy))){
            String line;
            while ((line = reader.readLine()) != null){
                writer.write(line+"\n");
                writer.flush();
                System.out.println("Đã sao chep : "+line);
                Thread.sleep(1000);
            }
            System.out.println("Sao chép hoàn tất");
        }catch (IOException | InterruptedException e){
            System.out.println("Lỗi khi sao chép : "+ e.getMessage());
        }
    }
}
