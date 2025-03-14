package Buoi_3.bai2;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        String inputFile = "src/Buoi_3/bai2/input";
        String outputFile = "src/Buoi_3/bai2/ouput";
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập nội dung muốn ghi vào file 1 : ");
        String file1 = sc.nextLine();
        writeReadFile.writeFile(inputFile,file1);
        writeReadFile.copyFile(inputFile, outputFile);

        System.out.println(writeReadFile.readFile(outputFile));


    }
}
