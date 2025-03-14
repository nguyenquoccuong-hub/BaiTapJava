package Buoi_3.bai1;

import java.io.*;

public class InFile {
    public void writeFile(String file, String content){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(content);

        }catch (IOException e){
            System.out.println("Lỗi khi đọc file "+e.getMessage());

        }
    }

    public String readFile(String file){
        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());

        }
        return content.toString().trim();
    }


}
