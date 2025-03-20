package Buoi_4.Bai4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    public static void writerFile(String file, String content){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(content);
        }catch (IOException e){
            System.out.println("Lỗi khi ghi file : "+e.getMessage());
        }
    }
}
