package Buoi_4.Bai9;

import java.io.*;

public class CharCountThread extends Thread{
    private final String inputFile;
    private final String outputFile;

    public CharCountThread(String inputFile, String outputFile){
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    @Override
    public void run() {
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))){
            int charCount = 0;
            int a;
            while ((a = reader.read()) != -1){
                charCount++;
            }
            writeResult(charCount);

        }catch (IOException e){
            System.out.println("Lỗi khi đọc file : "+e.getMessage());
        }
    }

    private void writeResult(int charCount){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))){
            writer.write("số kí tự : "+charCount);
            System.out.println("số kí tự được ghi vào : "+charCount);
        }catch (IOException e){
            System.out.println("lỗi khi ghi file : "+e.getMessage());
        }
    }

}
