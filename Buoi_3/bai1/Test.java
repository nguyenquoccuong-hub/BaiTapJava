package Buoi_3.bai1;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InFile i1 = new InFile();
        String nameFile = "src/Buoi_3/bai1/a";
        System.out.println("Nhập nội dung ghi vào file : ");
        String content = sc.nextLine();
        i1.writeFile(nameFile,content);
        String noiDung = i1.readFile(nameFile);
        System.out.println(noiDung);
    }
}
