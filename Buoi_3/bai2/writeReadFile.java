package Buoi_3.bai2;

import java.io.*;

public class writeReadFile {
    public static void writeFile(String file, String content){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(content);
        }catch (IOException e){
            System.out.println("Lỗi khi đọc file : "+e.getMessage());
        }
    }

    public static String readFile(String file){
        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine())!= null){
                content.append(line).append("\n");
            }

        }catch (Exception e){
            System.out.println("Lỗi khi đọc file : "+e.getMessage());
            e.printStackTrace();
        }

        return content.toString().trim();
    }

    public static void copyFile(String file, String fileCopy){
        try(FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(fileCopy)){
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0){
                fos.write(buffer,0, length);
            }

        }catch (IOException e){
            System.out.println("Lỗi khi sao chép file : "+e.getMessage());
        }
    }
}
