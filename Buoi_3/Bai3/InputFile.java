package Buoi_3.Bai3;

import java.io.*;

public class InputFile {
    public static void writeFile(String file, String content){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(content);
        }catch (IOException e){
            System.out.println("LỖi khi ghi file : "+e.getMessage());
        }
    }

    public static int readFile(String file){
        int count=0;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            while (reader.readLine() != null){
                count++;

            }
        }catch (IOException e){
            System.out.println("Lỗi khi đọc file : "+e.getMessage());
            e.printStackTrace();
        }
        return count;
    }
}
